package org.example.dto;

public class User implements Comparable<User> {
    private final String name;
    private final String password;
    private final String email;

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public User(Builder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
    }

    public static class Builder {
        private String name;
        private String password;
        private String email;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public User build() {
            if (name == null || email == null || !email.contains("@")) {
                throw new IllegalArgumentException("Invalid user data");
            }
            return new User(this);
        }
    }

    @Override
    public int compareTo(User user) {
        if (this.email == null && user.email == null) {
            return this.name.compareToIgnoreCase(user.name);
        } else if (this.email == null) {
            return -1;
        } else if (user.email == null) {
            return 1;
        }
        int compareByEmail = this.email.compareToIgnoreCase(user.email);
        if (compareByEmail != 0) {
            return compareByEmail;
        }
        return this.name.compareToIgnoreCase(user.name);
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}