package com.example.demo.controller;

import com.example.demo.model.Plant;
import com.example.demo.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlantRestController {

    @Autowired
    PlantService plantService;

    @GetMapping("/getAllPlant")
    public List<Plant> getPlant(@RequestParam(required = false) String from,
                                @RequestParam(required = false) String to){
        return plantService.getJsonObject(from, to);
    }

}
