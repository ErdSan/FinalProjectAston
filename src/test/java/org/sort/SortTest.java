package org.sort;

import org.junit.jupiter.api.Test;
import org.example.Bus;



import static org.junit.jupiter.api.Assertions.*;

public class SortTest {

    @Test
    void testSelectionSort() {
        // Arrange
        SortStrategy<Bus> selectionSort = new SelectionSort<>();

        Bus[] buses = {
                new Bus.Builder().setNumber(101).setModel("Volvo").setMileage(50000).build(),
                new Bus.Builder().setNumber(103).setModel("Mercedes").setMileage(40000).build(),
                new Bus.Builder().setNumber(102).setModel("Scania").setMileage(60000).build()
        };

        // Act
        selectionSort.sort(buses);

        // Assert
        assertEquals(101, buses[0].getNumber());
        assertEquals(102, buses[1].getNumber());
        assertEquals(103, buses[2].getNumber());
    }

    @Test
    void testEvenOddSort() {
        // Arrange
        SortStrategy<Integer> evenOddSort = new EvenOddSort<>();

        Integer[] numbers = {5, 2, 7, 8, 3, 6, 1};

        // Act
        evenOddSort.sort(numbers);

        // Assert
        assertArrayEquals(new Integer[]{5, 2, 7, 6, 3, 8, 1}, numbers);
    }
}
