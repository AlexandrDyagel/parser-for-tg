import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface TelegramBotService {
    @GET("getUpdates")
    suspend fun getUpdates(
        @Query("offset") offset: Long?,
        @Query("limit") limit: Int?,
        @Query("timeout") timeout: Int?
    ): ApiResponse<List<Update>>
}

data class ApiResponse<T>(
    val ok: Boolean,
    val result: T?,
    val error_code: Int?,
    val description: String?
)

data class Update(
    val update_id: Long,
    val message: Message?
)

data class Message(
    val message_id: Long,
    val date: Long,
    val chat: Chat,
    val text: String?,
    val entities: List<MessageEntity>?
)

data class Chat(
    val id: Long,
    val type: String,
    val username: String
)

data class MessageEntity(
    val type: String,
    val offset: Int,
    val length: Int
)

suspend fun getAllChannelPosts(
    botToken: String,
    channelUsername: String
): List<Message> {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.telegram.org/bot$botToken/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val telegramBotService = retrofit.create(TelegramBotService::class.java)

    var offset: Long? = null
    val limit = 100
    val timeout = 30

    val allMessages = mutableListOf<Message>()

    while (true) {
        val response = telegramBotService.getUpdates(offset, limit, timeout)

        if (!response.ok) {
            throw RuntimeException("Failed to get updates: ${response.description}")
        }

        val updates = response.result ?: emptyList()

        if (updates.isEmpty()) {
            break
        }

        for (update in updates) {
            offset = update.update_id + 1

            val message = update.message ?: continue
            val chat = message.chat

            if (chat.type != "channel" || chat.username != channelUsername) {
                continue
            }

            val text = message.text ?: continue
            val entities = message.entities ?: continue

            val postEntity = entities.find { it.type == "url" } ?: continue
            val postUrl = text.substring(postEntity.offset, postEntity.offset + postEntity.length)

            val postMessage = getPostMessage(botToken, channelUsername, postUrl)

            allMessages.add(postMessage)
        }
    }

    return allMessages
}

suspend fun getPostMessage(
    botToken: String,
    channelUsername: String,
    postUrl: String
): Message {
    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.telegram.org/bot$botToken/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val telegramBotService = retrofit.create(TelegramBotService::class.java)

    val response = telegramBotService.getUpdates(null, null, null)

    if (!response.ok) {
        throw RuntimeException("Failed to get updates: ${response.description}")
    }

    val updates = response.result ?: emptyList()

    val postMessage = updates
        .mapNotNull { it.message }
        .find {
            it.chat.type == "channel" &&
                    it.chat.username == channelUsername &&
                    it.text == postUrl
        } ?: throw RuntimeException("Failed to find post message")

    return postMessage
}