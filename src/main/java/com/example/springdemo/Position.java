package com.example.springdemo;
import java.util.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("position")
public class Position {
    @Id
    private int id;
    private String val;
    private int index1;
    private int index2;
    // Default constructor
    public Position() {
    }
    // Constructor with parameters
    public Position(String val, int index1, int index2) {
        this.val = val;
        this.index1 = index1;
        this.index2 = index2;
    }

    // Getters and Setters
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
    //Methods
    public static List<Position> solveMaze(List<Position> positions) {
        for (Position pos : positions) {
            if (" ".equals(pos.getVal())) {
                pos.setVal("*");
            }
        }
        return positions;
    }
    public static String[][] toMaze(List<Position> positions) {
        int maxRow = positions.stream().mapToInt(Position::getIndex1).max().orElse(0);
        int maxCol = positions.stream().mapToInt(Position::getIndex2).max().orElse(0);

        String[][] maze = new String[maxRow + 1][maxCol + 1];
        for (Position pos : positions) {
            maze[pos.getIndex1()][pos.getIndex2()] = pos.getVal();
        }

        return maze;
    }
}