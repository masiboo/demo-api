package com.example.demo.controller;

import com.example.demo.controller.util.TestDataUtil;
import com.example.demo.model.Plant;
import com.example.demo.service.PlantService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class ViewControllerTest {

    @Mock
    private PlantService plantService;

    @InjectMocks
    private ViewController viewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void index() {
        // Arrangement
       String expectedView = "index";
       // Act
       String actualView =  viewController.index();
       // Asset
       assertEquals(expectedView, actualView,
               "Both expected and actual must be equal");
    }

    @Test
    void getPlant() {
        // Arrangement
        List<Plant> expected = TestDataUtil.createPlantList();
        String expectedView = "plantDetails";
        String from = "0";
        String to = "1";
        // Act
        when(plantService.getJsonObject(from, to)).thenReturn(expected);
        String actualView =  viewController.getPlant(new ModelMap(), from, to);
        // Assert
        assertNotNull(actualView);
        assertEquals(expectedView, actualView, "Both expected and actualView must be equal");

    }
}