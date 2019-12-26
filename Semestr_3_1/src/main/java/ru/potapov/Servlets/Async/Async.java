package ru.potapov.Servlets.Async;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "Async", asyncSupported = true)
public class Async extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        AsyncContext asyncContext = request.getAsyncContext();
        asyncContext.start(new Runnable() {
            @Override
            public void run() {
                //await event
            }
        });
        asyncContext.complete();
    }
}
