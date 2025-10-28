# 🌳 Binary Search Tree Simulator

The ThreeTen Binary Search Tree Simulator is a Java-based academic project that implements a recursive Binary Search Tree (BST) and extends it into an AVL self-balancing tree to maintain efficient performance during insertions and lookups. The system recursively processes text input to count the frequency of all characters, including letters, digits, punctuation, whitespace, and dynamically balances the tree using rotation algorithms. Built with a focus on object-oriented principles, algorithmic efficiency, and recursion, the simulator features a graphical interface (SimGUI) for visualizing tree operations, making it an effective tool for understanding real-world applications of data structures and algorithm design.

## 🎥 Demo

<div>
    <a href="https://www.loom.com/share/edd6d2f747dc49b6a086e11f152d3d89"></a>
    <a href="https://www.loom.com/share/edd6d2f747dc49b6a086e11f152d3d89">
      <img style="max-width:300px;" src="https://cdn.loom.com/sessions/thumbnails/edd6d2f747dc49b6a086e11f152d3d89-910cfdecfd3476e4-full-play.gif">
    </a>
</div>

## 📁 Project Structure

```
BinarySearchTreeSimulator/
├── src/
│   ├── ThreeTenBinarySearchTree.java     # Core BST + AVL logic (implemented by Mohammed Abdur Rahman)
│   ├── TreeNode.java                     # Node structure (provided)
│   ├── ThreeTenSetNode.java              # Support class (provided)
│   ├── BuildSetAlg.java                  # Helper algorithms (provided)
│   ├── ForestAlg.java                    # Tree traversal utilities (provided)
│   ├── RecursionCheck.java               # Ensures recursive approach (provided)
│   ├── SimGUI.java                       # Visualization (provided)
│   └── TreeEdge.java                     # Supporting class (provided)
│
├── lib/
│   ├── 310libs.jar
│   ├── checkstyle.jar
│   └── junit-4.11.jar
│
├── config/
│   ├── cs310all-in-one.xml
│   ├── cs310code.xml
│   └── cs310comments.xml
│
├── .gitignore
└── README.md
```

## 💻 System Requirements

To compile and run this project, ensure your environment meets the following:
- [x] Java Version: OpenJDK 17 or higher (Java 21 recommended)
- [x] Operating System: macOS, Windows, or Linux
- [x] Build Tools: Standard Java compiler (javac) and runtime (java)
- [x] IDE (optional): Visual Studio Code, IntelliJ IDEA, or Eclipse
- [x] Dependencies:
    - [x] `310libs.jar` (provided in `/lib`)
    - [x] `checkstyle.jar` (for code style checks)
    - [x] `junit-4.11.jar` (for testing)

## 🚀 Quick Start

You can build and launch the Binary Search Tree Simulator using the provided `commands.sh` script. This script automates the setup process, compiles all source files, and runs the graphical interface.

#### 🧬 Clone the Repository

```
git clone git@github.com:compscibro/BinarySearchTreeSimulator.git
cd BinarySearchTreeSimulator
```

#### ⚙️ Make Executable and Run

```
chmod +x commands.sh
./commands.sh
```