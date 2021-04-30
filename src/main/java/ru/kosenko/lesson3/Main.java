package ru.kosenko.lesson3;

import java.util.*;

public class Main {
    public static void main(String[] args){
        List<String> array = Arrays.asList(
                "Красная поляна", "Урал", "Туапсе", "Лазаревское", "Пятигорск", "Геленджик", "Адлер", "Кисловодск", "Анапа", "Геленджик", "Дагомыс", "Джубга",
                "Пятигорск", "Кисловодск", "Лазаревское", "Коктебель", "Красная поляна", "Абзаково", "Красная поляна", "Лазаревское", "Лазурное",
                "Лермонтово", "Геленджик", "Массандра", "Туапсе", "Лазаревское", "Приморский",
                "Приэльбрусье", "Туапсе", "Кисловодск", "Красная поляна", "Анапа", "Пятигорск", "Лазаревское", "Урал", "Геленджик"
        );

        Set<String> unicArray = new HashSet(array);
        System.out.printf("\nНаш массив: \n%s \n", array);
        System.out.printf("\nУникальные слова: \n%s \n", unicArray);
        System.out.println("\nЧастота встречаемости слов:");
        for (String kode : unicArray) {
            System.out.println(kode + ": " + Collections.frequency(array, kode));
        }
    }
}
