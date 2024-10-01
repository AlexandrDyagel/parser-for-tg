enum class Menu(val id: Int, val text: String) {
    GeneratePostingDate(1,"Генерация даты публикации постов"),
    InsertNewRow(2,"Добавление новой строки после заголовка поста"),
    DeleteFilesFromDirs(3,"Удаление файлов с названием времени публикации (которые пустые внутри)"),
    EditFilesTxt(4,"Заменяет одну строку текста на другую"),
    StartFormatPost(5,"Составление поста из названий рецептов"),
    GetRecipeLinks(6,"Парсинг ссылок на страницы рецептов"),
    ParseRecipeFromSite(7,"Парсинг рецептов по ссыкам на них"),
    RenameFilesFromDirs(8,"Перебор файлов в папках, можно все что угодно делать, переименовывать файлы"),
    SelectAllTags(9,"Сохраняет в файл все уникальные тэги из всех рецептов"),
    Exit(10,"Выход из программы");
}