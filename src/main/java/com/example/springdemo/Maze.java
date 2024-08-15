package com.example.springdemo;

import java.util.ArrayList;
import java.util.List;

public class Maze {
    private final String[][] maze;
    private final int rows, cols;
    private int total = 0;
    private final List<String[][]> paths = new ArrayList<>();

    public Maze(String[][] maze) {
        this.maze = maze;
        this.rows = maze.length;
        this.cols = maze[0].length;
    }

    public List<String[][]> getPaths() {
        return paths;
    }

    // Recursive approach
    public void findAllPaths(int startRow, int startCol) {
        boolean[][] visited = new boolean[rows][cols];
        List<int[]> path = new ArrayList<>();
        depthFirstSearchRecursive(startRow, startCol, visited, path);
        System.out.println("Total paths found: " + total);
    }

    private void depthFirstSearchRecursive(int row, int col, boolean[][] visited, List<int[]> path) {
        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col] || maze[row][col].equals("0")) {
            return;
        }

        visited[row][col] = true;
        path.add(new int[]{row, col});

        if (maze[row][col].equals("E")) {
            total++;
            paths.add(copyAndMarkPath(path));
            path.remove(path.size() - 1);
            visited[row][col] = false;
            return;
        }

        depthFirstSearchRecursive(row + 1, col, visited, path); // down
        depthFirstSearchRecursive(row - 1, col, visited, path); // up
        depthFirstSearchRecursive(row, col + 1, visited, path); // right
        depthFirstSearchRecursive(row, col - 1, visited, path); // left

        path.remove(path.size() - 1);
        visited[row][col] = false;
    }

    private String[][] copyAndMarkPath(List<int[]> path) {
        String[][] mazeCopy = new String[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(maze[i], 0, mazeCopy[i], 0, cols);
        }

        for (int[] pos : path) {
            int row = pos[0];
            int col = pos[1];
            if (!mazeCopy[row][col].equals("S") && !mazeCopy[row][col].equals("E")) {
                mazeCopy[row][col] = "*";
            }
        }
        return mazeCopy;
    }
}
