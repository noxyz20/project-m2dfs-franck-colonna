package com.m2dfs.franck.colonna.project.foot.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;


@RestController
public class CurrentController {

    @Autowired
    RestTemplate restTemplate;

    @ApiOperation(value = "Get details for weather", response = CurrentController.class, tags = "getWeather")
    @RequestMapping(value = "/current/{key}", method = RequestMethod.GET)
    public String getWeather(@PathVariable String key) {
        String response = restTemplate.exchange("http://dataservice.accuweather.com/currentconditions/v1/{key}?apikey=u5S7y7V5wFQR7JlNwu4G0Y9x4pOXJY2b&language=fr-fr",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, key).getBody();
        return response;
    }

    String query;

    @ApiOperation(value = "Get details for weather", response = CurrentController.class, tags = "getCity")
    @RequestMapping(value = "/getCity/{query}", method = RequestMethod.GET)
    public String getCity(@PathVariable String query) {
        String response = restTemplate.exchange("http://dataservice.accuweather.com/locations/v1/cities/search?apikey=2ISwGQTvWNt4926KkCQnSbTUXDoWTXCc&q=" + query + "&language=fr-fr",
                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, query).getBody();
        return response;
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}