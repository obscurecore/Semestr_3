package ru.potapov.Servlets.Session;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Enumeration;

@WebServlet(name = "Servlet3")
public class Servlet3 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        Enumeration<String> stringEnumeration = session.getAttributeNames();
        while (stringEnumeration.hasMoreElements()){
            String attributeName = stringEnumeration.nextElement();
            System.out.println(attributeName+"="+session.getAttribute(attributeName));

        }
        Integer count=(Integer) session.getAttribute("count");
        if(count==null){
            session.setAttribute("count",1);
        }
        else{
            session.setAttribute("count",count+1);
        }


        Cart cart = (Cart)session.getAttribute("cart");
        String name = request.getParameter("name");
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        if(cart==null){
           cart= new Cart();
           cart.setName(name);
           cart.setQuntity(quantity);

        } else {

        }
        session.setAttribute("cart",cart);
    }
}
