# Camp Application and Management System (CAMs)

**CAMs** is an application for staff and students to manage, view and register for
camps within NTU. The application will act as a centralized hub for all staff and
students.

## Prerequisites

Install the [Java Development Kit](https://www.oracle.com/java/technologies/downloads/) (minimum version 15)
and [Apache Maven](https://maven.apache.org/download.cgi).

## Installation

1. Run the following command in the root directory to build the application. Build files will be located in `target/`
   and the application will be compiled in the `.jar` file.

```bash
mvn clean package
```

2. Run the application `cams.App` inside the `.jar` file using the following command.

```bash
java -cp target/SC2002-CAMs-1.0-SNAPSHOT.jar cams.App
```

## Generating Documentation

1. Run the following command to generate application documentation from Javadoc comments.

```bash
mvn clean site
```

2. Access the application documentation site from `target/site/apidocs/index.html`.

## Building App and Generating Documentation

1. Run the following command in the root directory.

```bash
mvn clean package site 
```