package org.example.BinarySearch;

/*
*   Реализация бинарного поиска
*/

public class BinarySearch<T extends Comparable<T>> {
    public int search(T[] array, T key) {
        int low = 0;
        int high = array.length - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int comparison = array[mid].compareTo(key);
            if (comparison == 0) {
                printFoundMessage(array[mid], mid);
                return mid;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        printNotFoundMessage(key);
        return -1;
    }


    /*
     *   Блок для вывода сообщения, что элемент найден и находиться на таком-то индексе
    */

    private void printFoundMessage(T element, int index) {
        if (element instanceof Bus) {
            System.out.println("Автобус найден: " + element + " на индексе " + index);
        } else if (element instanceof Student) {
            System.out.println("Студент найден: " + element + " на индексе " + index);
        } else if (element instanceof User) {
            System.out.println("Пользователь найден: " + element + " на индексе " + index);
        } else {
            System.out.println("Элемент найден: " + element + " на индексе " + index);
        }
    }

    /*
     *   Блок для вывода сообщения, что элемент не найден
     */

    private void printNotFoundMessage(T key) {
        if (key instanceof Bus) {
            System.out.println("Автобус не найден.");
        } else if (key instanceof Student) {
            System.out.println("Студент не найден.");
        } else if (key instanceof User) {
            System.out.println("Пользователь не найден.");
        } else {
            System.out.println("Элемент не найден.");
        }
    }
}

