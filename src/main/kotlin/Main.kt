@file:OptIn(DelicateCoroutinesApi::class)

import kotlinx.coroutines.*
import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import org.yaml.snakeyaml.Yaml
import java.io.File
import java.io.FileInputStream

import java.net.URL
import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset
import java.nio.file.Files
import java.nio.file.Paths
import java.util.Properties
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

suspend fun schedule(hour: Int = 0, minutes: Int = 0, seconds: Int = 0, block: (LocalDateTime) -> Unit) {
    repeat(Int.MAX_VALUE) {
        delay(1000)

        val currentMoment = Clock.System.now()
        val dateTime = currentMoment.toLocalDateTime(TimeZone.currentSystemDefault())

        if (dateTime.hour == hour && dateTime.minute == minutes && dateTime.second == seconds)
            block(dateTime)
    }

}

@OptIn(ExperimentalTime::class)
fun main(args: Array<String>) {
//    val str = """{"initData":"query_id=AAH_hg4qAgAAAP-GDip5lmCq&user=%7B%22id%22%3A5000562431%2C%22first_name%22%3A%22%D0%90%D0%BB%D0%B5%D0%BA%D1%81%D0%B0%D0%BD%D0%B4%D1%80%22%2C%22last_name%22%3A%22%D0%A4%D1%80%D0%BE%D1%81%D1%82%22%2C%22username%22%3A%22iamdruid%22%2C%22language_code%22%3A%22ru%22%2C%22allows_write_to_pm%22%3Atrue%7D&auth_date=1729623319&hash=b0f5b4249dca6fe34c3f09d63fd317d2a29e34979f08abea4522392561a1a531"}"""

    /*val session = Jsoup.newSession().timeout(30000).userAgent("Mozilla")
    session.newRequest().url("").get()*/

//    val jsoup = Jsoup.connect("https://t.me/iamdruid").get()
//    val src = jsoup.select("img").attr("src")
//    println(src)
//    val a = "https://cdn4.cdn-telegram.org/file/nHPy3aaN6RPwr6wnphh52jygPIzJauMVjLTah9UX7Kti-EX6NRcAQn6j0oCJ9SOW5CwhHnn_5IVwsegmhfsNv2xZlvv_0K48RY0vpQMuSOoSfft-THEwXQkZM5W2QRSZhlqU2GsXLnRniqhRF5_3S21Q0GvSXtWa0-JG0tHX10ISNy2SC4Ph6RniJrQrynvNx-6KwDM06qy4HOcM6WM3hEVLquLT204h-dj33Lk4WCv35X15V8q1z9U2-SJp0NNOgIQmNF4S0rxPuBw4Qhcy8NotOkfAAiH6rB6BANXGJZ-57-oSEssekBWG4pFAUZfrZ6hSELZkUlJ0PirKq_12kA.jpg"
//    val b = "https://cdn4.cdn-telegram.org/file/IVMo3VjPIm2HKsmiv-CTy8QuvB5joUKK-k8oFGOymeWOgdUnW2OmO0Q0ZcXKARZcc1y_05L8pKO5hCOhDdV1JBchr8Ihy14-4NQVxipVz7LJqJg7vL6pRDv3328-MA-aRhyqv5HBqC2bRh3nepHWTAZAmR8E2XooR2j-TjDJmFLI_5LqcmHrvXFCJbwWrUQuuPT6EP4HFovlG39kOGQ-sXbmj4S-EtE9BuiYK4WcYTX7crM8wAGqw9pFKrpgvM0AJMbkDQdOkuEgjEQ0CdNot1qJTHemNSUU6-9qnVz-vfBzTkEI7q9jB-TDwljyWluwYJ5AizjCHwzQnYagdsTUHA.jpg"

    val time = measureTime {
        //editFilesTxt2()
        //titleLowercase()

        // TODO Расширение файла должно быть .properties
//        val properties = Properties()
//        val input = FileInputStream("config.yml")
//        properties.load(input)
//
//        println("Имя: ${properties.getProperty("user.name")}\nПочта: ${properties.getProperty("user.email")}")

        // TODO расширение файла должно быть .yml

//        val file = File("config.yml")
//        val yaml = Yaml()
//        val config = yaml.load<Map<String, String>>(file.inputStream())
//
//        val user = config["user"] as Map<String, String>
//
//        println("Имя: ${user["name"]}\nПочта: ${user["email"]}")


        runBlocking {

            //showResult()

            // TODO запуск приложения
            //startApp()

            // TODO генерация даты публикации постов
            //generatePostingDate()

            // TODO добавление новой строки после заголовка поста
            //insertNewRow()

            // TODO удаление файлов с названием времени публикации (которые пустые внутри)
            //deleteFilesFromDirs()

            // TODO заменяет одну строку текста на другую
            //editFilesTxt()

            // TODO составление поста из названий рецептов
            //startFormatPost()

            // TODO парсинг ссылок на страницы рецептов
            //getRecipeLinks()

            // TODO парсинг рецептов по ссыкам на них
            //parseRecipeFromSite()

            // TODO перебор файлов в папках, можно все что угодно делать, переименовывать файлы
            //renameFilesFromDirs()

            // TODO Сохраняет в файл все уникальные тэги из всех рецептов
            //selectAllTags()
        }
    }
    println("Затраченное время на парсинг - ${time.inWholeHours} ч. ${time.inWholeMinutes} мин. ${time.inWholeSeconds} сек.")
}

