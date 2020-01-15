package ru.potapov.Servlets.Cookie;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TempServlet")
public class TempServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie cookie = new Cookie("testCookie", "abc");
        cookie.setMaxAge(5);
        response.addCookie(cookie);

        Cookie[] cookies = request.getCookies();
        for(Cookie cookie1:cookies){
            System.out.println(cookie1.getName());
        }
    }
}
