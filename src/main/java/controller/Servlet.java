package controller;



import controller.commands.Command;
import controller.commands.implementation.IndexCommand;
import controller.commands.implementation.LoginCommand;
import controller.commands.implementation.ReaderCommand;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@WebServlet(urlPatterns = "/library/*" , loadOnStartup = 1)
public class Servlet extends HttpServlet {


    private static ServletContext context;
    private static Map<String, Command> commands;

    @Override
    public void init(ServletConfig servletConfig) {
        context = servletConfig.getServletContext();
        commands = new ConcurrentHashMap<>();

        commands.put("index", new IndexCommand());
        commands.put("login", new LoginCommand());
        commands.put("reader", new ReaderCommand());

    }

    public static ServletContext getContext() {
        return context;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        processRequest(req, resp);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {



        //request.getRequestDispatcher("/login.jsp").forward(request, response);

        Command command = getCommand(request);
        String page = null;
        try {
            page = command.execute(request, response);
        } catch (Exception e) {
            System.err.println("Exception in Servlet");
            e.printStackTrace();
        }
        if (page != null) {
            if (page.contains("redirect: ")) {
                response.sendRedirect(request.getContextPath() + page.replaceAll("redirect: ", ""));

            } else {
                request.getRequestDispatcher(page).forward(request, response);

            }
        }
    }

    private Command getCommand(HttpServletRequest request) {
        String path = request.getRequestURI();
        path = path.replaceAll(".*/library[/]?", "").replaceAll(".*/library", "");
        return commands.getOrDefault(path, (r, j) -> "/library/index");
    }
}