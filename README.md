# test-automation

# Doping Teknoloji - Student Exam Service

## Project Summary

This project is a student exam service designed for Doping Teknoloji's challenge. It enables students to take tests,
manage their performance, and view their results. The project is built using **Spring Boot** with **Java 17** and
includes H2 in-memory database for storing student and test-related information. The service follows RESTful principles
and incorporates caching mechanisms to optimize performance.

## Technologies Used

- **Java 17**: The core programming language used.
- **Spring Boot**: For creating the RESTful web services.
- **Spring Data JPA**: For database interactions.
- **H2 In-Memory Database**: Used for storing the data during runtime.
- **JUnit**: For writing unit tests.
- **Spring Cache**: For caching to optimize performance.

## Project Structure

```plaintext
src/
├── main/
│   └── java/
│       └── com.doping.demo/
│           ├── controller/
│           │   ├── ExamController
│           │   ├── QuestionController
│           │   └── StudentController
│           ├── service/
│           │   ├── ExamService
│           │   ├── QuestionService
│           │   └── StudentService
│           ├── repository/
│           │   ├── ExamRepository
│           │   ├── QuestionRepository
│           │   └── StudentRepository
│           ├── entity/
│           │   ├── Exam
│           │   ├── Question
│           │   └── Student
│           ├── dto/
│           │   ├── ExamResponse
│           │   ├── QuestionResponse
│           │   └── StudentResponse
│           └── DopingApplication
├── test/
│   └── java/
│       └── com.doping.demo/
│           └── service/
│               ├── ExamServiceImplTest
│               ├── QuestionServiceImplTest
│               └── StudentServiceImplTest
