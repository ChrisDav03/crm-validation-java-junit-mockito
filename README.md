# CRM Validation with Java, JUnit, and Mockito

This project is a Customer Relationship Management (CRM) system developed in Java, featuring validation mechanisms and unit testing using JUnit and Mockito.

## Project Structure

```
crm-validation-java-junit-mockito/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/chrisdav03/crm/
│   │           ├── domain/
│   │           │   ├── model/
│   │           │   ├── service/
│   │           ├── application/
│   │           ├── main/
│   └── test/
│       └── java/
│           └── com/chrisdav03/crm/
│               ├── domain/
│               │   ├── service/
├── .gitattributes
├── .gitignore
├── pom.xml
└── README.md
```

- **src/main/java/com/chrisdav03/crm/domain/model/**: Contains entity classes such as `Lead`, `Prospect`, etc.
- **src/main/java/com/chrisdav03/crm/domain/service/**: Includes service classes implementing business logic and validation.
- **src/main/java/com/chrisdav03/crm/application/**: Provides external services for validation like `NationalRegistryService`.
- **src/main/java/com/chrisdav03/crm/main/**: The entry point of the application (`Main.java`).

## Prerequisites

- **Java 8 or higher**: Ensure you have Java Development Kit (JDK) 8 or a later version installed.
- **Maven**: Used for dependency management and project build.

## Installation

1. **Clone the repository**:

   ```bash
   git clone https://github.com/ChrisDav03/crm-validation-java-junit-mockito.git
   cd crm-validation-java-junit-mockito
   ```

2. **Build the project with Maven**:

   ```bash
   mvn clean install
   ```

   This command will download the necessary dependencies and compile the project.

## Running Tests

To execute the unit tests, run the following Maven command:

```bash
mvn test
```

This will run all tests located in `src/test/java/`.

## Using Mockito for Unit Testing

This project leverages **Mockito** for creating mock objects in unit tests. Mockito is a popular framework for Java-based testing. For more details, visit the [Mockito GitHub repository](https://github.com/mockito/mockito).



## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

