import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class HeaderPost(
    @SerialName("id") val id: Int,
    @SerialName("text") val body: List<ItemPost>
)

@Serializable
data class ItemPost(
    @SerialName("type") val type: String,
    @SerialName("text") val text: String
)