fun showResult() {
    println("Путь к директории папок:")
    val path = readln()
    val mainDir = File(path)
    val resultFile = File("$path${File.separator}result.txt")
    val hashTags = mutableListOf<String>()
    val categories = mutableSetOf<String>()
    mainDir.listFiles()?.forEach { dir ->
        if (dir.isDirectory) {
            val files = dir.listFiles()
            val txtFile = files?.first { it.name.endsWith(".txt") && it.readBytes().isNotEmpty() }
            txtFile?.let { file ->
                file.forEachLine { line ->
                    if ('#' in line) {
                        if ("##" in line) {
                            val strings = line.replace("##", "")
                            val newStrings = strings.replace("#", "").split(" ").map { it.trim() }
                            newStrings.forEach {
                                if (it.isNotBlank() || it.isNotEmpty()) {
                                    hashTags.add(it)
                                }
                            }
                        } else {
                            val strings = line.replace("#", "").split(" ").map { it.trim() }
                            /*if (strings.size == 2) {
                                strings.forEach {
                                    if (!it.contains("домашн")) {
                                        hashTags.add(it)
                                    }
                                }
                            } else {
                                hashTags.add(strings.first())
                            }*/
                            if (strings.size == 2) {
                                val count = strings.filter { !it.contains("домаш") && !it.contains("кухн") }.size
                                if (count == 2) {
                                    println(file.name)
                                }
                            }
                            hashTags.add(strings.first())
                        }
                    }
                }
            }
        }
    }
    val sortedHashTags = hashTags.filter { !it.contains("кухня") }
    categories.addAll(sortedHashTags)
    val sb = StringBuilder()
    categories.forEach { category ->
        sb.append("$category - ${sortedHashTags.filter { it == category }.size}\n")
    }

    resultFile.writeText(sb.toString())
}

fun titleLowercase() {
    println("Путь к директории c папками файлов:")
    val path = readln()

    val file = File(path)
    val fileNames = file.listFiles()
    fileNames?.forEach { dir ->
        if (dir.isDirectory) {
            val innerFiles = dir.listFiles()?.map { it.name }
            val txtFiles = innerFiles?.filter { it.endsWith(".txt") }
            txtFiles?.forEach { txtFileName ->
                val f = File("$path${File.separator}${dir.name}${File.separator}$txtFileName")
                if (f.exists()) {
                    val lines = f.readLines()
                    if (lines.isNotEmpty()) {
                        val newTxt = mutableListOf<String>()
                        firstCharUppercase(lines[0])
                        newTxt.add(firstCharUppercase(lines[0]))
                        newTxt.addAll(lines.subList(3, lines.lastIndex + 1))
                        f.writeText(newTxt.joinToString("\n"))
                    }
                }
            }
        }
    }
}

