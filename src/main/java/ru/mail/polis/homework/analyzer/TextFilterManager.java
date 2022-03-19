package ru.mail.polis.homework.analyzer;

import java.util.Arrays;

/**
 * Задание написать систему фильтрации комментариев.
 * Надо реализовать три типа обязательных фильтров
 * 1) фильтр для слишком длинных текстов (длина задается при создании) (TOO_LONG)
 * 2) фильтр для спама (передается массив плохих слов, которых не должно быть в тексте) (SPAM)
 * 3) фильтр для текстов с плохими эмоциями. (в тексте не должно быть таких смайлов:
 * "=(", ":(", ":|" (NEGATIVE_TEXT)
 * + сделать любой свой фильтр (CUSTOM)
 * <p>
 * Класс TextFilterManager должен содержать все фильтры, которые передаются ему в конструкторе,
 * и при анализе текста через метод analyze должен выдавать первый "успешный" фильтр,
 * если ни один не прошел, то возвращать тип GOOD.
 *
 * Усложненное задание на полный балл: нужно всем типам фильтров задать приоритет
 * (SPAM, TOO_LONG, NEGATIVE_TEXT, CUSTOM - в таком порядке) и возвращать тип с максимальным приоритетом.
 * Отсортировать фильтра можно с помощью функции
 * Arrays.sort(filter, (filter1, filter2) -> {
 * if (filter1 < filter2) {
 * return -1;
 * } else if (filter1 == filter2) {
 * return 0;
 * }
 * return 1;
 * }
 * где вместо сравнения самих фильтров должно быть сравнение каких-то количественных параметров фильтра
 *
 * 2 тугрика за класс
 * 5 тугриков за приоритет
 * Итого 20 тугриков за все задание
 */
public class TextFilterManager {

    /**
     * Для работы с каждым элементом массива, нужно использовать цикл for-each
     * Хочется заметить, что тут мы ничего не знаем, какие конкретно нам объекты переданы, знаем только то,
     * что в них реализован интерфейс TextAnalyzer
     */
    private final TextAnalyzer[] analyzers;

    public TextFilterManager(TextAnalyzer[] filters) {
        this.analyzers = Arrays.copyOf(filters, filters.length);

    }

    /**
     * Если переменная текст никуда не ссылается, то это означает, что не один фильтр не сработал
     */
    public FilterType analyze(String text) {

        if (analyzers == null || analyzers.length == 0) {
            return FilterType.GOOD;
        }
        if (text == null || text.isEmpty()) {
            return FilterType.GOOD;
        }
        Arrays.sort(analyzers, (filter1, filter2) -> {
            if (filter1.FilterValue(text).priority < filter2.FilterValue(text).priority) {
                return -1;
            } else if (filter1.FilterValue(text).priority == filter2.FilterValue(text).priority) {
                return 0;
            }
            return 1;
        });
        for (TextAnalyzer analyzer : analyzers) {
            FilterType filterVal = analyzer.FilterValue(text);
            if (filterVal != FilterType.GOOD) {
                return filterVal;
            }
        }
        return FilterType.GOOD;
    }
}
