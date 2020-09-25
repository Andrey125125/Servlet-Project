package controller.commands.implementation;

import controller.commands.Command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ReaderCommand implements Command {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {


        return "/WEB-INF/reader/readerCatalog.jsp";
    }
}