fun insertNewRow() {
    println("Путь к директории c папками файлов:")
    val path = readln()
    println("Какой текст вставить:")
    val insertedRow = readln()

    val file = File(path)
    val fileNames = file.listFiles()
    fileNames?.forEach { dir ->
        if (dir.isDirectory) {
            val innerFiles = dir.listFiles()?.map { it.name }
            val txtFiles = innerFiles?.filter { it.endsWith(".txt") }
            txtFiles?.forEach { txtFileName ->
                val f = File("$path${File.separator}${dir.name}${File.separator}$txtFileName")
                if (f.exists()) {
                    val lines = f.readLines()
                    if (lines.isNotEmpty()) {
                        val newTxt = mutableListOf<String>()
                        newTxt.add(lines[0])
                        newTxt.add("\n$insertedRow")
                        newTxt.addAll(lines.subList(1, lines.lastIndex + 1))
                        f.writeText(newTxt.joinToString("\n"))
                    }
                }
            }
        }
    }
}

/**
 * Сохраняет в файл все уникальные тэги из всех рецептов
 */
fun selectAllTags() {
    println("Путь к директории папок:")
    val path = readln()
    val mainDir = File(path)
    val resultFile = File("$path${File.separator}result.txt")
    val uniq = mutableSetOf<String>()
    mainDir.listFiles()?.forEach { dir ->
        if (dir.isDirectory) {
            val files = dir.listFiles()
            val txtFile = files?.first { it.name.endsWith(".txt") && it.readBytes().isNotEmpty() }
            txtFile?.let { file ->
                file.forEachLine { line ->
                    if ('#' in line) {
                        val strings = line.replace("#", "").split(" ").map { it.trim() }
                        strings.forEach {
                            uniq.add(it)
                        }
                    }
                }
            }
        }
    }
    resultFile.writeText(uniq.joinToString("\n"))
}

/**
 * Удаление файлов с названием времени публикации
 * то есть удаляет файлы, в которых пусто (нет никакого текста)
 */
fun deleteFilesFromDirs() {
    println("Путь к директории папок:")
    val path = readln()
    val mainDir = File(path)
    mainDir.listFiles()?.forEach { dir ->
        if (dir.isDirectory) {
            val files = dir.listFiles()
            val txtFiles = files?.filter { it.name.endsWith(".txt") }
            txtFiles?.forEach {
                if (it.readBytes().isEmpty()) {
                    it.delete()
                }
            }
        }
    }
}

/**
 * Переименование файлов в директориях
 * Можно вообще все что угодно делать. Тут перебор файлов в папках
 */
fun renameFilesFromDirs() {
    println("Путь к директории папок:")
    val path = readln()
    val mainDir = File(path)
    mainDir.listFiles()?.forEach { dir ->
        if (dir.isDirectory) {
            val files = dir.listFiles()
            val txtFile = files?.first { it.name.endsWith(".txt") && !it.name.startsWith("БЕЗ РАЗМЕТКИ") }
            val fileVK = files?.first { it.name.startsWith("БЕЗ РАЗМЕТКИ") }
            val nameVk = txtFile?.nameWithoutExtension
            val f = File("$path${File.separator}${dir.name}${File.separator}${nameVk?.uppercase()}.txt")
            val text = fileVK?.readLines()

            txtFile?.delete()
            fileVK?.delete()

            f.writeText(text?.joinToString("\n") ?: "fsdfsdf")
        }
    }
}

/**
 *  Парсинг всех рецептов с сайта из ссылок, которые находятся в файле
 */
