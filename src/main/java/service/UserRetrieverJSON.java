package service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.User;
import main.Main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class UserRetrieverJSON implements UserRetriever{
    private final String pathToJSONUsers;

    public UserRetrieverJSON() {
        URL url = Main.class.getResource("/json/users.json");
        if(url == null) {
            throw new IllegalStateException("users.json resource not found");
        }
        this.pathToJSONUsers = url.getPath();
    }

    @Override
    public User retrieveFromIdentifierAndPassword(String identifier, String password) {
        String json = JsonReader.readJsonFile(this.pathToJSONUsers);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            List<User> users = objectMapper.readValue(json, new TypeReference<List<User>>() {});

            for (User user : users) {
                if (user == null) {
                    continue;
                }

                if (user.getPassword().equals(password) && user.getIdentifier().equals(identifier)) {

                    return user;
                }
            }
        } catch (JsonProcessingException e) {
            System.err.println(e.getMessage());
        }
        return null;
    }
}
