import java.util.Arrays;

public class MultiThread {

    static final int size = 10000000;
    static final int h = size / 2;

    public static void main(String[] args) throws InterruptedException {

        float[] arr = new float[size];
        float[] arr1 = new float[size];
        Arrays.fill(arr, 1);
        Arrays.fill(arr1, 1);

        long a = System.currentTimeMillis();
        for (int i = 0; i < arr.length; i++) {
//            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));

//            System.out.printf("Делим i на 5: %s\n ", i / 5);
//            System.out.printf("Делим i на 5.00: %s\n ", i / 5.0);
//            System.out.printf("и делим на 5  : %s\n ", arr[i]);
//            System.out.printf("и делим на 5.0: %s\n ", (float)(arr[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0)));
        }
        long b = System.currentTimeMillis() - a;
        System.out.printf("Время выполнения: %s\n ", b);
        long a1 = System.currentTimeMillis();

            Thread thread = new Thread(() -> {
                for (int i = 1; i <= size; i+=2) {
                    arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
//                    try {
//                        Thread.sleep(500);
//
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                    System.out.println(i);
                }
            });
            thread.start();
        for (int i = 0; i <= size - 1; i+=2) {
            arr1[i] = (float)(arr1[i] * Math.sin(0.2f + i / 5.0) * Math.cos(0.2f + i / 5.0) * Math.cos(0.4f + i / 2.0));
        }
            thread.join();
        long b1 = System.currentTimeMillis() - a1;

        System.out.printf("Время выполнения в 2 потока: %s\n ", b1);

        System.out.println("END");
//        for (int i = 0; i < size; i++){
//            System.out.printf("массив 1: %s     массив 2: %s\n", arr[i], arr1[i]);
//        }
        thread.join();
        System.out.println(Arrays.equals(arr, arr1));
        }
    }



//        1. Необходимо написать два метода, которые делают следующее:
//
//        1) Создают одномерный длинный массив, например:
//        static final int size = 10000000;
//        static final int h = size / 2;
//        float[] arr = new float[size];
//        2) Заполняют этот массив единицами;
//        3) Засекают время выполнения: long a = System.currentTimeMillis();
//        4) Проходят по всему массиву и для каждой ячейки считают новое значение по формуле:
//        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//        5) Проверяется время окончания метода System.currentTimeMillis();
//        6) В консоль выводится время работы: System.out.println(System.currentTimeMillis() - a);
//
//        Отличие первого метода от второго:
//        Первый просто бежит по массиву и вычисляет значения.
//        Второй разбивает массив на два массива, в двух потоках высчитывает новые значения и потом склеивает эти массивы обратно в один.
//
//        Пример деления одного массива на два:
//
//        System.arraycopy(arr, 0, a1, 0, h);
//        System.arraycopy(arr, h, a2, 0, h);
//
//        Пример обратной склейки:
//
//        System.arraycopy(a1, 0, arr, 0, h);
//        System.arraycopy(a2, 0, arr, h, h);
//
//        Примечание:
//        System.arraycopy() – копирует данные из одного массива в другой:
//        System.arraycopy(массив-источник, откуда начинаем брать данные из массива-источника, массив-назначение, откуда начинаем записывать данные в массив-назначение, сколько ячеек копируем)
//        По замерам времени:
//        Для первого метода надо считать время только на цикл расчета:
//
//        for (int i = 0; i < size; i++) {
//        arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
//        }
//
//        Для второго метода замеряете время разбивки массива на 2, просчета каждого из двух массивов и склейки.
//
//        Не забудьте выполнить в конце Arrays.equals(arr1, arr)