fun parseRecipeFromSite() {
    println("Путь к директории файла с данными для парсинга")
    val pathFileData = readln()
    //val pathFileData = "E:\\РЕЦЕПТЫ\\Ссылки на рецепты"

    println("Имя файла с данными для парсинга (имя файла с расширением!)")
    val fileName = readln()
    //val fileName = "Отсортированные ссылки 10798 рецептов.txt"

    println("Путь к директории для сохранения:")
    val pathSaveData = readln()
    //val pathSaveData = "E:\\РЕЦЕПТЫ\\Готовые рецепты"

    println("Имя каталога для коротких рецептов (< 1024 символов)")
    val shortDirname = readln()
    //val shortDirname = "Короткие рецепты"

    println("Имя каталога для длинных рецептов (> 1024 символов)")
    val longDirname = readln()
    //val longDirname = "Длинные рецепты"

    println("С какой позиции начать? (Enter - сначала)")
    val startIndex = readln().toInt()

    runBlocking {
        val logFile = File("$pathSaveData${File.separator}log.txt")

        val recipePreview = parseLinksFromFile(path = pathFileData, fileName = fileName)
        val recipePreviewSize = recipePreview.size
        // Парсинг рецептов из файла ссылок

        for (index in startIndex until recipePreview.size) {
            try {
                val recipe = parseRecipe(url = recipePreview[index].link, tagRecipe = recipePreview[index].badge)
                recipe?.let {
                    val message = it.toString()
                    val dirname =
                        if (message.length < 1024) "$shortDirname${File.separator}${index + 1}" else "$longDirname${File.separator}${index + 1}"

                    val absolutePathDir = createDir(path = pathSaveData, dirName = dirname)

                    saveToFile(message, absolutePathDir, "${it.title.uppercase()}.txt")
                    saveToFile(it.toStringForVK(), absolutePathDir, "Для VK.txt")
                    saveToFile(it.toStringWithoutMarkdown(), absolutePathDir, "Без разметки.txt")
                    downloadFile(URL(it.imgUrl), absolutePathDir, "${it.title.hashCode()}", ext(it.imgUrl))
                    println("Прогресс ${(index + 1) / recipePreviewSize.toFloat() * 100}%")
                }
            } catch (e: Exception) {
                val errorMessage = "Произошла ошибка в ${index + 1} записи (index=$index): ${e.message} записях/ссылках"
                println(errorMessage)
                if (!logFile.exists())
                    logFile.writeText("$errorMessage\n")
                else
                    logFile.appendText("$errorMessage\n")
            }
        }
        println("Всего ошибок - ${logFile.readLines().size}")
    }
}

/**
 * Получение данных для парсинга из файла
 * @return Список RecipePreview
 */
fun parseLinksFromFile(path: String, fileName: String): List<RecipePreview> {
    val file = File("$path${File.separator}$fileName")
    val recipePreview = mutableListOf<RecipePreview>()
    if (file.exists()) {
        file.readLines().forEach {
            val data = it.split('@')
            recipePreview.add(RecipePreview(badge = data.first().split("|"), link = data.last()))
        }
    } else println("Файл '$fileName' не существует")
    return recipePreview
}

/**
 * Достаем ссылки на каждый рецепт
 */
fun getRecipeLinks() {
    println("Путь к директории сохранения данных:")
    val path = readln()

    println("Имя директории сохранения данных:")
    val dirName = readln()

    println("Имя файла с расширением для сохранения данных:")
    val fileName = readln()

    println("Url для парсинга (в конце нужен слеш '/'):")
    val url = readln()

    println("Начальная страница:")
    val startPage = readln().toInt()

    println("Конечная страница:")
    val endPage = readln().toInt()

    createDir(path, dirName)

    runBlocking {
        for (page in startPage..endPage) {
            try {
                if (page == 0) continue
                val linksFromPage = parsePage(url = url, page = page)
                saveToFile(linksFromPage, "$path${File.separator}$dirName", fileName)
                println("Обработана страница $page")
            } catch (e: Exception) {
                println("Произошла ошибка на странице $page: ${e.message}")
            }
        }
        val file = File("$path${File.separator}$dirName${File.separator}$fileName")
        if (file.exists()) {
            println("Количество записей ${file.readLines().size}")
        }
    }
}

/**
 * Что-то меняем в файлах. Замена строки на другую строку
 */
