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
- `Pet` is an abstract class with an abstract `calculateWeeklyFee()` method — each subclass (Dog, Cat, Rabbit) overrides this method with its own fee logic.
- `List<Pet>` was used as the field type rather than `ArrayList<Pet>` to program to an interface rather than a concrete implementation. This provides flexibility to change the underlying implementation without breaking existing code.
- `updatePet` returns a boolean rather than a Pet object, making it easier to check whether the update was successful.
- `isValidIndex()` from the `Utilities` class was used instead of writing a separate validation method in `DayCare`.

### Extras (beyond the original spec)
- Added `Rabbit` and `RabbitBreedUtility` classes
- Added a DayCare Settings menu
- Added a clear, structured menu system

### Limitations
- Pet ID does not update correctly when editing a pet — the other fields update successfully but the ID change is not persisted.
- Ran out of time to complete the project to the standard originally intended; focus was on ensuring all completed features work without bugs.

## Reference List
- XStream XML Serialization — [XStream documentation](https://x-stream.github.io/)
- Why prefer `List` over `ArrayList` — [Stack Overflow](https://stackoverflow.com/questions/147468/why-should-the-interface-for-a-java-class-be-preferred)
- Programming to an interface — [Stack Overflow](https://stackoverflow.com/questions/9852831/polymorphism-why-use-list-list-new-arraylist-instead-of-arraylist-list-n)
- Abstract classes and polymorphism — [GeeksforGeeks](https://www.geeksforgeeks.org/java/abstract-classes-in-java/)
- Java instanceof pattern matching — [Oracle JEP 394](https://openjdk.org/jeps/394)
