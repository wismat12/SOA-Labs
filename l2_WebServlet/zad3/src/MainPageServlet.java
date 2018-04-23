import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/mainPage")
public class MainPageServlet extends HttpServlet {

    private ServletContext context;

    public void init() throws ServletException {

        this.context=getServletContext();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setBufferSize(8192);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html lang=\"en\">"
                    + "<head><title>zad5</title></head>");

            out.println("<body  bgcolor=\"#ffffff\">"

                    + "<form method=\"post\" action=\"/zad3/pageValidate\">"

                    + "<h1>Podaj ilosc parametrow</h1>"

                    + "<input type=\"text\" name=\"form_amount_param\" size=\"2\">"

                    + "<input type=\"submit\" value=\"Submit\">"

                    + "<input type=\"reset\" value=\"Reset\">"

                    + "</form>");

            out.println("</body></html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

}