fun editFilesTxt() {
    println("Путь к директории c папками файлов:")
    val path = readln()
    println("Какой текст заменить:")
    val oldText = readln()
    println("На какой текст заменить:")
    val newText = readln()

    val file = File(path)
    val fileNames = file.listFiles()
    fileNames?.forEach { dir ->
        if (dir.isDirectory) {
            val innerFiles = dir.listFiles()?.map { it.name }
            val txtFiles = innerFiles?.filter { it.endsWith(".txt") }
            txtFiles?.forEach { txtFileName ->
                val f = File("$path${File.separator}${dir.name}${File.separator}$txtFileName")
                if (f.exists()) {
                    val lines = f.readLines()
                    lines.forEachIndexed { index, line ->
                        if (oldText in line) {
                            val firstTxt = lines.subList(0, index)
                            val newTxt = mutableListOf<String>()
                            newTxt.addAll(firstTxt)
                            newTxt.add(newText)
                            if (index != lines.size - 1) {
                                val lastTxt = lines.subList(index + 1, lines.indexOf(lines.last()) + 1)
                                newTxt.addAll(lastTxt)
                            }
                            f.writeText(newTxt.joinToString("\n"))
                        }
                    }
                }
            }
        }
    }
}

fun editFilesTxt2() {
    println("Путь к директории c папками файлов:")
    val path = readln()
    println("Какой текст заменить:")
    val oldText = readln()
    val newText = "\uD83D\uDC49 Больше рецептов в нашем Телеграм-каналеhttps://t.me/+NF1I5iy6xENkYWVi\n" +
            "\n" +
            "\uD83D\uDC49 Понравился рецепт? Тогда подписывайтесь на канал в Дзенhttps://dzen.ru/id/647e4298fbbc155f79d11e13 и ставьте вкусный лайк!\n" +
            "\n" +
            "У нас всегда найдется что-то вкусненькое, например:"

    val file = File(path)
    val fileNames = file.listFiles()
    fileNames?.forEach { dir ->
        if (dir.isDirectory) {
            val innerFiles = dir.listFiles()?.map { it.name }
            val txtFiles = innerFiles?.filter { it.endsWith(".txt") }
            txtFiles?.forEach { txtFileName ->
                val f = File("$path${File.separator}${dir.name}${File.separator}$txtFileName")
                if (f.exists()) {
                    val lines = f.readLines()
                    lines.forEachIndexed { index, line ->
                        if (oldText in line) {
                            val firstTxt = lines.subList(0, index)
                            val newTxt = mutableListOf<String>()
                            newTxt.addAll(firstTxt)
                            newTxt.add(newText)
                            if (index != lines.size - 1) {
                                val lastTxt = lines.subList(index + 1, lines.indexOf(lines.last()) + 1)
                                newTxt.addAll(lastTxt)
                            }
                            f.writeText(newTxt.joinToString("\n"))
                        }
                    }
                }
            }
        }
    }
}

/**
 * Парсинг всех ссылок на рецепты со страницы
 * @param [url] строка типа http://site.ru/na-kazhdyj-den/page/
 * @param [page] страница для парсинга
 * @return форматированный текст с сылками на рецепты
 */
suspend fun parsePage(url: String, page: Int): String {
    val data = StringBuilder()
    coroutineScope {
        launch {
            val doc = Jsoup.connect("$url$page").get()
            val cards = doc.select(".views-item")
            val recipePreview = mutableListOf<RecipePreview>()

            cards.forEach { card ->
                val link = card.select(".views-item__line_preview a").attr("abs:href")
                val badgeList = card.select(".snippet-badges a")
                val badge = mutableListOf<String>()
                badgeList.forEach { badge.add(it.text()) }

                if (link.isNotEmpty()) {
                    recipePreview.add(RecipePreview(link = link, badge = badge))
                }
            }
            recipePreview.forEach { data.append("${it.badge.joinToString("|")}@${it.link}\n") }
        }
    }
    return data.toString()
}

