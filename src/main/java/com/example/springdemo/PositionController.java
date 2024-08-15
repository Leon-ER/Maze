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
    public String getPositions(Model model) {
        List<Position> positions = (List<Position>) positionRepository.findAll();
        String[][] mazeArray = Position.toMaze(positions);
        Maze maze = new Maze(mazeArray);
        maze.findAllPaths(1, 1);
        model.addAttribute("paths", maze.getPaths());
        return "positions";
    }

    @GetMapping("/api/positions")
    @ResponseBody
    public Iterable<Position> getPositionsApi() {
        return positionRepository.findAll();
    }
    @GetMapping("/api/paths")
    @ResponseBody
    public List<String[][]> getMazePathsApi() {
        List<Position> positions = (List<Position>) positionRepository.findAll();
        String[][] mazeArray = Position.toMaze(positions);
        Maze maze = new Maze(mazeArray);
        maze.findAllPaths(1, 1);
        return maze.getPaths();
    }
}
