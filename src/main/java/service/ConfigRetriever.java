package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Config;
import entity.User;
import main.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class ConfigRetriever {
    private static String getConfigFilePath() {
        URL url = Main.class.getResource("/json/config.json");
        if(url == null) {
            throw new IllegalStateException("config.json resource not found");
        }
        return url.getPath();
    }
    public static Config retrieve() {
        String json = JsonReader.readJsonFile(getConfigFilePath());
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(json, Config.class);
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
