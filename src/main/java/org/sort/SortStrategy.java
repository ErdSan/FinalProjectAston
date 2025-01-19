package org.sort;

// Интерфейс стратегии сортировки
public interface SortStrategy<T> {
    void sort(T[] array); // Метод сортировки массива
}
