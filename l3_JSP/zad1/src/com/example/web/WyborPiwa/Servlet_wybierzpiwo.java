package com.example.web.WyborPiwa;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/WybierzPiwo.do")
public class Servlet_wybierzpiwo extends HttpServlet {

    private ServletContext context;

    public void init() throws ServletException {

        this.context=getServletContext();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        out.println("Porada piwna<br/>");

        String c = request.getParameter("kolor");

        out.println("<br/>Wybor kolor piwa: "+c);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

}
