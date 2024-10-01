import java.io.File

/**
 * Составление поста из шапок других постов
 */

fun startFormatPost() {
    println("Путь к директории сохранения файла:")
    val path = readln()

    println("Имя файла для сохранения поста (без расширения):")
    val fileName = readln()

    val file = File("$path${File.separator}${fileName}.txt")

    val post = StringBuilder()
    post.append("⁉️ Чтобы приготовить на выходных? Смотрите нашу подборку из прошлых рецептов.\n\n")

    do {
        var command = ""

        println("Название раздела (Супы, Выпечка, Салаты, Вторые блюда, Десерты, Закуски, Соусы, Напитки, Заготовки 'без учета регистра'):")
        val sectionName = readln()
        if (sectionName.lowercase() == "exit") break

        var index = 0
        do {
            println("Название поста:")
            val postTitle = readln()
            if (postTitle == "exit") command = "exit"

            if (command == "exit") break
            println("Ссылка на пост '$postTitle':")
            val postLink = readln()
            if (postLink == "exit") command = "exit"

            if (command == "exit") break
            if (index == 0) {
                val smile = when (sectionName.lowercase()) {
                    "супы" -> "\uD83C\uDF5C "
                    "выпечка" -> "\uD83E\uDD6E "
                    "салаты" -> "\uD83E\uDD57 "
                    "вторые блюда" -> "\uD83C\uDF57 "
                    "десерты" -> "\uD83C\uDF70 "
                    "закуски" -> "\uD83C\uDF2E "
                    "напитки" -> "\uD83C\uDF79 "
                    "заготовки" -> "\uD83E\uDD6B "
                    "соусы" -> "\uD83E\uDED5 "
                    else -> ""
                }
                post.append("$smile${sectionName.uppercase()}:\n")
            }
            post.append("${++index}. [${modifyString(postTitle)}]($postLink)\n")

        } while (true)
        post.append("\n")
    } while (true)

    post.append("#подборка")

    if (post.isNotBlank()) {
        file.writeText(post.toString().trim())
        if (file.exists()) println("Файл '$fileName.txt' создан")
    }
}

private fun modifyString(title: String): String {
    return if (title.contains('«')) {
        title.lowercase().replaceFirstChar { it.uppercase() }.split(" ").joinToString(" ") { capitalizeAfterQuote(it)}
    } else {
        title.lowercase().replaceFirstChar { it.uppercase() }
    }
}

private fun capitalizeAfterQuote(input: String): String {
    if (input.startsWith('«') && input.length > 1) {
        return input[0] + input[1].uppercase() + input.substring(2)
    }
    return input
}