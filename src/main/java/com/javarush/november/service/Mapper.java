package com.javarush.november.service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javarush.november.domain.City;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class Mapper {
    public Map<String, String> parseEntityMap(InputStream inputStream, ObjectMapper objectMapper) {
        try {
            return new HashMap<>(objectMapper.readValue(inputStream,
                    new TypeReference<Map<String, String>>() {
                    }));
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException("Error");
        }
    }

    public Map<Integer, City> parseCityMap(InputStream inputStream, ObjectMapper objectMapper) {
        try {
            return new HashMap<>(objectMapper.readValue(inputStream,
                    new TypeReference<Map<Integer, City>>() {
                    }));
        } catch (IOException | IllegalArgumentException e) {
            throw new RuntimeException("Error");
        }
    }
}
