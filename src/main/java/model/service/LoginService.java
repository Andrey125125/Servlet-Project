package model.service;

import model.entity.User;


import java.util.*;

public class LoginService {

    private static List<User> users = new ArrayList<>();
    static  {
        User admin = new User();
        admin.setRole(User.Role.ADMIN);
        admin.setLogin("admin");
        admin.setPassword("admin");
        users.add(admin);
        User user = new User();
        user.setRole(User.Role.READER);
        user.setLogin("user");
        user.setPassword("user");
        users.add(user);
        User user1 = new User();
        user1.setRole(User.Role.READER);
        user1.setLogin("user1");
        user1.setPassword("user1");
        users.add(user1);

    }


    public Optional<User> checkUser(String login, String password){
        Optional<User> optionalUser = Optional.empty();
        for (User user: users){
            if (user.getLogin().equals(login) && user.getPassword().equals(password)) optionalUser = Optional.of(user);
        }
        return optionalUser;
    }
}
