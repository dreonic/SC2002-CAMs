![CAMs Logo](https://github.com/dreonic/SC2002-CAMs/assets/66062290/8291ba8a-be45-4e54-9386-a067d2b68efe)

# Camp Application and Management System (CAMs)

![Java](https://img.shields.io/badge/Java-%23ED8B00.svg?style=flat&logo=openjdk&logoColor=white)
![Build](https://github.com/dreonic/SC2002-CAMs/actions/workflows/maven-pr.yml/badge.svg)

CAMS (Camp Application and Management System) is a Java Command Line Interface (CLI) application. It is made for staff and students to manage, view and register for camps within NTU. The app implements loosely coupled classes in multiple distinct packages. This makes our system easy to maintain, improve, and extendable.

It utilizes entity, controller, and boundary (ECB) class stereotypes. Users of the app access through menus which are boundary classes. It requests data from managers, which acts as controller classes. Managers implement logic and perform error checking and exception catching to guarantee that the app works properly. The data is fetched from entity classes, which store the data. Abstractions demonstrated enable easy modification and extension.

# Group Members

SC2002 Object Oriented Design & Programming SCSB Group 3:
| Name                 | Email                    |                                                                      GitHub Profile                                                                       |
| -------------------- | ------------------------ | :-------------------------------------------------------------------------------------------------------------------------------------------------------: |
| Gillbert Susilo Wong | gillbert001@e.ntu.edu.sg |        [![GitHub](https://img.shields.io/badge/gillwong-%23121011.svg?style=flat-square&logo=github&logoColor=white)](https://github.com/gillwong)        |
| Juan Frederick       | juan0012@e.ntu.edu.sg    |         [![GitHub](https://img.shields.io/badge/dreonic-%23121011.svg?style=flat-square&logo=github&logoColor=white)](https://github.com/dreonic)         |
| Karl Devlin Chau     | karl0009@e.ntu.edu.sg    |      [![GitHub](https://img.shields.io/badge/devlinchau-%23121011.svg?style=flat-square&logo=github&logoColor=white)](https://github.com/devlinchau)      |
| Pascalis Pandey      | pascalis001@e.ntu.edu.sg |    [![GitHub](https://img.shields.io/badge/pascalpandey-%23121011.svg?style=flat-square&logo=github&logoColor=white)](https://github.com/pascalpandey)    |
| Nguyen Thi Quynh Trang        |     trang005@e.ntu.edu.sg              | [![GitHub](https://img.shields.io/badge/quynhtrangsolar-%23121011.svg?style=flat-square&logo=github&logoColor=white)](https://github.com/quynhtrangsolar) |

## Directory Layout

```
./
├── .github/
├── app/
│   ├── src/                            # Application source
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── cams/               # CAMs application
│   │   │   └── resources/              # Initial data
│   │   └── test/
│   │       ├── java/
│   │       │   └── cams/               # Test source
│   │       └── resources/              # Test data
│   ├── .gitignore
│   └── pom.xml
├── bin/
│   └── SC2002-CAMs-1.0.jar             # Packaged jar
├── javadoc/
│   ├── index.html                      # Javadoc site
│   └── ...
├── report/
│   ├── Report.pdf                      # Report document
│   ├── SC2002-CAMs.png                 # UML class diagrams
│   ├── view package class diagram.png  #
│   └── SC2002-CAMs.vpp                 # Visual Paradigm 17.1 project
├── .gitignore
└── README.md
```

## Running the Application

To run the application using the bundled Java Archive (`jar` file), clone this repository to your machine, navigate to the 
bin folder, and run the `jar` file. The [Java Runtime Environment](https://www.oracle.com/java/technologies/downloads/) 
(minimum version 17) is required to run this.

```bash
git clone https://github.com/dreonic/SC2002-CAMs.git
cd SC2002-CAMs/bin
java -jar SC2002-CAMs-1.0.jar
```

## Accessing the Javadoc

Open `javadoc/index.html` or open [this repository's GitHub Pages](https://dreonic.github.io/SC2002-CAMs/) with your preferred browser.

## Building & Running from Source

1. Install the [Java Development Kit](https://www.oracle.com/java/technologies/downloads/) (minimum version 17)
   and [Apache Maven](https://maven.apache.org/download.cgi).
2. Clone this repository to your machine and navigate to the application directory (`app/`).

```bash
git clone https://github.com/dreonic/SC2002-CAMs.git
cd SC2002-CAMs/app
```

3. Clean existing builds, build the application, package the application into a Java Archive (`jar` file), and generate
   the documentation. All build outputs (`jar` and `class` files) are stored inside the target directory (`app/target/`). The
   Javadoc is generated at `app/target/site/apidocs/`.

```bash
mvn clean package site
```

4. Execute the application through the Java Archive (`jar` file).

```bash
java -jar target/SC2002-CAMs-1.0.jar
```

5. View the Javadoc generated at `app/target/site/apidocs/index.html`.
