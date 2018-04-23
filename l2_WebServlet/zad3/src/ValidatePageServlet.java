import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/pageValidate")
public class ValidatePageServlet extends HttpServlet {

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

                    + "<form method=\"post\" action=\"/zad3/actionValidate\">"

                    + "<h1>Wybrales " + request.getParameter("form_amount_param") + " parametrow</h1>");

            for(int i = 0; i < Integer.valueOf(request.getParameter("form_amount_param")); i++){
                out.println("Podaj Liczbe: <input type=\"text\" name=\"form_number"+String.valueOf(i)+ "\" size=\"6\"> <br/>");
            }

            out.println("<h1>Wybierz akcje z podanymi parametrami:</h1>"

                    + " <input type=\"checkbox\" name=\"form_action_mean\" value=\"mean\"> Chce policzyc srednia arytmetyczna podanych parametrow <br/> "

                    + " <input type=\"checkbox\" name=\"form_action_sort\" value=\"sort\"> Chce posortowac rosnaco podane parametry <br/> "

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