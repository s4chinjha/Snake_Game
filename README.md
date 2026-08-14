# 🐍 Snake Game in Java

A classic Snake Game built using **Java Swing and AWT**, featuring keyboard controls, real-time game updates, collision detection, scoring, restart functionality, and sound effects.

The game is also packaged as a **Java JAR** and deployed to the web using **CheerpJ**, allowing the Java application to run directly in a modern web browser.

## 🌐 Play Online

**[▶️ Play Snake Game](https://s4chinjha.github.io/Snake_Game/)**

No Java installation is required to play the browser version.

## 🎮 Features

- Classic Snake gameplay
- Smooth snake movement
- Food spawning system
- Score tracking
- Snake growth
- Wall and self-collision detection
- Game Over screen
- Restart functionality
- Apple-eating sound effect
- Browser-based version using CheerpJ
- Automatic deployment using GitHub Actions

## 🛠️ Tech Stack

- Java 17
- Java Swing
- Java AWT
- CheerpJ
- GitHub Pages
- GitHub Actions

## 🚀 Getting Started

### Prerequisites

- Java JDK 17+
- Any Java IDE or terminal

## 📥 Clone the Repository

```bash
git clone https://github.com/s4chinjha/Snake_Game.git
cd Snake_Game
````

## ▶️ Run Locally

Compile the source files:

```bash
mkdir -p build/classes
javac -d build/classes src/*.java
```

Run the game:

```bash
java -cp build/classes SnakeGame
```

## 🎯 Controls

| Key | Action                  |
| --- | ----------------------- |
| ↑   | Move Up                 |
| ↓   | Move Down               |
| ←   | Move Left               |
| →   | Move Right              |
| R   | Restart after Game Over |

## 📁 Project Structure

```text
Snake_Game/
├── src/
│   ├── SnakeGame.java
│   ├── GameFrame.java
│   └── GamePanel.java
├── res/
│   └── sounds/
│       └── appleEaten.wav
├── web/
│   ├── index.html
│   └── SnakeGame.jar
└── .github/
    └── workflows/
        └── deploy.yml
```

## 🚀 Deployment

The web version is automatically deployed to **GitHub Pages** using **GitHub Actions**.

```text
Java Swing Game
      ↓
   Java 17
      ↓
   JAR File
      ↓
    CheerpJ
      ↓
    Browser
      ↓
 GitHub Pages
```

## 💡 What I Built & Learned

* Java Swing and AWT application development
* Game loops and rendering
* Keyboard event handling
* Collision detection
* Java resource management
* JAR packaging
* Running Java applications in the browser using CheerpJ
* GitHub Actions and GitHub Pages deployment

## 📌 Future Improvements

* Fullscreen browser mode
* Difficulty levels
* Pause/resume functionality
* High-score persistence
* Improved graphics and animations

---

Made with ❤️ by **Sachin Kumar Jha**
