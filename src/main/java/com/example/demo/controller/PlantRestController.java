package com.example.demo.controller;

import com.example.demo.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlantRestController {

    @Autowired
    PlantService plantService;

    @GetMapping("/getxxxx")
    public String getTest(){
        return   plantService.getJsonString(null, null);
    }

}
