package service;

import entity.User;

public interface UserRetriever {
    public User retrieveFromIdentifierAndPassword(String identifier, String password);
}
