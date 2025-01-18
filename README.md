
# TRON Game Project

This repository contains the implementation of the TRON game, a classic light-cycle-based game inspired by the movie "TRON." The project includes source code, documentation, and build files.

## Features

- **Light-Cycle Gameplay**: Players control a light-cycle, leaving trails that opponents must avoid.
- **Single and Multiplayer Modes**: Play against the computer or with other players.
- **Customizable Settings**: Adjust game parameters such as grid size, and more.
- **Documentation**: Comprehensive project details in `Tron_game_documentation.pdf`.

## File Structure

```
TRON/
├── build.xml           # Apache Ant build script
├── build/              # Build output files
├── dist/               # Distributable files
├── manifest.mf         # Manifest file for Java application
├── nbproject/          # NetBeans project files
├── src/                # Source code of the project
└── Tron_game_documentation.pdf # Game documentation
```

## Getting Started

### Prerequisites

- Java Development Kit (JDK) 8 or higher
- Apache Ant (for building the project)
- NetBeans IDE (optional, for development)

### Installation

1. Clone the repository:
   ```bash
   git clone https://github.com/Abaidullah889/TRON.git
   cd TRON
   ```

2. Build the project using Apache Ant:
   ```bash
   ant build
   ```

3. Run the game:
   ```bash
   ant run
   ```


## Documentation

- **Detailed Design**: Read `Tron_game_documentation.pdf` for in-depth information on the game’s features, implementation, and usage.

## Planned Improvements

- Adding JUnit tests for core components like `GameController`, `Motorcycle`, and `HighScores`.
- Enhancing AI behavior for more competitive gameplay.
- Introducing new game boards and customizable game parameters.

## Contributing

Contributions are welcome! Follow these steps to contribute:

1. Fork the repository.
2. Create a feature branch:
   ```bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```bash
   git commit -m "Add feature"
   ```
4. Push to the branch:
   ```bash
   git push origin feature-name
   ```
5. Open a pull request.

---

Enjoy playing the TRON game! Your feedback and contributions are highly appreciated.
