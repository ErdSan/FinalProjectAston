package org.example.dto;

public class Student implements Comparable<Student> {


    private final int numberGroup;
    private final double averageScore;
    private final long bookNumber;

    public Student(Builder builder) {
        this.numberGroup = builder.numberGroup;
        this.averageScore = builder.averageScore;
        this.bookNumber = builder.bookNumber;
    }

    public static class Builder {
        private int numberGroup;
        private double averageScore;
        private long bookNumber;


        public Builder setNumberGroup(int numberGroup) {
            this.numberGroup = numberGroup;
            return this;
        }

        public Builder setAverageScore(double averageScore) {
            if (averageScore < 0 || averageScore > 5) {
                throw new IllegalArgumentException("Должно быть от 0 до 5");
            }
            this.averageScore = averageScore;
            return this;
        }

        public Builder setBookNumber(long bookNumber) {
            if (bookNumber <= 0) {
                throw new IllegalArgumentException("Номер зачетной книжки должен быть положительным числом.");
            }
            this.bookNumber = bookNumber;
            return this;
        }

        public Student build() {
            if (numberGroup <= 0) {
                throw new IllegalArgumentException("Номер группы должен быть положительным");
            }
            return new Student(this);
        }
    }

    @Override
    public int compareTo(Student student) {
        int groupComparison = Integer.compare(this.numberGroup, student.numberGroup);
        if (groupComparison != 0) {
            return groupComparison;
        }
        return Double.compare(this.averageScore, student.averageScore);
    }

    @Override
    public java.lang.String toString() {
        return "Student{" +
                "numberGroup='" + numberGroup + '\'' +
                ", averageScore='" + averageScore + '\'' +
                ", bookNumber='" + bookNumber + '\'' +
                '}';
    }

    public int getNumberGroup() {
        return numberGroup;
    }

    public double getAverageScore() {
        return averageScore;
    }

    public long getBookNumber() {
        return bookNumber;
    }
}
