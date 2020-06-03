package com.example.demo.service;

import com.example.demo.model.Plant;

import java.util.List;

public interface PlantService {
    String getJson();
    List<Plant> getJsonObject(String from, String to);
    String getJsonString(String from, String to);
}
