package com.chrisdav03.crm.main;

import com.chrisdav03.crm.domain.model.Lead;
import com.chrisdav03.crm.domain.model.Prospect;
import com.chrisdav03.crm.domain.service.LeadValidationUseCase;
import com.chrisdav03.crm.application.NationalRegistryService;
import com.chrisdav03.crm.application.JudicialRecordsService;
import com.chrisdav03.crm.application.ProspectScoringService;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Main {
    private static final Pattern EMAIL_PATTERN = Pattern.compile(
            "^(?!.*\\.\\.)([A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6})$"
    ); // Improved regex to prevent double dots
    private static final Pattern NUMERIC_PATTERN = Pattern.compile("^\\d+$");
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        LeadValidationUseCase validationService = new LeadValidationUseCase(
                new NationalRegistryService(),
                new JudicialRecordsService(),
                new ProspectScoringService()
        );

        Scanner scanner = new Scanner(System.in);
        boolean continueProcess = true;

        while (continueProcess) {
            System.out.println("\n===== CRM - Lead Validation =====");

            String id = requestInput(scanner, "Enter Lead ID (numbers only):", NUMERIC_PATTERN);
            String name = requestText(scanner, "Enter Name:");
            String nationalId = requestInput(scanner, "Enter National ID (numbers only):", NUMERIC_PATTERN);
            String email = requestInput(scanner, "Enter Email (valid format):", EMAIL_PATTERN);
            LocalDate birthDate = requestBirthDate(scanner, "Enter Birthdate (YYYY-MM-DD):");

            Lead lead = new Lead(id, name, nationalId, email, birthDate);
            Prospect prospect = validationService.validateAndConvertLead(lead);

            if (prospect != null) {
                System.out.println("Prospect generated: " + prospect);
            } else {
                System.out.println("The Lead did not qualify as a Prospect.");
            }

            System.out.println("\nDo you want to try with another Lead? (Y/N):");
            String response = scanner.nextLine().trim().toLowerCase();
            if (!response.equals("y")) {
                continueProcess = false;
            }
        }

        System.out.println("Exiting CRM...");
        scanner.close();
    }

    private static String requestInput(Scanner scanner, String message, Pattern pattern) {
        String input;
        while (true) {
            System.out.println(message);
            input = scanner.nextLine().trim();
            if (pattern.matcher(input).matches()) {
                return input;
            }
            System.out.println("Invalid input. Please try again.");
        }
    }

    private static String requestText(Scanner scanner, String message) {
        System.out.println(message);
        return scanner.nextLine().trim();
    }

    private static LocalDate requestBirthDate(Scanner scanner, String message) {
        while (true) {
            System.out.println(message);
            String input = scanner.nextLine().trim();
            try {
                LocalDate birthDate = LocalDate.parse(input, DATE_FORMATTER);
                if (birthDate.isAfter(LocalDate.now())) {
                    System.out.println("Birthdate cannot be in the future. Please enter a valid date.");
                } else if (!isValidDate(input)) {
                    System.out.println("Invalid date. Please enter a real date (e.g., not February 30).");
                } else {
                    return birthDate;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter a date in YYYY-MM-DD format.");
            }
        }
    }

    private static boolean isValidDate(String dateString) {
        try {
            LocalDate parsedDate = LocalDate.parse(dateString, DATE_FORMATTER);
            return parsedDate.toString().equals(dateString);
        } catch (DateTimeParseException e) {
            return false;
        }
    }
}
