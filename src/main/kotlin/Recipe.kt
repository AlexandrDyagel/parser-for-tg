data class Recipe(
    val title: String,
    val description: String,
    val imgUrl: String,
    val timeCook: String,
    val steps: List<String>,
    val serving: String,
    val ingredients: List<String>,
    val cuisineTags: List<String>,
    val difficulty: String,
    val advice: String
) {
    override fun toString(): String {
        val sb = StringBuilder()

        sb.append("\uD83E\uDD63 ${"**${title.uppercase()}**"}\n")

        sb.append(if (description.isNotBlank()) "\n✏️ $description\n\n" else "\n")

        if (timeCook.isNotBlank()) sb.append("⏰ **Время приготовления:** $timeCook\n")
        if (difficulty.isNotBlank()) sb.append("\uD83C\uDF96 **Сложность:** $difficulty\n")
        if (serving.isNotBlank()) sb.append("\uD83C\uDF72 **Порций:** $serving\n")

        if (timeCook.isBlank() && difficulty.isBlank() && serving.isBlank())
            sb.append("\uD83D\uDCD6 __Ингредиенты:__\n\n") else sb.append("\n\uD83D\uDCD6 __Ингредиенты:__\n\n")

        // Список ингредиентов
        sb.append(
            ingredients.mapIndexed { index, ingredient ->
                if (index % 2 == 0) "\uD83D\uDD39 $ingredient" else "\uD83D\uDD38 $ingredient"
            }.joinToString("\n")
        )

        sb.append("\n\n\uD83E\uDDD1\uD83C\uDFFC\u200D\uD83C\uDF73 __Приготовление:__\n\n")

        // Шаги приготовления
        sb.append(
            steps.mapIndexed { index, step ->
                "${index + 1}. $step"
            }.joinToString("\n")
        )

        // Советы
        if (advice.isNotBlank()) sb.append("\n\n❗️**Совет:** __${advice}__")

        // Тэги
        sb.append("\n\n${cuisineTags.joinToString(" ")}\n\n")

        // Ссылка на канал
        sb.append("\uD83D\uDC49 [$NAME_CHANNEL]($LINK_CHANNEL)")

        return sb.toString()
    }

    fun toStringWithoutMarkdown(): String {
        val sb = StringBuilder()

        sb.append("\uD83E\uDD63 ${title.uppercase()}\n")

        sb.append(if (description.isNotBlank()) "\n✏️ $description\n\n" else "\n")

        if (timeCook.isNotBlank()) sb.append("⏰ Время приготовления: $timeCook\n")
        if (difficulty.isNotBlank()) sb.append("\uD83C\uDF96 Сложность: $difficulty\n")
        if (serving.isNotBlank()) sb.append("\uD83C\uDF72 Порций: $serving\n")

        if (timeCook.isBlank() && difficulty.isBlank() && serving.isBlank())
            sb.append("\uD83D\uDCD6 Ингредиенты:\n\n") else sb.append("\n\uD83D\uDCD6 Ингредиенты:\n\n")

        // Список ингредиентов
        sb.append(
            ingredients.mapIndexed { index, ingredient ->
                if (index % 2 == 0) "\uD83D\uDD39 $ingredient" else "\uD83D\uDD38 $ingredient"
            }.joinToString("\n")
        )

        sb.append("\n\n\uD83E\uDDD1\uD83C\uDFFC\u200D\uD83C\uDF73 Приготовление:\n\n")

        // Шаги приготовления
        sb.append(
            steps.mapIndexed { index, step ->
                "${index + 1}. $step"
            }.joinToString("\n")
        )

        // Советы
        if (advice.isNotBlank()) sb.append("\n\n❗️Совет: $advice")

        // Тэги
        sb.append("\n\n${cuisineTags.joinToString(" ")}\n\n")

        // Ссылка на канал
        sb.append("\uD83D\uDC49 $NAME_CHANNEL - $LINK_CHANNEL")

        return sb.toString()
    }

    fun toStringForVK(): String {
        val sb = StringBuilder()

        sb.append("\uD83E\uDD63 ${title.uppercase()}\n")

        sb.append(if (description.isNotBlank()) "\n✏️ $description\n\n" else "\n")

        if (timeCook.isNotBlank()) sb.append("⏰ Время приготовления: $timeCook\n")
        if (difficulty.isNotBlank()) sb.append("\uD83C\uDF96 Сложность: $difficulty\n")
        if (serving.isNotBlank()) sb.append("\uD83C\uDF72 Порций: $serving\n")

        if (timeCook.isBlank() && difficulty.isBlank() && serving.isBlank())
            sb.append("\uD83D\uDCD6 Ингредиенты:\n\n") else sb.append("\n\uD83D\uDCD6 Ингредиенты:\n\n")

        // Список ингредиентов
        sb.append(
            ingredients.mapIndexed { index, ingredient ->
                if (index % 2 == 0) "\uD83D\uDD39 $ingredient" else "\uD83D\uDD38 $ingredient"
            }.joinToString("\n")
        )

        sb.append("\n\n\uD83E\uDDD1\uD83C\uDFFC\u200D\uD83C\uDF73 Приготовление:\n\n")

        // Шаги приготовления
        sb.append(
            steps.mapIndexed { index, step ->
                "${index + 1}. $step"
            }.joinToString("\n")
        )

        // Советы
        if (advice.isNotBlank()) sb.append("\n\n❗️Совет: $advice")

        // Тэги
        //sb.append("\n\n${cuisineTags.joinToString(" ")}\n\n")

        // Ссылка на канал
        sb.append("\n\n\uD83D\uDC49 Наш телеграм-канал - $LINK_CHANNEL")

        return sb.toString()
    }

    companion object {
        const val NAME_CHANNEL = "Рецепты настоящей еды"
        const val LINK_CHANNEL = "https://t.me/+P-ckB1rUnN04NWNi"
    }
}

