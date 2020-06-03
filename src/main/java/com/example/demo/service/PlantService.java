package com.example.demo.service;

import com.example.demo.model.Plant;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jdk.nashorn.internal.runtime.ParserException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class PlantService {
    @Autowired
    RestTemplate restTemplate;
    static String url = "http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=Oak";

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    private String getJson(){
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        return response.getBody();
    }

    public String getJsonString(String from, String to){
        return limitJsonString(getJson(), from, to);
    }

    public List<Plant> getJsonObject(String from, String to){
        return limitJsonObject(getJson(), from, to);
    }

    private String limitJsonString(String jsonString, String from, String to ){
        int intFrom = convertStringToInt(from);
        int intTo = convertStringToInt(to);
        List<Plant> allPlants = new ArrayList<>();
        JSONObject root = new JSONObject(jsonString);
        JSONArray plants = root.getJSONArray(Plant.KEY_ROOT_ARRAY);

        for(int i = 0; i < plants.length(); i++){
            JSONObject jsonPlant = plants.getJSONObject(i);
            Plant plant = new Plant();
            plant.setId(jsonPlant.getInt(Plant.KEY_ID));
            plant.setGenus(jsonPlant.getString(Plant.KEY_GENUS));
            plant.setCommon(jsonPlant.getString(Plant.KEY_COMMON));
            plant.setCultivar(jsonPlant.getString(Plant.KEY_CULTIVAR));
            plant.setSpecies(jsonPlant.getString(Plant.KEY_SPECIES));
            allPlants.add(plant);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if(intFrom < 0 || intTo <= 0 ){
                return objectMapper.writeValueAsString( allPlants);
            }else{
                return   objectMapper.writeValueAsString( allPlants.subList(intFrom,intTo));
            }
        } catch (JsonProcessingException e) {
            throw new ParserException(e.getMessage());
        }
    }

    private List<Plant> limitJsonObject(String jsonString, String from, String to ){
        int intFrom = convertStringToInt(from);
        int intTo = convertStringToInt(to);
        List<Plant> allPlants = convertJsonToObject(jsonString);
        if(intFrom < 0 || intTo <= 0 ){
            return allPlants;
        }else{
            return allPlants.subList(intFrom,intTo);
        }
    }

    private List<Plant> convertJsonToObject(String jsonString){
        List<Plant> allPlants = new ArrayList<>();
        JSONObject root = new JSONObject(jsonString);
        JSONArray plants = root.getJSONArray(Plant.KEY_ROOT_ARRAY);

        for(int i = 0; i < plants.length(); i++){
            JSONObject jsonPlant = plants.getJSONObject(i);
            Plant plant = new Plant();
            plant.setId(jsonPlant.getInt(Plant.KEY_ID));
            plant.setGenus(jsonPlant.getString(Plant.KEY_GENUS));
            plant.setCommon(jsonPlant.getString(Plant.KEY_COMMON));
            plant.setCultivar(jsonPlant.getString(Plant.KEY_CULTIVAR));
            plant.setSpecies(jsonPlant.getString(Plant.KEY_SPECIES));
            allPlants.add(plant);
        }
        return allPlants;
    }

    public int convertStringToInt(String input) {
        int output = 0;
        if(Objects.nonNull(input)){
            try {
                output = Integer.parseInt(input);
            }catch (NumberFormatException ignored){
            }
        }
        return output;
    }
}
