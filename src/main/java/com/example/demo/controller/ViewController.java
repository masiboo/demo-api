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

  /**
   * This will show the index page.
   * @since 03-06-2020
   * @return
   */
  @GetMapping("/")
  public String index(){
    return "index";
  }

  /**
   * This will get all plant details in JSON format within the given range from plantplaces.com
   * in response with HTTP status code 200. If from or to blank it will fetch all data
   * @since 03-06-2020
   * @param model send data to view
   * @param from start range
   * @param to end range
   * @return
   */
  @GetMapping("/getPlant")
  public String getPlant(ModelMap model,
                        @RequestParam(required = false) String from,
                        @RequestParam(required = false) String to){
    List<Plant> plants =  plantService.getJsonObject(from, to);
    model.addAttribute("plants", plants);
    return "plantDetails";
  }
}
