# Telephone Line Manager - Taller

## Description

Telephone line management system with a full call lifecycle and billing by line type (local, national and corporate lines). Built with **MVC** separation and file persistence through the **DAO** pattern with DTOs and a `DataMapper`.

Academic project developed for the **Java programming** courses - Systems Engineering, Universidad El Bosque.

## Author

- Sergio Alejandro Parra Rivera

## Technologies

- Java 21 (compiled with `maven-compiler-plugin`)
- Maven 3.8+ with `exec-maven-plugin` to run the application
- **MVC** architecture (`controller`, `model`, `view`)
- Inheritance for line types (`LineaLocal`, `LineaNacional`, `LineaCorporativa` extend `LineaTelefonica`)
- **DAO** and **DTO** patterns with a `DataMapper` for file persistence
- Custom exception for call validation (`TipoLlamadaNoPermitidaException`)

## Requirements

| Requirement | Version | How to check |
| --- | --- | --- |
| JDK | 21 or higher | `javac -version` |
| Maven | 3.8 or higher | `mvn -v` |
| Git | Any recent version | `git --version` |

## Installation

Follow these steps exactly so the application runs on the first try.

### 1. Clone the repository

```bash
git clone https://github.com/P2X4A/Taller.git
```

### 2. Enter the project folder

```bash
cd Taller
```

> On Windows, if your path contains spaces, always quote it, for example: `cd "C:\Users\Example\Documents\Taller"`.

### 3. Verify your environment

```bash
javac -version
mvn -v
```

Expected: JDK 21 (or higher) and Maven 3.8 (or higher). If any command fails, install or update that tool before continuing.

### 4. Compile the project

```bash
mvn clean compile
```

What success looks like:

- The console ends with `BUILD SUCCESS`.
- A `target/classes/` folder is created with the compiled classes.

## Running the application

```bash
mvn exec:java
```

The menu is displayed in the terminal: register telephone lines, record calls and review the billing per line type.

Troubleshooting:

- `ClassNotFoundException` or missing main class: compile first with `mvn clean compile`.
- `UnsupportedClassVersionError`: your JDK is older than 21; install JDK 21 or higher and check it with `javac -version`.

## Main features

- **Line types:** `LineaLocal`, `LineaNacional` and `LineaCorporativa` extend the base `LineaTelefonica`, each with its own behavior.
- **Call lifecycle:** register calls (`Llamada`) classified by `TipoLlamada`.
- **Validation with exceptions:** a call type that the line does not allow raises `TipoLlamadaNoPermitidaException`.
- **Billing:** calls are accounted per line type to compute the invoice of each line.
- **Persistence:** lines and calls are stored in files through the DAO layer (`LlamadaDAOImpl`, `LlamadaDTO`, `DataMapper`, `FileHandler`).

## Project structure

```text
Taller/
├── pom.xml
├── README.md
├── LICENSE
└── src/
    └── co/edu/unbosque/
        ├── controller/
        │   ├── AplMain.java               # Entry point (main class)
        │   └── Controlador.java           # Application controller (MVC)
        ├── model/
        │   ├── Ecomm.java                 # Company model
        │   ├── LineaTelefonica.java       # Base telephone line
        │   ├── LineaLocal.java            # Line types
        │   ├── LineaNacional.java
        │   ├── LineaCorporativa.java
        │   ├── Llamada.java               # Call entity
        │   ├── TipoLlamada.java           # Call types
        │   ├── TipoLlamadaNoPermitidaException.java
        │   └── persistence/
        │       ├── DAO.java               # Data access interface
        │       ├── LlamadaDAOImpl.java
        │       ├── LlamadaDTO.java        # Call record (DTO)
        │       ├── DataMapper.java        # Domain object <-> DTO mapping
        │       └── FileHandler.java       # File reading and writing
        └── view/
            └── Consola.java               # Console interface
```
