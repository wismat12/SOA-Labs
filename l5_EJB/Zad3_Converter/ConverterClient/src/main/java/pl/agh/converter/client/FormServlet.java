package pl.agh.converter.client;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/convPage")
public class FormServlet extends HttpServlet {

    private RequestDispatcher dispatcherResultPage;

    public void init() throws ServletException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setBufferSize(8192);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html lang=\"en\">"
                    + "<head><title>converter</title></head>");

            // then write the data of the response
            out.println("<body  bgcolor=\"#ffffff\">"

                    + "<form method=\"post\" action=\"/ConverterClient/result\">"

                    + "<h2>Wybierz kierunek konwersji:</h2>"

                    + "<select name=\"conv_dir\">"
                    + "<option value=\"Fahr2Cels\">F na C</option>"
                    + "<option value=\"CEls2Fahr\">C na F</option>"
                    + "</select>"

                    + "<h2>Wpisz wartosc:</h2>"
                    + "<input type=\"text\" name=\"temperature_val\" size=\"25\"> <br/><br/>"

                    + "<input type=\"submit\" value=\"Submit\">"
                    + "<input type=\"reset\" value=\"Reset\">"

                    + "</form></body></html>");
        }catch (Exception e){

        }

    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

}

