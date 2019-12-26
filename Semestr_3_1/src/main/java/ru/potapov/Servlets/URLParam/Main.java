package ru.potapov.Servlets.URLParam;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet("/Main")
public class Main extends HttpServlet {
    public Main() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
        String reqq = req.getParameter("one");
        String []ones = req.getParameterValues("value");
        Enumeration<String> params = req.getParameterNames();
        while(params.hasMoreElements()){
            String nextel = params.nextElement();
            System.out.println(nextel+"="+req.getParameter(params.nextElement()));
        }
        System.out.println(req.getRequestURL());
        System.out.println(req.getQueryString());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
}
