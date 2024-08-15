package com.example.springdemo;

// Import necessary libraries
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
    public String getPositions(Model model) {
        // Fetch all Position entities from the database
        List<Position> positions = (List<Position>) positionRepository.findAll();
        // Convert the list of Position objects to a 2D maze array
        String[][] mazeArray = Position.toMaze(positions);
        // Create a Maze object with the 2D array
        Maze maze = new Maze(mazeArray);
        // Find all paths in the maze starting from position (1, 1)
        maze.findAllPaths(1, 1);
        // Add the paths found to the model to be accessed in the view
        model.addAttribute("paths", maze.getPaths());
        // Return the view name "positions" to render the result
        return "positions";
    }

    @GetMapping("/api/positions")
    @ResponseBody
    public Iterable<Position> getPositionsApi() {
        // Return all Position entities from the database as JSON
        return positionRepository.findAll();
    }

    @GetMapping("/api/paths")
    @ResponseBody
    public List<String[][]> getMazePathsApi() {
        // Fetch all Position entities from the database
        List<Position> positions = (List<Position>) positionRepository.findAll();
        // Convert the list of Position objects to a 2D maze array
        String[][] mazeArray = Position.toMaze(positions);
        // Create a Maze object with the 2D array
        Maze maze = new Maze(mazeArray);
        // Find all paths in the maze starting from position (1, 1)
        maze.findAllPaths(1, 1);
        // Return the list of all paths found as JSON
        return maze.getPaths();
    }
}
