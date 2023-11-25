# Camp Application and Management System (CAMs)

![ss_2023-11-25_22-38-49](https://github.com/dreonic/SC2002-CAMs/assets/66062290/b407e699-0bf9-4938-8e73-8bb518f354c6)

**CAMs** is an application for staff and students to manage, view and register for
camps within NTU. The application will act as a centralized hub for all staff and
students.

## Building

1. Install the [Java Development Kit](https://www.oracle.com/java/technologies/downloads/) (minimum version 17)
   and [Apache Maven](https://maven.apache.org/download.cgi).
2. Clone this repository to your machine.
3. Clean existing builds, build the application, package the application into a Java Archive (`jar` file) and generate
   the documentation by running the following command in the project directory (`SC2002-CAMs/`).

```bash
mvn clean package site
```

All build outputs (`jar` and `class` files) are stored inside the target directory (`SC2002-CAMs/target/`). The
Javadoc is generated at `
SC2002-CAMs/target/site/apidocs/`.

## Running

Execute the application through the Java Archive (`jar` file) by running the following command.

```bash
java -jar target/SC2002-CAMs-1.0-SNAPSHOT.jar
```

## Documentation

View the Javadoc generated at `SC2002-CAMs/target/site/apidocs/index.html`.
