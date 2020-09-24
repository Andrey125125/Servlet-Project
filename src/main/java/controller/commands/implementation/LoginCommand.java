package controller.commands.implementation;

import controller.commands.Command;
import model.service.LoginService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginCommand implements Command {

    private LoginService service = new LoginService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {

        String name = req.getParameter("login");
        String pass = req.getParameter("password");

        if (name.equals("user") && pass.equals("user")) return "/index.jsp";



        return "/register.jsp";
    }



}
