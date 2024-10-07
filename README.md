# Bookstore Documentation

Welcome to the documentation for Bookstore project! Here you can explore detailed information about the project, its
architecture, API, testing, and the technologies used.

## Project Overview

Bookstore is a server-side web application built using Spring Boot, Liquibase, Hibernate, PostgreSQL, and other
technologies. It provides an API for managing a book store, including operations related to books, authors, categories,
orders and reports.

## Documentation

Comprehensive and easy-to-understand documentation is available for the Bookstore project. It includes the following
sections:

1. **Technology Stack**: Information about frameworks, tools and libraries that the Bookstore project uses.
2. **Usage of Generics**. Key Information about using of generics in the Bookstore project.
3. **Architecture**: An overview of the application's architecture and key components.
4. **API Analysis/Documentation**: Key, core documentation of the example of available RESTful
   APIs. [More about APIs](http://localhost:8080/Store/api-doc)
5. **Code Analysis**: A breakdown of the project's code structure, organization, and key points.
6. **Testing**: Information about the testing approach and instructions for running the tests.

For more information, please refer to the project documentation in
the [GitHub repository](https://github.com/Arthur488/bookstore/tree/arthurs_branch).

## Technology Stack

The Bookstore application utilizes the following technologies and frameworks:

- **Spring Boot**: A Java framework used to create stand-alone, production-grade Spring-based applications.
- **Liquibase**: A database schema management tool that allows for easy tracking, versioning, and deployment of database
  changes.
- **Hibernate**: An object-relational mapping (ORM) framework that simplifies database access and manipulation.
- **PostgreSQL**: An open-source relational database management system used for data storage.
- **JUnit**: A unit testing framework for Java applications.
- **Mockito**: A mocking framework used for unit testing Java applications.
- **Lombok**: A library that helps reduce boilerplate code by automatically generating code such as getters, setters,
  and constructors.
- **Generics**: A feature of the Java programming language that allows the creation of reusable code components that can
  work with multiple types.

## Usage of Generics

Generics in the Bookstore project enhance code reusability and type safety. They allow the creation of classes,
interfaces, and methods that can operate on different types while providing compile-time type checks.

Here are some examples of how generics are used in the project:

1. **Repository and Service Interfaces**: Generics are used in repository and service interfaces to define methods that
   work with different entity types. For example, a generic repository interface may have a method
   like `findById(ID id)`, where `ID` is a type parameter representing the entity's identifier.

2. **Data Access Objects (DAOs)**: DAOs use generics to provide common CRUD operations for different entity types. For
   instance, a generic `BaseDao<T>` may have methods like `save(T entity)`, `update(T entity)`, and `delete(T entity)`.

3. **Service Classes**: Service classes use generics to define methods that operate on different entity types. For
   example, a generic service class may have a method like `findByID(ID id)` that can retrieve an entity by its
   identifier, regardless of the specific entity type.

4. **Rest Controllers**: Generics are also used in REST controllers to provide basic (CRUD) functionality for hairs. For
   example, a generic controller class have all methods for CRUD operations.

By utilizing generics, the Bookstore project achieves code reusability and type safety, as the same repository and
service interfaces can be used for different entity types without sacrificing compile-time type checks.

For more specific details on how generics are used in the project, please refer to the code and relevant classes in
the [GitHub repository](https://github.com/Arthur488/bookstore/tree/arthurs_branch).

## Architecture

The Bookstore project follows a layered architecture, separating concerns and promoting modularity and maintainability.
The key architectural components include:

- **Controller Layer**: This layer contains the API endpoints and request handling logic.
- **Service Layer**: The service layer implements the business logic and coordinates data access.
- **Repository Layer**: The repository layer handles data persistence and retrieval using Hibernate and PostgreSQL.
- **Model Layer**: The model layer defines the data models used by the application.

## API Analysis

The backend server exposes a set of RESTful APIs for performing various operations on books, authors, and orders. The
APIs follow a resource-based naming convention and adhere to REST principles. Here are some key endpoints:

- `GET /api/books`: Retrieves a list of books.
- `GET /api/books/{id}`: Retrieves a specific book, corresponding to Its id.
- `POST /api/books`: Creates and saves a new book.
- `PUT /api/books/{id}`: Updates a specific book.
- `DELETE /api/books/{id}`: Deletes a specific book by id.

Similar endpoints exist for authors, orders, categories, allowing CRUD operations on these entities. The API responses
follow
standard HTTP status codes and provide JSON payloads.

You can explore in swagger tool additional APIs, for example:

- `GET /api/orders/keyword/{keyword}`: Retrieves a list of orders by Orders book authors name.
- `GET /api/reports/{startData}:{endDate}`: Generates reports from date to date. (Pattern to inputted localDate: yyyy:
  MM:dd (Example: 2023.09.18))

For detailed API documentation, you can generate API documentation using tools like Swagger or Spring REST Docs

## Exceptions

Bookstore handles exceptions using the GlobalExceptionHandler. The GlobalExceptionHandler is responsible for catching
and handling exceptions that occur during the execution of API requests. It provides appropriate error responses to the
client.

For more information about exceptions and their handling in the Bookstore project, please refer to the code and relevant
classes in
the [GitHub repository](https://github.com/Arthur488/bookstore/tree/arthurs_branch/BookStore/src/main/java/practise/bookstore/generics/exceptions).

## Code Analysis

The Bookstore codebase follows best practices and conventions for Java development. Here are some key points:

- The Java code is organized into packages based on functionality, following the modular structure of Spring Boot
  applications.
- Clear separation of concerns is maintained, with separate packages for controllers, services, repositories, and
  models.
- Spring Data JPA and Hibernate simplify database operations and reduce boilerplate code.
- Lombok annotations such as `@Data`, `@Getter`, `@Setter`, and `@NoArgsConstructor` are used to generate getters,
  setters, constructors, and other common code, reducing manual effort.
- JUnit and Mockito are employed for unit testing, ensuring code reliability and maintainability.
- The project includes comprehensive unit tests that cover critical business logic and edge cases.

## Testing

The Bookstore application includes comprehensive unit tests to ensure the correctness of the implemented features. The
tests are written using JUnit and Mockito. Here are some key points about testing:

**There are NO Service and Controller layers for Categories**

- Unit tests are placed in separate test directories, following the same package structure as the source code.
- Test classes are named with the suffix `Test` to indicate that they are test classes.
- Mocking using Mockito allows for isolated testing of individual components, improving test reliability.
- Test coverage aims to cover critical business logic and edge cases to ensure the robustness of the application.

## Conclusion

The Bookstore project showcases a server-side web application for managing a book store.
Links:

- [GitHub repository](https://github.com/Arthur488/bookstore-java-practise)
- [Swagger](http://localhost:8080/Store/api-doc)