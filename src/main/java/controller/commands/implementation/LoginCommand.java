package controller.commands.implementation;

import controller.commands.Command;
import controller.utils.LoginUtil;
import model.entity.User;
import model.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.Optional;

public class LoginCommand implements Command {


    private LoginUtil loginUtil = new LoginUtil();
    private LoginService service = new LoginService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        logout(req);
        String name = req.getParameter("login");
        String pass = req.getParameter("password");

       // if (name.equals("user") && pass.equals("user")) return "/index.jsp";

        try {
            User user = checkLoginAndPassword(req, name, pass);
            return getRedirectPath(user.getRole());
        } catch (Exception ex) {
            return informAboutWrongInput(req, ex.getMessage());
        }



    }


    private User checkLoginAndPassword(HttpServletRequest request, String login, String password) {
        if (login == null || login.equals("") || password == null || password.equals("")) {
            throw new RuntimeException("Invalid name or password");
        }
        Optional<User> user = service.checkUser(login, password);
        if (user.isPresent()) {
            logIn(request, user.get());
            return user.get();
        }
        return null;
    }

    private void logIn(HttpServletRequest request, User user) {
        Map<Integer, HttpSession> loggedUsers = loginUtil.getLoggedUsers();
        int userId = user.getId();
        destroyPreviousSession(loggedUsers, userId);
        loggedUsers.put(userId, request.getSession());
        loginUtil.setLoggedUsers(loggedUsers);
        sessionSetup(request, user);
    }

    private void sessionSetup(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        user.setPassword(null);
        session.setAttribute("user", user);
    }

    private void destroyPreviousSession(Map<Integer, HttpSession> loggedUsers, int userId) {
        if (loggedUsers.containsKey(userId)) {
            loggedUsers.get(userId).invalidate();
        }
    }


    private void logout(HttpServletRequest request) {
        Optional.ofNullable(request.getSession().getAttribute("user")).ifPresent(x ->
                loginUtil.logOut(request.getSession())
        );
    }

    private String informAboutWrongInput(HttpServletRequest request, String message) {
        request.setAttribute("info", message);
        return "/login.jsp";
    }

    private String getRedirectPath(User.Role role) {
        if (role == User.Role.READER) {
            return "redirect: /library/reader";
        } else return "/login.jsp";
    }

}