suspend fun parseRecipe(url: String, tagRecipe: List<String>): Recipe? {
    var recipe: Recipe? = null
    coroutineScope {
        launch {
            val doc = Jsoup.connect(url).timeout(30000).userAgent("Mozilla").get()
            val recipeElements = doc.select(".hrecipe")
            val title = recipeElements.select("h1.fn").text()
            val content = recipeElements.select(".recipe-content")

            val description = content.select(".recipe_desc").text()
            val imgUrl = content.select(".result-photo.photo").attr("src")
            val timeCook = content.select(".recipe_info__item_cook strong").text()

            val stepsV2 = content.select(".instructions.ver_2")
            val steps = mutableListOf<String>()
            if (stepsV2.isNotEmpty()) {
                steps.addAll(stepsV2.select(".instruction_description").map { it.text() })
                /*val noPhoto = stepsV2.select(".instruction_description._no-photo")
                if (noPhoto.isNotEmpty()){
                    steps.addAll(noPhoto.map { it.text() })
                } else {
                    val withPhoto = stepsV2.select(".instruction_description._with-photo")
                    if (withPhoto.isNotEmpty()){
                        steps.addAll(withPhoto.map { it.text() })
                    }
                }*/
            } else {
                val stepsV1 = content.select(".instructions.ver_1")
                if (stepsV1.isNotEmpty()) {
                    steps.addAll(stepsV1.select(".instruction").map { it.text() })
                }
            }

            val serving = content.select("#servings_input").attr("value")

            val ingredientElements = content.select(".ingredient")
            val ingredients = ingredientElements.map { ingredient ->
                val name = ingredient.select(".name").text()
                val value = ingredient.select(".value").text() ?: ""
                val type = ingredient.select(".type").text() ?: ""
                val amount = ingredient.select(".amount").text() ?: ""
                val notes = ingredient.select(".notes").text() ?: ""
                val temp =
                    if (type.isBlank() && value.isBlank() && amount.isBlank()) "$name$notes" else "$name$notes - $amount$value $type"
                temp.trim().replaceFirstChar { it.uppercase() }
            }

            val cuisinesElement = content.select(".recipe-cuisine")
            val cuisinesList = cuisinesElement.select("a")
            val cuisinesSingle = cuisinesElement.select("span")
            val cuisines = mutableListOf<String>()
            cuisines.addAll(tagRecipe)
            if (!cuisinesList.isNullOrEmpty()) {
                cuisines.addAll(cuisinesList.map { it.text() })
            } else {
                cuisines.addAll(cuisinesSingle.map { it.text() })
            }

            val difficulty = content.select(".recipe-difficulty span").text()

            val advice = content.select(".recipe-notes div").text()

            val urlsPhotoReport = content.select(".photo-report_img img").map { it.attr("src") }

            recipe = Recipe(
                title = title,
                description = description,
                imgUrl = imgUrl,
                timeCook = timeCook,
                steps = steps.filter { it.isNotBlank() },
                serving = serving,
                ingredients = ingredients,
                cuisineTags = cuisines.map { "#${it.lowercase().replace(" ", "_")}" },
                difficulty = difficulty,
                advice = advice
            )
        }
    }
    return recipe
}

/**
 * @return Абсолютный путь к созданной директории
 */
fun createDir(path: String, dirName: String): String {
    val dir = File("$path${File.separator}$dirName")
    if (dir.mkdirs()) {
        println("Директория '$dirName' создана")
    }
    return "$path${File.separator}$dirName"
}

/**
 * Сохранение текста в файл
 * @param [data] Текст для сохранения
 * @param [path] Путь к директории сохранения данных
 * @param [fileName] Имя файла для сохранения
 */
suspend fun saveToFile(data: String, path: String, fileName: String) = coroutineScope {
    launch {
        val file = File("$path${File.separator}$fileName")
        if (!file.exists()) file.writeText(data) else file.appendText(data)
    }
}

suspend fun downloadFile(url: URL, path: String, fileName: String, ext: String) = coroutineScope {
    launch {
        withContext(Dispatchers.IO) {
            url.openStream()
        }.use { Files.copy(it, Paths.get("$path${File.separator}$fileName.$ext")) }
    }
}

/**
 * Получение расширения файла из строки типа url
 * @param [link] Строка типа url
 * @return Расширение файла без .
 */
fun ext(link: String) = link.split(".").last()

/**
 * Преобразует первый символ строки в верхний регистр
 */
