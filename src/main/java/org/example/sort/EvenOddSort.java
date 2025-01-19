package org.example.sort;

// Реализация сортировки для четных и нечетных значений
public class EvenOddSort<T extends Comparable<T>> implements SortStrategy<T> {

    /**
     * Сортирует массив таким образом, что элементы с четными значениями сортируются
     * в натуральном порядке, а нечетные остаются на своих местах.
     *
     * @param array массив, который нужно отсортировать.
     */
    @Override
    public void sort(T[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            if (isEven(array[i])) { // Проверяем, является ли значение четным
                int minIndex = i;

                // Находим минимальный четный элемент в оставшейся части массива
                for (int j = i + 1; j < n; j++) {
                    if (isEven(array[j]) && array[j].compareTo(array[minIndex]) < 0) {
                        minIndex = j;
                    }
                }

                // Меняем местами элементы
                if (minIndex != i) {
                    T temp = array[i];
                    array[i] = array[minIndex];
                    array[minIndex] = temp;
                }
            }
        }
    }

    /**
     * Проверяет, является ли значение четным.
     *
     * @param value значение для проверки.
     * @return true, если значение четное; иначе false.
     */
    private boolean isEven(T value) {
        if (value instanceof Integer) {
            return (Integer) value % 2 == 0;
        }
        throw new IllegalArgumentException("Поддерживаются только объекты Integer.");
    }
}
