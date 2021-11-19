package com.m2dfs.cityservice.controller;

import com.google.gson.Gson;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@RestController
public class CityController {

    @Autowired
    RestTemplate restTemplate;

    @ApiOperation(value = "Get city weather by cityName", response = CityController.class, tags = "getCity")
    @RequestMapping(value = "/search/{query}", method = RequestMethod.GET)
    public String getCity(@PathVariable String query) {
        String response = restTemplate.exchange("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=Rylikb8YkhPmbdXO2aAsTAfs97NsPpBc&q=" + query + "&language=fr-fr",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, query).getBody();

        String json = response;
        json = json.substring(1, json.length() - 1);
        Map jsonJavaRootObject = new Gson().fromJson(json, Map.class);

        String key = (String) jsonJavaRootObject.get("Key");

        return response = restTemplate.exchange("http://localhost:8082/current/"+key,
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, key).getBody();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
