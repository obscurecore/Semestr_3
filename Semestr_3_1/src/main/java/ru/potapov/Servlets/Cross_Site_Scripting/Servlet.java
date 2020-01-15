package ru.potapov.Servlets.Cross_Site_Scripting;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Servlet")
public class Servlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
String one = request.getParameter("one");
one =  one == null? "":one.replaceAll("<", "&lt;").replaceAll(">", "&gt");
response.getWriter().write("<html>"+
        "<head>" +
        "one="+ one +
        "<form action = 'temp' method post'>" +
        "<textarea name='one'></textarea>" +
        "<input type = 'submit' name ='submit'/>" +
        "</form>" +
        "</body>" +
        "</html>");
    }
}
