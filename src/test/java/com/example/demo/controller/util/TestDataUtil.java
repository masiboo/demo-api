package com.example.demo.controller.util;

import com.example.demo.model.Plant;

import java.util.ArrayList;
import java.util.List;

public class TestDataUtil {

    public static List<Plant> createPlantList(){
        List<Plant> plants = new ArrayList<>();
        Plant plant = new Plant();
        plant.setCommon("common");
        plant.setId(1);
        plant.setGenus("genus");
        plant.setCultivar("cultivar");
        plant.setSpecies("species");
        plants.add(plant);
        return plants;
    }
}
