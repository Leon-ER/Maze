package com.example.springdemo;

// Importing necessary libraries
import java.util.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

// Annotation to specify that this class represents a database table named "position"
@Table("position")
public class Position {

    // Annotation to specify that this field is the primary key in the database
    @Id
    private int id;

    private String val;

    private int index1;

    private int index2;

    public Position() {
    }
    public Position(String val, int index1, int index2) {
        this.val = val;
        this.index1 = index1;
        this.index2 = index2;
    }

    // getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public int getIndex1() {
        return index1;
    }

    public void setIndex1(int index1) {
        this.index1 = index1;
    }

    public int getIndex2() {
        return index2;
    }

    public void setIndex2(int index2) {
        this.index2 = index2;
    }

    // Static method to convert a list of Position objects to a 2D maze array
    public static String[][] toMaze(List<Position> positions) {
        // Determine the maximum row and column indices to define the size of the maze
        int maxRow = positions.stream().mapToInt(Position::getIndex1).max().orElse(0);
        int maxCol = positions.stream().mapToInt(Position::getIndex2).max().orElse(0);

        // Create a 2D array to represent the maze
        String[][] maze = new String[maxRow + 1][maxCol + 1];

        // Populate the maze array with the values from the position objects
        for (Position pos : positions) {
            maze[pos.getIndex1()][pos.getIndex2()] = pos.getVal();
        }

        // Return the maze array
        return maze;
    }
}
