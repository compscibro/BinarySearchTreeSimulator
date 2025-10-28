#!/bin/bash
# ---------------------------------------------------------
# ThreeTen Binary Search Tree Simulator – Build & Run Script
# ---------------------------------------------------------
# This script runs the checkstyles, compiles all Java source files, and launches the GUI simulation using the provided CS310 library.

# Run all Checkstyle checks
java -jar lib/checkstyle.jar -c config/cs310all-in-one.xml src/*.java

# Compile all Java files in src/ into the 'out' directory
javac src/*.java -d out -cp lib/310libs.jar

# Run the simulator GUI
java -cp out:lib/310libs.jar SimGUI

# Sample text for character frequency visualization:
# "When Mr. Bilbo Baggins of Bag End announced that he would shortly be celebrating..."