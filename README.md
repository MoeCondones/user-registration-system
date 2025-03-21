# User Registration System - Unit Testing

This project demonstrates unit testing of a Java-based user registration system. It addresses key knowledge and performance criteria for software testing.

## Project Overview

The project consists of a user registration system with the following components:
- User management (registration and login)
- UK address validation
- Custom exception handling

## Testing Approach

### Knowledge Criteria Addressed:
- **K1**: Tests validate that the user registration and login functionality perform as specified
- **K2**: Tests follow the testing lifecycle within the software development project
- **K3**: Tests serve as both validation and design improvement
- **K5**: Tests underpin the development process
- **K6**: Appropriate testing strategy and techniques are applied
- **K7**: Test planning is evident in the organization of test classes
- **K8**: Test cases and scripts with test data are designed
- **K9**: Unit testing is implemented for individual components
- **K12**: Representative test data is used, including deliberately wrong data
- **K15**: Tests follow standards related to software testing
- **K16**: Tests verify results against expected outcomes
- **K17**: Unit testing is implemented at each stage of development

### Performance Criteria Addressed:
- **P1**: Requirements analyzed to determine appropriate testing strategies
- **P2**: Test plans designed and implemented
- **P5**: Various testing contexts implemented (unit testing)
- **P6**: Industry standard testing techniques applied
- **P7**: Suitable test data produced to cover possible and impossible inputs
- **P9**: Bugs identified and fixed during testing
- **P10**: Software quality assurance implemented
- **P11**: Test results analyzed and reported

## Custom Exceptions
The project uses custom exceptions for better error handling:
- `InvalidUserDetailsException`
- `InvalidUKAddressException`
- `LoginException`
- `TooManyLoginTriesException`

## Mocking the Database
The tests use Mockito to mock the database interactions, demonstrating how to test code without relying on external dependencies. The latest version of Mockito (5.8.0) is used.

## Test Framework
The project uses JUnit 5 (Jupiter) as the testing framework. JUnit 5 offers several advantages over JUnit 4:
- Better support for Java 8 and above
- More powerful assertions
- Better parameterized test support
- Extension model instead of runners
- Better nesting and organization of tests

## Test Coverage
The tests cover:
1. User registration functionality
   - Successful registration
   - Validation of usernames and passwords
   - Handling duplicate username registration
2. Login functionality
   - Successful login
   - Failed login with invalid credentials
   - Account lockout after multiple failed attempts
3. Address validation
   - UK address format validation
   - City validation

## How to Run the Tests

### Using Maven (Recommended)
This project uses Maven for dependency management and build automation.

#### Installing Maven
If you don't have Maven installed yet:
```
# On macOS with Homebrew
brew install maven

# On Ubuntu/Debian
sudo apt install maven

# On Windows
# Download from https://maven.apache.org/download.cgi and follow installation instructions
```

#### Running Tests with Maven
```
# Compile and run tests
mvn clean test

# Generate test coverage report with JaCoCo
mvn jacoco:report
# The report will be available at target/site/jacoco/index.html
```

### Using an IDE
To run the tests in any Java IDE (Eclipse, IntelliJ IDEA, etc.):
1. Import the project as a Maven project
2. Right-click on `UserAccountManagerTest.java` and select "Run as JUnit Test"

## License
This project is open source and available under the MIT License. 