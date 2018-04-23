package lab2;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/mainpage")
public class MainPageServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setBufferSize(8192);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html lang=\"en\">"
                    + "<head><title>zad1</title></head>");

            // then write the data of the response
            out.println("<body  bgcolor=\"#ffffff\">"

                    + "<form method=\"get\" action=\"/zad1/check\">"

                    + "<h2>Podaj swoje imie:</h2>"

                    + "<input title=\"My name is: \"type=\"text\" name=\"form_surname\" size=\"25\">"

                    + "<h2>Podaj swoj wiek:</h2>"

                    + "<input title=\"My age: \"type=\"text\" name=\"form_age\" size=\"3\">"

                    + "<p></p>"

                    + "<input type=\"submit\" value=\"Submit\">"

                    + "<input type=\"reset\" value=\"Reset\">"

                    + "</form>");

            out.println("</body></html>");
        }
    }
}
