package model.service;

import model.entity.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class LoginService {

    private static Map<String, String> users = new HashMap<>();
    static  {
        users.put("user", "user");
        users.put("admin", "admin");
        users.put("user2", "user2");
    }


    public Optional<User> checkUser(String login, String password){
        return Optional.ofNullable(users.get(login));
    }
}
