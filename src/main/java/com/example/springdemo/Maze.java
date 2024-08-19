package com.example.springdemo;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private String[][] maze;
    private int rows, cols;
    private int total = 0;
    private List<String[][]> paths = new ArrayList<>();

    public Maze(String[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
    }

    public List<String[][]> getPaths() {
        return paths;
    }

    public void findAllPaths(int startRow, int startCol) {
        boolean[][] visited = new boolean[rows][cols]; // 2D array to keep track of visited cells
        List<int[]> path = new ArrayList<>(); // List to keep the current path
        depthFirstSearchRecursive(startRow, startCol, visited, path);
        System.out.println("Total paths found: " + total);
    }

    // Recursive method to perform depth-first search for paths
    private void depthFirstSearchRecursive(int row, int col, boolean[][] visited, List<int[]> path) {
        // Check if the current cell is out of bounds, already visited, or a wall
        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col] || maze[row][col].equals("0")) {
            return;
        }
        //Mark the current cell as visited and adds the current cell to the path
        visited[row][col] = true;
        path.add(new int[]{row, col});

        // Check if the end of the maze has been reached
        if (maze[row][col].equals("E")) {
            total++;
            paths.add(copyAndMarkPath(path)); // Store a copy of the path
            path.remove(path.size() - 1); // Remove the current cell from the path
            visited[row][col] = false; // Unmark the current cell as visited
            return;
        }

        // Explore the four possible directions: down, up, right, and left
        depthFirstSearchRecursive(row + 1, col, visited, path); // down
        depthFirstSearchRecursive(row - 1, col, visited, path); // up
        depthFirstSearchRecursive(row, col + 1, visited, path); // right
        depthFirstSearchRecursive(row, col - 1, visited, path); // left

        path.remove(path.size() - 1); // Remove the current cell from the path
        visited[row][col] = false; // Unmark the current cell as visited
    }

    // Method to create a copy of the maze and mark the path
    private String[][] copyAndMarkPath(List<int[]> path) {
        String[][] mazeCopy = new String[rows][cols]; // Create a copy of the maze
        for (int i = 0; i < rows; i++) {
            System.arraycopy(maze[i], 0, mazeCopy[i], 0, cols); // Copy each row
        }

        // Mark the cells in the path with "*"
        for (int[] pos : path) {
            int row = pos[0];
            int col = pos[1];
            if (!mazeCopy[row][col].equals("S") && !mazeCopy[row][col].equals("E")) {
                mazeCopy[row][col] = "*";
            }
        }
        return mazeCopy; // Return the marked maze copy
    }
}
