package com.example.demo.controller;

import com.example.demo.model.Plant;
import com.example.demo.service.PlantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ViewController {

  @Autowired
  PlantService plantService;

  @GetMapping("/")
  public String index(){
    return "index";
  }

  @GetMapping("/getPlant")
  public String getTest(ModelMap model,
                        @RequestParam(required = false) String from,
                        @RequestParam(required = false) String to){
    List<Plant> plants =  plantService.getJsonObject(from, to);
    model.addAttribute("plants", plants);
    return "plantDetails";
  }
}
