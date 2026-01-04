# Pet Day Care Management System

A Java-based console application for managing a pet day care facility. This system allows staff to track pets (dogs, cats, and rabbits), manage bookings, calculate fees, and generate reports.

## Features

### Pet Management
- **Add Pets**: Register dogs, cats, and rabbits with their specific attributes
- **Update Pets**: Modify existing pet information
- **Delete Pets**: Remove pets by index or ID
- **Search Pets**: Find pets by name or owner

### Pet Types Supported
- **Dogs**: Track breed, dangerous breed status, and neutered status
- **Cats**: Monitor indoor/outdoor status and favorite toys
- **Rabbits**: Record litter training status and breed

### Reports & Analytics
- List all pets, dogs, cats, or specific categories
- Filter dangerous breed dogs
- Find indoor cats
- Search by age, favorite toy, owner, or attendance duration
- Calculate weekly income from all pets
- Track average days per week pets stay

### Data Persistence
- Save and load pet data using XML serialization
- Automatic file management with XStream

## System Requirements

- Java 11 or higher
- XStream library for XML serialization
- Console/terminal environment

## Project Structure

```
src/
├── controllers/
│   └── DayCare.java          # Main controller managing all pets
├── models/
│   ├── Pet.java              # Abstract base class for all pets
│   ├── Dog.java              # Dog-specific implementation
│   ├── Cat.java              # Cat-specific implementation
│   └── Rabbit.java           # Rabbit-specific implementation
├── utils/
│   ├── CatToyUtility.java    # Validates cat toy names
│   ├── DogBreedUtility.java  # Validates dog breeds
│   ├── RabbitBreedUtility.java # Validates rabbit breeds
│   ├── ScannerInput.java     # Robust user input handling
│   ├── Utilities.java        # General utility methods
│   └── ISerializer.java      # Serialization interface
└── main/
    └── Driver.java           # Main application entry point
```

## How to Use

### Starting the Application

1. Compile all Java files
2. Run the `Driver` class:
   ```bash
   java main.Driver
   ```

### Main Menu Options

```
1) Pets CRUD Menu        - Add, list, update, or delete pets
2) Reports Menu          - Generate various reports
3) Search & Sort Menu    - Search pets by different criteria
4) Save pets             - Save data to pets.xml
5) Load pets             - Load data from pets.xml
0) Exit                  - Close the application
```

### Adding a Pet

1. Select option 1 from the main menu (CRUD Menu)
2. Choose option 1 (Add a new Pet)
3. Select pet type (Dog or Cat)
4. Enter required information:
    - Owner's name
    - Pet's age
    - Sex (m/f)
    - Days attending (Mon-Fri)
    - Unique ID number
    - Pet's name
    - Neutered status

#### Dog-Specific Fields
- Breed (validated against approved list)
- Dangerous breed status

#### Cat-Specific Fields
- Indoor cat status
- Favorite toy (validated against approved list)

### Fee Calculation

The system automatically calculates weekly fees based on:

- **Dogs**:
    - Dangerous breeds: €40/day
    - Non-dangerous breeds: €30/day
- **Cats**:
    - Indoor cats: €25/day
    - Outdoor cats: €20/day
- **Rabbits**:
    - Litter trained: €15/day
    - Not litter trained: €18/day

Fees are multiplied by the number of days the pet attends per week.

## Validation Features

### Input Validation
- Pet IDs must be unique
- Sex must be 'm' or 'f'
- Dog breeds validated against approved list (22 breeds)
- Cat toys validated against approved list (22 toys)
- Rabbit breeds validated against approved list (22 breeds)
- Boolean inputs validated as 'y' or 'n'

### Data Validation
- Owner names truncated to 20 characters
- Pet names truncated to 30 characters
- Age must be a positive integer
- Days attending tracked as boolean array (5 days)

## Sample Dog Breeds

- Labrador Retriever
- German Shepherd
- Golden Retriever
- Bulldog
- Beagle
- And 17 more...

## Sample Cat Toys

- Feather Wand
- Laser Pointer
- Ball of Yarn
- Mice Toy
- Catnip Mouse
- And 17 more...

## Sample Rabbit Breeds

- Netherland Dwarf
- Holland Lop
- Mini Lop
- Lionhead
- Flemish Giant
- And 17 more...

## Data Storage

Pet data is persisted to `pets.xml` in the project root directory using XStream XML serialization. The file is automatically created on first save.

## Key Classes

### DayCare (Controller)
- Central management class for all pets
- Handles CRUD operations
- Generates reports and statistics
- Manages data persistence

### Pet (Abstract Model)
- Base class for all pet types
- Common attributes: owner, age, sex, ID, name, neutered status
- Days attending tracking
- Abstract method for fee calculation

### Dog, Cat, Rabbit (Concrete Models)
- Extend Pet with type-specific attributes
- Implement custom fee calculation logic
- Override toString() for detailed output

## Error Handling

- Invalid menu options display error messages
- Invalid IDs prompt re-entry
- File operations wrapped in try-catch blocks
- Input validation prevents invalid data entry

## Future Enhancements

- Sort functionality for pets
- Additional search criteria
- More pet types
- Enhanced reporting features
- GUI interface
- Database integration

## Author

Rachel Gillespie

## Version

1.0

## License

Educational project - All rights reserved

