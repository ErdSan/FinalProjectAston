package org.example;

import org.example.dto.Bus;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BusTest {

    @Test
    void testBusCreation() {
        Bus bus = new Bus.Builder()
                .setModel("Mercedes-Benz")
                .setNumber(123)
                .setMileage(100000)
                .build();
        assertNotNull(bus);
        assertEquals("Mercedes-Benz", bus.getModel());
        assertEquals(123, bus.getNumber());
        assertEquals(100000, bus.getMileage());
    }

    @Test
    void testBusCreationWithInvalidMileage() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bus.Builder()
                    .setModel("Volvo")
                    .setNumber(456)
                    .setMileage(-100) // отрицательный пробег
                    .build();
        });
    }

    @Test
    void testBusCreationWithInvalidNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Bus.Builder()
                    .setModel("Scania")
                    .setNumber(0)
                    .setMileage(50000)
                    .build();
        });
    }

    @Test
    void testBusComparison() {
        Bus bus1 = new Bus.Builder().setModel("A").setNumber(1).setMileage(100).build();
        Bus bus2 = new Bus.Builder().setModel("B").setNumber(1).setMileage(200).build();
        Bus bus3 = new Bus.Builder().setModel("A").setNumber(2).setMileage(100).build();

        assertTrue(bus1.compareTo(bus2) < 0); // сравнение по модели
        assertTrue(bus2.compareTo(bus3) < 0); // сравнение по номеру
        assertTrue(bus1.compareTo(bus3) < 0); // сравнение по номеру
    }

    @Test
    void testBusToString() {
        Bus bus = new Bus.Builder()
                .setModel("MAN")
                .setNumber(789)
                .setMileage(250000)
                .build();

        //Проверка на null, перед выводом в консоль
        assertNotNull(bus, "Bus object should not be null");
        assertEquals("Bus{model='MAN', number=789, mileage=250000.0}", bus.toString());
    }
}