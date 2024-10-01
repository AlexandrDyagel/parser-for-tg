import java.io.File
import java.time.Month
import kotlin.random.Random

fun generatePostingDate() {

    println("TG или VK?")
    val place = readln().lowercase().trim()

    println("Путь к директории папок с файлами постов:")
    val path = readln()
    //val path = "E:\\РЕЦЕПТЫ\\На публикацию\\tg"

    println("Количество постов в день:")
    val nPost = readln().toInt()
    //val nPost = 3

    println("Дата начала постинга формата 11 06 2023(день месяц год)")
    val startDate = readln()
    //val startDate = "11 6 2023"

    val date = startDate.split(" ")
    var day = date[0].toInt()
    var month = date[1].toInt()
    var year = date[2].toInt()

    val formatString = "Время (час) %s поста:"
    var hours = mutableListOf<Int>()
    for (i in 1..nPost) {
        when (i) {
            1 -> println(String.format(formatString, "первого"))
            2 -> println(String.format(formatString, "второго"))
            3 -> println(String.format(formatString, "третьего"))
            4 -> println(String.format(formatString, "четвертого"))
            5 -> println(String.format(formatString, "пятого"))
            6 -> println(String.format(formatString, "шестого"))
            7 -> println(String.format(formatString, "седьмого"))
        }
        hours.add(readln().toInt())
    }

    val dirs = File(path).listFiles()
    dirs?.let { directories ->
        val dirsList = mutableListOf<List<String>>()
        val tempList = mutableListOf<String>()
        directories.sortBy { it.name.toInt() }

        if (directories.size < hours.size) {
            dirsList.add(directories.map { it.path })
        } else {
            directories.forEachIndexed { index, dir ->
                if ((index + 1) % hours.size != 0) {
                    tempList.add(dir.path)
                } else {
                    tempList.add(dir.path)
                    dirsList.add(ArrayList(tempList))
                    tempList.clear()
                }
            }
        }

        if (directories.size < hours.size) {
            hours = hours.subList(0, directories.size)
        }

        dirsList.forEach { paths ->
            hours.forEachIndexed { index, hour ->
                File(
                    "${paths[index]}${File.separator}${
                        if (place == "tg") randomizeTg(hour, day, month, year) else randomizeVk(hour, day, month, year)
                    }.txt"
                ).createNewFile()
            }
            val daysInMonth = daysInMonth(month, year)
            if (day < daysInMonth) {
                day++
            } else {
                if (month < Month.values().size) {
                    month++
                    day = 1
                } else {
                    year++
                    month = Month.JANUARY.value
                    day = 1
                }
            }
        }
    }
}

fun daysInMonth(month: Int, year: Int) = when (month) {
    Month.JANUARY.value, Month.MARCH.value, Month.MAY.value, Month.JULY.value, Month.AUGUST.value, Month.OCTOBER.value, Month.DECEMBER.value -> 31
    Month.APRIL.value, Month.JUNE.value, Month.SEPTEMBER.value, Month.NOVEMBER.value -> 30
    Month.FEBRUARY.value -> if (year % 4 == 0) 29 else 28
    else -> 0
}

    //TODO тут изменял формат даты (убрал пробел между day и month. Вернул пробел обратно)
/*fun randomizeTg(hour: Int, day: Int, month: Int, year: Int) =
    "$hour ${Random.nextInt(1, 60)} $day $month $year"*/

fun randomizeTg(hour: Int, day: Int, month: Int, year: Int) =
    "${hour}ч ${Random.nextInt(1, 60)}м - $day ${sortMonth(month)} $year"

fun sortMonth(month: Int) = when (month) {
    1 -> "янв"
    2 -> "фев"
    3 -> "мар"
    4 -> "апр"
    5 -> "мая"
    6 -> "июн"
    7 -> "июл"
    8 -> "авг"
    9 -> "сен"
    10 -> "окт"
    11 -> "ноя"
    12 -> "дек"
    else -> ""
}

fun randomizeVk(hour: Int, day: Int, month: Int, year: Int): String {
    val stringMonth = when (month) {
        1 -> "Января"
        2 -> "Февраля"
        3 -> "Марта"
        4 -> "Апреля"
        5 -> "Мая"
        6 -> "Июня"
        7 -> "Июля"
        8 -> "Августа"
        9 -> "Сентября"
        10 -> "Октября"
        11 -> "Ноября"
        12 -> "Декабря"
        else -> ""
    }

    return "$day $stringMonth $year в ${addZeroToDate(hour)}.${addZeroToDate(Random.nextInt(1, 60))}"
}

fun addZeroToDate(number: Int) = if (number < 10) "0$number" else "$number"