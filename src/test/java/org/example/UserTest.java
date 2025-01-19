package org.example;

import org.example.dto.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class UserTest {

    @Test
    void testValidUserCreation() {
        User user = new User.Builder()
                .setName("John Doe")
                .setPassword("password123")
                .setEmail("john.doe@example.com")
                .build();
        assertNotNull(user);
        assertEquals("John Doe", user.getName());
        assertEquals("password123", user.getPassword());
        assertEquals("john.doe@example.com", user.getEmail());
    }

    @Test
    void testInvalidEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                new User.Builder()
                        .setName("Jane Doe")
                        .setPassword("securePassword")
                        .setEmail("invalid email")
                        .build());
    }

    @Test
    void testNullName() {
        assertThrows(IllegalArgumentException.class, () ->
                new User.Builder()
                        .setPassword("anotherPassword")
                        .setEmail("jane.doe@example.com")
                        .build());
    }

    @Test
    void testNullEmail() {
        assertThrows(IllegalArgumentException.class, () ->
                new User.Builder()
                        .setName("Peter Pan")
                        .setPassword("peterPassword")
                        .build());
    }

    @Test
    void testCompareTo_DifferentEmails() {
        User user1 = new User.Builder().setName("Alice").setEmail("alice@example.com").build();
        User user2 = new User.Builder().setName("Bob").setEmail("bob@example.com").build();
        assertTrue(user1.compareTo(user2) < 0);
    }

    @Test
    void testCompareTo_SameEmailsDifferentNames() {
        User user1 = new User.Builder().setName("Alice").setEmail("alice@example.com").build();
        User user2 = new User.Builder().setName("Bob").setEmail("alice@example.com").build();
        assertTrue(user1.compareTo(user2) < 0);
    }

    @Test
    void testCompareTo_SameUsers() {
        User user1 = new User.Builder().setName("Alice").setEmail("alice@example.com").build();
        User user2 = new User.Builder().setName("Alice").setEmail("alice@example.com").build();
        assertEquals(0, user1.compareTo(user2));
    }


    @Test
    void testCompareTo_BothNullEmails() {
        User user1 = new User.Builder()
                .setName("Alice")
                .setEmail("alice@example.com")
                .build();

        User user2 = new User.Builder()
                .setName("Bob")
                .setEmail("bob@example.com")
                .build();

        assertTrue(user1.compareTo(user2) < 0);
    }

    @Test
    void testToString() {
        User user = new User.Builder()
                .setName("Test User")
                .setPassword("testPassword")
                .setEmail("test@user.com")
                .build();
        assertEquals("User{name='Test User', password='testPassword', email='test@user.com'}", user.toString());
    }
}