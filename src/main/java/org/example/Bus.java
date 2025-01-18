package org.example;

import java.util.Objects;

public class Bus implements Comparable<Bus> {
    private final String model;
    private final int number;
    private final double mileage;


    public Bus(Builder builder) {
        this.model = builder.model;
        this.number = builder.number;
        this.mileage = builder.mileage;
    }

    @Override
    public String toString() {
        return "Bus{" +
                "model='" + model + '\'' +
                ", number=" + number +
                ", mileage=" + mileage +
                '}';
    }

    @Override
    public int compareTo(Bus bus) {
        int compareByNumber = Integer.compare(this.number, bus.number);
        if (compareByNumber != 0) {
            return compareByNumber;
        }
        int compareByModel = this.model.compareTo(bus.model);
        if (compareByModel != 0) {
            return compareByModel;
        }
        return Double.compare(this.mileage, bus.mileage);
    }

    public String getModel() {
        return model;
    }

    public int getNumber() {
        return number;
    }

    public double getMileage() {
        return mileage;
    }

    public static class Builder {
        private String model;
        private int number;
        private double mileage;

        public Builder setModel(String model) {
            this.model = model;
            return this;
        }

        public Builder setNumber(int number) {
            this.number = number;
            return this;
        }

        public Builder setMileage(double mileage) {
            if (mileage < 0) {
                throw new IllegalArgumentException("Mileage меньше 0");
            }
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            Objects.requireNonNull(model, "Model cannot be null");
            if (number <= 0) {
                throw new IllegalArgumentException("Bus number must be positive"); // Выбрасываем исключение
            }
            return new Bus(this);
        }


    }
}

