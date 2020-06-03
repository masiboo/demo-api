package com.example.demo.controller;

import com.example.demo.model.Plant;
import com.example.demo.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlantRestController {

    @Autowired
    PlantService plantService;

    /**
     * This will get all plant details in JSON format within the given range from plantplaces.com
     * in response with HTTP status code 200. If from or to blank it will fetch all data
     * @since 03-06-2020
     * @param from start range
     * @param to end range
     * @return
     */
    @GetMapping("/getAllPlant")
    public List<Plant> getPlant(@RequestParam(required = false) String from,
                           @RequestParam(required = false) String to){
        return plantService.getJsonObject(from, to);
    }

}
