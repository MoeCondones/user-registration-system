# User Registration System

A Java-based user registration system with comprehensive testing and custom exception handling.

## Features

- User registration with validation
- Login functionality with attempt limiting
- UK address validation
- Custom exception handling
- Comprehensive test suite

## Requirements

- Java 8 or higher
- No external dependencies required

## Project Structure

```
src/
├── qa/                    # Main source code
│   ├── User.java         # User class with validation
│   ├── Address.java      # UK address validation
│   ├── UserAccountManager.java  # User management logic
│   ├── IUserAccountDb.java      # Database interface
│   ├── UserAccountDBStub.java   # In-memory database implementation
│   ├── Main.java         # Main application entry point
│   └── Utils.java        # Utility functions
└── test/
    └── java/
        └── qa/           # Test classes
            └── AllTests.java  # Comprehensive test suite
```

## Custom Exceptions

The project implements several custom exceptions for better error handling:

- `InvalidUserDetailsException`
  - `InvalidUsernameException`
  - `InvalidPasswordException`
  - `UserAlreadyExistsException`
- `InvalidUKAddressException`
  - `InvalidHouseNumberException`
  - `InvalidPostcodeException`
  - `InvalidCityException`
- `LoginException`
- `TooManyLoginTriesException`

## Building and Running

1. Compile the source code:
```bash
javac -d build src/qa/*.java
```

2. Compile the tests:
```bash
javac -d build -cp build src/test/java/qa/AllTests.java
```

3. Run the main application:
```bash
java -cp build qa.Main
```

4. Run the test suite:
```bash
java -cp build test.java.qa.AllTests
```

## Testing

The project includes comprehensive tests for:
- User validation (username, password)
- Address validation (house number, postcode, city)
- Login functionality
- Exception handling
- Edge cases and error conditions

## License

This project is open source and available under the MIT License. 