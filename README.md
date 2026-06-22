# 🐾 Pet Daycare Management System

## Project Overview
Pet Daycare Management System is a Java console application for managing a pet daycare facility. Staff can register dogs and cats, manage their records, generate reports, search the pet list, and calculate weekly income. Data is persisted between sessions using XML serialization. Built as part of a Higher Diploma in Computer Science, Object-Oriented Programming module.

## Features
- **Register pets** — add dogs or cats with full details (owner, age, sex, attendance days, breed, neutered status)
- **CRUD operations** — update pet information or delete pets by index or ID
- **Reports** — list all pets, dogs, cats, dangerous breed dogs, indoor cats, or neutered pets; view counts for each category
- **Weekly income report** — calculates total weekly fees across all registered pets, based on breed/type and days attending
- **Search** — find pets by name, by owner, by favourite toy, or filter dogs by age and pets by days attending
- **Data persistence** — save and load all pet data to and from `pets.xml` using XStream serialization
- Input validation throughout: unique IDs, validated dog breeds, cat toys, y/n booleans, and m/f sex inputs

## Fee Structure
| Pet Type | Rate |
|---|---|
| Dangerous breed dog | €40/day |
| Standard dog | €30/day |
| Indoor cat | €25/day |
| Outdoor cat | €20/day |
| Litter-trained rabbit | €15/day |
| Non-litter-trained rabbit | €18/day |

## Tech Stack
| Category | Technology |
|---|---|
| Language | Java |
| Serialization | XStream (XML) |
| Type | Console Application |
| IDE | IntelliJ IDEA |

## Project Structure
```
src/
├── controllers/
│   └── DayCare.java          # Main controller — all pet management logic
├── models/
│   ├── Pet.java              # Abstract base class
│   ├── Dog.java
│   ├── Cat.java
│   └── Rabbit.java
├── utils/
│   ├── ScannerInput.java     # Scanner wrapper for safe user input
│   ├── Utilities.java
│   ├── ISerializer.java      # Serialization interface
│   ├── DogBreedUtility.java
│   ├── CatToyUtility.java
│   └── RabbitBreedUtility.java
└── main/
    └── Driver.java           # Entry point and menu I/O
```

## Setup Instructions

### Prerequisites
- [Java JDK 17+](https://www.oracle.com/java/technologies/downloads/) installed
- [XStream library](https://x-stream.github.io/) added to the project classpath
- A terminal or an IDE such as [IntelliJ IDEA](https://www.jetbrains.com/idea/)

### Installation
1. Clone the repository:
```bash
git clone https://github.com/rachel-gillespie/pet-daycare-management-system
cd pet-daycare-management-system
```
2. Add the XStream `.jar` file to your project's classpath (via IntelliJ's Project Structure, or include it in your `lib/` folder and reference it when compiling).

## How to Run

**From IntelliJ IDEA:**
Open the project and run `src/main/Driver.java`.

**From the terminal** (with XStream jar in `lib/`):
```bash
javac -cp lib/xstream.jar -d out src/**/*.java
java -cp out:lib/xstream.jar main.Driver
```

## Reflection

### Architecture Choices
- `Pet` is an abstract class with an abstract `calculateWeeklyFee()` method — each subclass (Dog, Cat, Rabbit) implements its own fee logic. This means the weekly income report works by iterating over an `ArrayList<Pet>` and calling `calculateWeeklyFee()` polymorphically, without needing to check the type of each pet.
- The Controller/Model separation keeps all business logic in `DayCare.java` and all I/O in `Driver.java`. This makes it easier to test the logic independently of the user interface.
- A custom `ScannerInput` utility class wraps the Scanner and handles buffer clearing, preventing common input issues when mixing `nextInt()` and `nextLine()`.
- `Utilities.YNtoBoolean()` converts `y/n` char inputs to booleans consistently across the app.
- XStream was used for XML serialization as it requires minimal setup and produces human-readable output that's easy to debug.

### Trade-offs
- String concatenation is used in the reporting methods rather than `StringBuilder`. For small datasets this is fine, but it would become inefficient with a large number of pets.
- Some menu options — Check In/Out, Today's Attendance, and Sort — are implemented as empty stubs. The menu is fully wired up for future expansion, but these features were out of scope for the submission.

### Limitations
- The `Rabbit` model class is fully implemented with fee logic, but the Driver's add-pet menu only presents Dog and Cat as options — Rabbit support isn't wired up to the UI.
- `pets.xml` is saved to the project root directory; the path is not configurable.
- No GUI — the application is console-only.

## Reference List
- XStream XML Serialization — [XStream documentation](https://x-stream.github.io/)
- Java ArrayList — [Oracle docs](https://docs.oracle.com/javase/8/docs/api/java/util/ArrayList.html)
- Abstract classes and polymorphism — [GeeksforGeeks](https://www.geeksforgeeks.org/java/abstract-classes-in-java/)
- Java instanceof pattern matching — [Oracle JEP 394](https://openjdk.org/jeps/394)
