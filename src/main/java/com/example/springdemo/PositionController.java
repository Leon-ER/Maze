package com.example.springdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PositionController {

    @Autowired
    private PositionRepository positionRepository;

    @GetMapping("/positions")
    public String getPositions() {
        return "positions";
    }

    @GetMapping("/api/paths")
    @ResponseBody
    public List<String[][]> getMazePathsApi() {
        List<Position> positions = (List<Position>) positionRepository.findAll(); // Fetch all positions from the database
        String[][] mazeArray = Position.toMaze(positions);//Convert the list of positions to a 2d maze array
        Maze maze = new Maze(mazeArray); // Create a Maze object with the 2D array
        maze.findAllPaths(1, 1); // Start position 1,1 for maze
        return maze.getPaths(); // Return all paths as JSON
    }
}
