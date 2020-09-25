package controller.utils;

import controller.Servlet;
import model.entity.User;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

public class LoginUtil {

    private static ServletContext context = Servlet.getContext();


    private Integer getUserId(HttpSession session) {
        Optional<User> user = Optional.ofNullable(((User) session.getAttribute("user")));
        return user.map(User::getId).orElse(-1);
    }

    public void logOut(HttpSession session) {
        Map<Integer, HttpSession> loggedUsers = getLoggedUsers();
        loggedUsers.remove(getUserId(session));
        setLoggedUsers(loggedUsers);

    }

    public void setLoggedUsers(Map<Integer, HttpSession> loggedUsers) {
        context.setAttribute("loggedUsers", loggedUsers);
    }

    public Map<Integer, HttpSession> getLoggedUsers() {
        return Optional.ofNullable((ConcurrentHashMap<Integer, HttpSession>) context.getAttribute("loggedUsers"))
                        .orElse(new ConcurrentHashMap<>());
    }
}
