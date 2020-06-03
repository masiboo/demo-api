package com.example.demo.controller;

import com.example.demo.controller.util.TestDataUtil;
import com.example.demo.model.Plant;
import com.example.demo.service.PlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class PlantRestControllerTest {

    @Mock
    private PlantService plantService;

    @InjectMocks
    private PlantRestController plantRestController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getPlant() {
        List<Plant> expected = TestDataUtil.createPlantList();
        String from = "0";
        String to = "1";

        when(plantService.getJsonObject(from,to)).thenReturn(expected);

        List<Plant> actual =  plantRestController.getPlant(from,to);

        assertNotNull(actual);
        assertEquals(expected, actual, "Both expected and actual must be equal");
    }
}