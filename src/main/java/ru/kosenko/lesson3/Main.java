package ru.kosenko.lesson3;

import java.util.*;

public class Main {
    public static void main (String[] args) {

        // 1. Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
        //   Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
        //   Посчитать сколько раз встречается каждое слово.
        // 2. Написать простой класс ТелефонныйСправочник, который хранит в себе список фамилий и телефонных номеров.
        //   В этот телефонный справочник с помощью метода add() можно добавлять записи.
        //   С помощью метода get() искать номер телефона по фамилии.
        //   Следует учесть, что под одной фамилией может быть несколько телефонов (в случае однофамильцев),
        //   тогда при запросе такой фамилии должны выводиться все телефоны.

        //   Желательно как можно меньше добавлять своего, чего нет в задании (т.е. не надо в телефонную запись
        //   добавлять еще дополнительные поля (имя, отчество, адрес), делать взаимодействие с пользователем
        //   через консоль и т.д.). Консоль желательно не использовать (в том числе Scanner), тестировать просто
        //   из метода main() прописывая add() и get().

        //  Задание №1:

        List <String> array = Arrays.asList (
                "Красная поляна", "Урал", "Туапсе", "Лазаревское", "Пятигорск", "Геленджик", "Адлер", "Кисловодск", "Анапа", "Геленджик", "Дагомыс", "Джубга",
                "Пятигорск", "Кисловодск", "Лазаревское", "Коктебель", "Красная поляна", "Абзаково", "Красная поляна", "Лазаревское", "Лазурное",
                "Лермонтово", "Геленджик", "Массандра", "Туапсе", "Лазаревское", "Приморский",
                "Приэльбрусье", "Туапсе", "Кисловодск", "Красная поляна", "Анапа", "Пятигорск", "Лазаревское", "Урал", "Геленджик"
        );

        Set <String> unicArray = new HashSet (array);
        System.out.printf ("\nНаш массив: \n%s \n", array);
        System.out.printf ("\nУникальные слова: \n%s \n", unicArray);
        System.out.println ("\nКоличество встречающихся слов:");
        for (String kode : unicArray) {
            System.out.println (kode + ": " + Collections.frequency (array, kode));
        }

//######################################################################################

       //  Задание №2:

        System.out.println ("\nЗадание 2:\nСоздаем телефонный справочник:\n");
        Phonebook phonebook = new Phonebook ();

        System.out.println ("Добавим номера:");
        phonebook.add ("Иванов", "+7(922) 333 44 44");
        phonebook.add ("Иванов", "+7(999) 333 44 44");
        phonebook.add ("Петров", "8(999) 000 11 44");
        phonebook.add ("Сидоров", "+7(999) 099 00 11");
        phonebook.add ("Иванов", "+1 999 343 33 33");

        System.out.println ("Получаем номера");
        System.out.printf ("Иванов %s\n", phonebook.get ("Иванов"));
        System.out.printf ("Петров %s\n", phonebook.get ("Петров"));
        System.out.printf ("Сидоров %s\n", phonebook.get ("Сидоров"));

        System.out.println ("Отсутствие записи: ");
        System.out.printf ("Некрасов %s", phonebook.get ("Некрасов"));


        System.out.println ("\nЗапись занятого номера:");
        phonebook.add ("Иванов", "+1 999 343 33 33");
        System.out.println (phonebook.get ("Иванов"));


    }
}

//-----------------------------------------------------------

    class Phonebook {
    private final HashMap < String, List <String> > ebook;
    public Phonebook () {
        this.ebook = new HashMap <> ();
    }

//------------------------------------------------------------

    public void add (String Lastname, String number) {
        if (ebook.containsKey (Lastname)) {
            List <String> numbers = ebook.get (Lastname);
            if (!numbers.contains (number)) {
                numbers.add (number);
                System.out.printf ("\n%s: добавлен ещё один номер %s\n", Lastname, number);
            } else {
                System.out.printf ("Номер %s уже существует: %s\n", number, Lastname);
            }
        } else {
            ebook.put (Lastname, new ArrayList <> (Collections.singletonList(number)));
            System.out.printf ("\nДобавлен номер: %s фамилия: %s\n", number, Lastname);
        }
    }

//------------------------------------------------------------------

    public List <String> get (String Lastname) {
        if (ebook.containsKey (Lastname)) {
            return ebook.get (Lastname);
        } else {
            System.out.printf ("Фамилии %s нет в справочнике\n", Lastname);
            return new ArrayList <> ();
        }
    }
}