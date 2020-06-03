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
public class PlantServiceImpl implements PlantService {
    @Autowired
    RestTemplate restTemplate;
    static String url = "http://plantplaces.com/perl/mobile/viewplantsjson.pl?Combined_Name=Oak";

    public PlantServiceImpl(){
    }

    @Bean
    private RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    /**
     * This will get all plant details in JSON format from plantplaces.com in  response with HTTP status code 200.
     * @since 03-06-2020
     * @return JSON data in string
     */
    public String getJson(){
        ResponseEntity<String> response = restTemplate.getForEntity(url,String.class);
        return response.getBody();
    }

    /**
     * This will get all plant details in JSON format from plantplaces.com
     * @since 03-06-2020
     * @param from start range
     * @param to end range
     * @return JSON data in string
     */
    public String getJsonString(String from, String to){
        return limitJsonString(getJson(), from, to);
    }

    /**
     * This will get all plant details in JSON format within the given range from plantplaces.com
     * If from or to blank it will fetch all data
     * @since 03-06-2020
     * @param from start range
     * @param to end range
     * @return List of Plant
     */
    public List<Plant> getJsonObject(String from, String to){
        return limitJsonObject(getJson(), from, to);
    }

    /**
     * This will get all plant details in JSON within the given range  from plantplaces.com
     * @since 03-06-2020
     * @param from start range
     * @param to end range
     * @return List of Plant
     */
    private String limitJsonString(String jsonData, String from, String to ){
        int intFrom = convertStringToInt(from);
        int intTo = convertStringToInt(to);
        List<Plant> allPlants = new ArrayList<>();
        JSONObject root = new JSONObject(jsonData);
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
        if(intTo > allPlants.size()){
            intTo = allPlants.size();
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

    /**
     * This will get all plant details in JSON within the given range  from plantplaces.com.
     *  It will convert JSON to list of Plant object
     * @since 03-06-2020
     * @param jsonData JSON data
     * @param from start range
     * @param to end range
     * @return List of Plant
     */
    private List<Plant> limitJsonObject(String jsonData, String from, String to ){
        int intFrom = convertStringToInt(from);
        int intTo = convertStringToInt(to);
        List<Plant> allPlants = convertJsonToObject(jsonData);
        if(intTo > allPlants.size()){
            intTo = allPlants.size();
        }
        if(intFrom < 0 || intTo <= 0 ){
            return allPlants;
        }else{
            return allPlants.subList(intFrom,intTo);
        }
    }

    /**
     * It will convert JSON to list of Plant object
     * @since 03-06-2020
     * @param jsonData JSON data
     * @return List of Plant
     */
    private List<Plant> convertJsonToObject(String jsonData){
        List<Plant> allPlants = new ArrayList<>();
        JSONObject root = new JSONObject(jsonData);
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


    /**
     * It will convert string to integer number. For any non digit return zero
     * @since 03-06-2020
     * @param input
     * @return integer number
     */
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
