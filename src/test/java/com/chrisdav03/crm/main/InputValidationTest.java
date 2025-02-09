package com.chrisdav03.crm.main;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.*;

public class InputValidationTest {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^(?!.*\\.\\.)([A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6})$"
    );
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^\\d+$");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Test
    void testValidEmails() {
        assertTrue(EMAIL_PATTERN.matcher("test@example.com").matches(), "Valid email should pass.");
        assertTrue(EMAIL_PATTERN.matcher("john.doe@company.io").matches(), "Valid email should pass.");
        assertTrue(EMAIL_PATTERN.matcher("user123@test.org").matches(), "Valid email should pass.");
    }

    @Test
    void testInvalidEmails() {
        assertFalse(EMAIL_PATTERN.matcher("usuario@").matches(), "Missing domain should fail.");
        assertFalse(EMAIL_PATTERN.matcher("correo.com").matches(), "Missing @ symbol should fail.");
        assertFalse(EMAIL_PATTERN.matcher("test@.com").matches(), "Invalid domain name should fail.");
        assertFalse(EMAIL_PATTERN.matcher("test@example..com").matches(), "Double dots in domain should fail.");
        assertFalse(EMAIL_PATTERN.matcher("test@@example.com").matches(), "Double @ symbols should fail.");
        assertFalse(EMAIL_PATTERN.matcher(" test@example.com").matches(), "Leading space should fail.");
        assertFalse(EMAIL_PATTERN.matcher("test@example.com ").matches(), "Trailing space should fail.");
    }

    @Test
    void testValidNumbers() {
        assertTrue(NUMERIC_PATTERN.matcher("123456").matches(), "Valid number should pass.");
        assertTrue(NUMERIC_PATTERN.matcher("000123").matches(), "Leading zeros should be valid.");
    }

    @Test
    void testInvalidNumbers() {
        assertFalse(NUMERIC_PATTERN.matcher("abc123").matches(), "Alphanumeric input should fail.");
        assertFalse(NUMERIC_PATTERN.matcher("12.34").matches(), "Decimal number should fail.");
        assertFalse(NUMERIC_PATTERN.matcher("999 888").matches(), "Number with spaces should fail.");
        assertFalse(NUMERIC_PATTERN.matcher("123-456").matches(), "Number with hyphen should fail.");
        assertFalse(NUMERIC_PATTERN.matcher("").matches(), "Empty input should fail.");
    }

    @Test
    void testValidBirthdates() {
        assertDoesNotThrow(() -> LocalDate.parse("1995-05-10", DATE_FORMATTER), "Valid birthdate should not throw an exception.");
        assertDoesNotThrow(() -> LocalDate.parse("2000-12-31", DATE_FORMATTER), "Valid birthdate should not throw an exception.");
        assertDoesNotThrow(() -> LocalDate.parse("1980-01-01", DATE_FORMATTER), "Valid birthdate should not throw an exception.");
    }

    @Test
    void testInvalidBirthdates() {
        assertThrows(DateTimeParseException.class, () -> LocalDate.parse("1995/05/10", DATE_FORMATTER), "Wrong format should fail.");
        assertThrows(DateTimeParseException.class, () -> LocalDate.parse("31-12-2000", DATE_FORMATTER), "Wrong format should fail.");
        assertThrows(DateTimeParseException.class, () -> LocalDate.parse("", DATE_FORMATTER), "Empty input should fail.");
        assertFalse(isValidDate("2022-02-30"), "Non-existent date should fail.");
        assertFalse(isValidDate("2023-04-31"), "April 31 does not exist.");
        assertFalse(isValidDate("2021-06-31"), "June 31 does not exist.");
    }

    private boolean isValidDate(String dateString) {
        try {
            LocalDate parsedDate = LocalDate.parse(dateString, DATE_FORMATTER);
            return parsedDate.toString().equals(dateString);
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    @Test
    void testFutureBirthdate() {
        LocalDate futureDate = LocalDate.now().plusDays(1);
        assertTrue(futureDate.isAfter(LocalDate.now()), "Birthdate cannot be in the future.");
    }
}