private fun firstCharUppercase(row: String): String {
    val strings = row.split(" ")

    val detectedStrings: List<String>
    val smile: String?

    if (strings[0][0].isSurrogate()) {
        detectedStrings = strings.subList(1, strings.size)
        smile = strings[0]
    } else {
        detectedStrings = strings
        smile = null
    }


    val transformedString = detectedStrings.map { it.lowercase() }.toMutableList().apply {
        replaceAll {
            if (it[0] == '«') {
                it.split('«').joinToString("«") { it.replaceFirstChar { it.uppercase() } }
            } else {
                it
            }
        }
    }

    return if (smile != null) {
        "$smile ${transformedString.joinToString(" ").replaceFirstChar { it.uppercase() }}"
    } else {
        transformedString.joinToString(" ").replaceFirstChar { it.uppercase() }
    }
}

private fun showMenu(): Int {
    println("Выберите пункт меню:")

    Menu.values().forEach { menuItem ->
        println(coloredString("${menuItem.id}. ${menuItem.text}", Color.BLUE))
    }

    return readln().toInt()
}

private fun exitApp(): Boolean {
    println(coloredString("Выход из приложения", Color.RED))
    return true
}


private fun headerTemplate(title: String, color: Color) {
    val lineStars = "*".repeat(title.length + 4)
    println("${color.code}\n$lineStars${Color.RESET.code}")
    println("${color.code}* $title *${Color.RESET.code}")
    println("${color.code}$lineStars\n${Color.RESET.code}")
}

private fun processEnd(message: String) {
    println(coloredString("\n$message\n", Color.GREEN))
}

private fun coloredString(text: String, color: Color) = "${color.code}$text${Color.RESET.code}"

private fun startProcess(menuItem: Menu, messageProcessEnd: String, process: () -> Unit) {
    headerTemplate(menuItem.text, Color.GREEN)
    process()
    processEnd(">>> $messageProcessEnd <<<")
}

private fun startApp() {
    var isExit = false
    while (!isExit) {
        val answer = showMenu()
        when (answer) {
            Menu.GeneratePostingDate.id -> {
                startProcess(Menu.GeneratePostingDate, "Процесс генерации даты публикации постов завершен") {
                    generatePostingDate()
                }
            }

            Menu.InsertNewRow.id -> {
                startProcess(Menu.InsertNewRow, "Процесс добавления новой строки после заголовка поста завершен") {
                    insertNewRow()
                }
            }

            Menu.DeleteFilesFromDirs.id -> {
                startProcess(
                    Menu.DeleteFilesFromDirs,
                    "Процесс удаления файлов с названием времени публикации завершен"
                ) {
                    deleteFilesFromDirs()
                }
            }

            Menu.EditFilesTxt.id -> {
                startProcess(Menu.EditFilesTxt, "Процесс замены одной строки текста на другую завершен") {
                    editFilesTxt()
                }
            }

            Menu.StartFormatPost.id -> {
                startProcess(Menu.StartFormatPost, "Процесс составления поста из названий рецептов завершен") {
                    startFormatPost()
                }
            }

            Menu.GetRecipeLinks.id -> {
                startProcess(Menu.GetRecipeLinks, "Процесс парсинга ссылок на страницы рецептов завершен") {
                    getRecipeLinks()
                }
            }

            Menu.ParseRecipeFromSite.id -> {
                startProcess(Menu.ParseRecipeFromSite, "Процесс парсинга рецептов по ссыкам на них завершен") {
                    parseRecipeFromSite()
                }
            }

            Menu.RenameFilesFromDirs.id -> {
                startProcess(Menu.RenameFilesFromDirs, "Процесс перебора файлов в папках завершен") {
                    renameFilesFromDirs()
                }
            }

            Menu.SelectAllTags.id -> {
                startProcess(
                    Menu.SelectAllTags,
                    "Процесс сохранения в файл всех уникальных тэгов из всех рецептов завершен"
                ) {
                    selectAllTags()
                }
            }

            Menu.Exit.id -> {
                isExit = exitApp()
            }

            else -> continue
        }
    }
}
