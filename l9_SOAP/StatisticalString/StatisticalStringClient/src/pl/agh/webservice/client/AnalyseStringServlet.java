package pl.agh.webservice.client;

import pl.agh.webservice.string.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/toAnalyseString")
public class AnalyseStringServlet extends HttpServlet {

    private StatisticalString service;


    public void init() throws ServletException {

        service = new StatisticalStringImplService().getStatisticalStringImplPort();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //invoke business method
        AnalyseStringResponse.ReturnedHashMap hashmap = service.analyseString(request.getParameter("input").toString());


        response.setContentType("text/html");
        response.setBufferSize(8192);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html lang=\"en\">"
                    + "<head><title>StringAnalyseOutput</title></head>"
                    + "Analiza dla: " + request.getParameter("input") + "<br/>");


            for(AnalyseStringResponse.ReturnedHashMap.Entry entry : hashmap.getEntry()){
                out.println("Klucz " + entry.getKey() + " Wskazuje na: " + entry.getValue() +"<br/>");
            }


            out.println("</body></html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }
}
