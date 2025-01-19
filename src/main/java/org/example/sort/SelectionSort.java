package org.example.sort;

// Реализация сортировки выбором
public class SelectionSort<T extends Comparable<T>> implements SortStrategy<T> {

    /**
     * Реализует сортировку выбором.
     *
     * @param array массив, который нужно отсортировать.
     */
    @Override
    public void sort(T[] array) {
        int n = array.length;

        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;

            // Находим индекс минимального элемента в неотсортированной части массива
            for (int j = i + 1; j < n; j++) {
                if (array[j].compareTo(array[minIndex]) < 0) {
                    minIndex = j;
                }
            }

            // Меняем местами текущий элемент с минимальным
            if (minIndex != i) {
                T temp = array[i];
                array[i] = array[minIndex];
                array[minIndex] = temp;
            }
        }
    }
}
