package org.example;

import org.example.dto.Student;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StudentTest {

    @Test
    void testValidStudentCreation() {
        Student student = new Student.Builder()
                .setNumberGroup(1)
                .setAverageScore(4.0)
                .setBookNumber(1234567890L)
                .build();

        assertEquals(1, student.getNumberGroup());
        assertEquals(4.0, student.getAverageScore());
        assertEquals(1234567890L, student.getBookNumber());
    }


    @Test
    void testInvalidNumberGroup() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Student.Builder().setNumberGroup(0).setAverageScore(4.0).setBookNumber(1234567890L).build();
        });
    }

    @Test
    void testInvalidAverageScore() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Student.Builder().setNumberGroup(1).setAverageScore(6.0).setBookNumber(1234567890L).build();
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new Student.Builder().setNumberGroup(1).setAverageScore(-1.0).setBookNumber(1234567890L).build();
        });
    }


    @Test
    void testInvalidBookNumber() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Student.Builder().setNumberGroup(1).setAverageScore(4.0).setBookNumber(0L).build();
        });
    }

    @Test
    void testCompareTo_SameGroupDifferentScore() {
        Student student1 = new Student.Builder().setNumberGroup(1).setAverageScore(4.0).setBookNumber(1L).build();
        Student student2 = new Student.Builder().setNumberGroup(1).setAverageScore(3.5).setBookNumber(2L).build();

        assertTrue(student1.compareTo(student2) > 0);
        assertTrue(student2.compareTo(student1) < 0);

    }


    @Test
    void testCompareTo_DifferentGroupSameScore() {
        Student student1 = new Student.Builder().setNumberGroup(2).setAverageScore(4.0).setBookNumber(1L).build();
        Student student2 = new Student.Builder().setNumberGroup(1).setAverageScore(4.0).setBookNumber(2L).build();

        assertTrue(student1.compareTo(student2) > 0);
        assertTrue(student2.compareTo(student1) < 0);
    }


    @Test
    void testCompareTo_SameStudent() {
        Student student1 = new Student.Builder().setNumberGroup(1).setAverageScore(4.0).setBookNumber(1L).build();
        Student student2 = new Student.Builder().setNumberGroup(1).setAverageScore(4.0).setBookNumber(1L).build();

        assertEquals(0, student1.compareTo(student2));
    }
}
