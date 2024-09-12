# Pokemon Battle Simulator

This project is a Pokemon battle simulator written in Java. It uses Maven for dependency management and includes various features for loading, managing, and battling with Pokemon.
It's only a prototype and it's not finished yet. It works in terminal and it's not really user-friendly. I made it for fun and to learn Java.

## Project Structure

- `src/main/java/Mostro/Database.java`: Manages the import of Pokemon, moves, and types data from text files.
- `src/main/java/Main.java`: Contains the main entry point of the application and handles the battle logic.
- `src/main/java/Mostro/Move.java`: Defines the `Move` class representing a Pokemon move.
- `src/main/java/Mostro/Pokemon.java`: Defines the `Pokemon` class representing a Pokemon with its stats, moves, and types.
- `src/main/java/Mostro/Type.java`: Defines the `Type` class representing a Pokemon type and its weaknesses.

## Features

- **Data Loading**: Imports Pokemon, moves, and types data from text files and from internet.
- **Battle Simulation**: Simulates battles between teams of Pokemon, with damage calculation based on stats, moves, and types.
- **User Interaction**: Allows the user to choose moves during the battle.

## Requirements

- Java 8 or higher
- Maven

## Installation

1. Clone the repository:
    ```sh
    git clone https://github.com/your-username/pokemon-battle-simulator.git
    cd pokemon-battle-simulator
    ```

2. Build the project with Maven:
    ```sh
    mvn clean install
    ```

3. Run the application:
    ```sh
    mvn exec:java -Dexec.mainClass="Main"
    ```

## Usage

1. On startup, the application will load Pokemon data.
2. Two teams of Pokemon will be created, one for the user and one for the enemy.
3. During the battle, the user can choose moves to use against enemy Pokemon.
4. The battle continues until one of the teams has no more available Pokemon.

## Contributions

Contributions are welcome! Feel free to open issues or pull requests to improve the project.

## License

This project is licensed under the MIT License. See the `LICENSE` file for more details.