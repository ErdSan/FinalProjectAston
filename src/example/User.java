package org.example;

public class User  implements Comparable<User>{
   private final String name;
    private final String password;
    private final String email;
    private User(Builder builder) {
        this.name = builder.name;
        this.password = builder.password;
        this.email = builder.email;
    }

    public User build() {
        if (name == null || email == null || !email.contains("@")) {
            System.out.println("Ошибка");
            return null;
        }
        return new User(this);
    }

    @Override
    public int compareTo(User user) {
        int compareByName = this.name.compareTo(user.name);
        if (compareByName != 0){
            return compareByName;
        }
        return this.email.compareTo(user.email);
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
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
