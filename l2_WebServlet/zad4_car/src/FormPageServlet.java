import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/formPage")
public class FormPageServlet extends HttpServlet {

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

                    + "<form method=\"post\" action=\"/zad4_car/carChoice\">"

                    + "<h2>Wybierz rodzaj samochodu:</h2>"

                    + "<select name=\"cars\">"
                    + "<option value=\"sportowy\">Sportowy</option>"
                    + "<option value=\"miejski\">Miejski</option>"
                    + "<option value=\"luksusowy\">Luksusowy</option>"
                    + "</select>"

                    + "<br/>"

                    + "<h2>Wybierz przedzial cenowy:</h2>"

                    + "Od:<input type=\"text\" name=\"form_price_from\" size=\"5\">"

                    + "   Do:<input type=\"text\" name=\"form_price_to\" size=\"5\">"

                    + "<p></p>"

                    + "<input type=\"submit\" value=\"Submit\">"

                    + "<input type=\"reset\" value=\"Reset\">"

                    + "</form>");

            out.println("</body></html>");
        }
    }
}
