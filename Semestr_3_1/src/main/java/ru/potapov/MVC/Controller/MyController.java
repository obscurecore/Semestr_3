package ru.potapov.MVC.Controller;

import ru.potapov.MVC.Bean.StudentB;
import ru.potapov.MVC.model.MyModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MyController")
public class MyController extends HttpServlet {
   MyModel myModel = new MyModel();
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        StudentB studentB = myModel.getStudent();
        request.setAttribute("Student",studentB);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("MyView.jsp");
        requestDispatcher.forward(request,response);
    }
}
