package controller.commands.implementation;

import controller.commands.Command;
import model.service.IndexService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class IndexCommand implements Command {

    private IndexService service = new IndexService();

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        return "/index.jsp";
    }
}
