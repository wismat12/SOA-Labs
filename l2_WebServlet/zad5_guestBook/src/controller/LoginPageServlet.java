package controller;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/loginPage")
public class LoginPageServlet extends HttpServlet {

    private ServletContext context;

    public void init() throws ServletException {

        this.context=getServletContext();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html");
        response.setBufferSize(8192);

        //System.out.println("loginPage");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html lang=\"en\">"
                    + "<head><title>zad5</title></head>");

            // then write the data of the response
            out.println("<body  bgcolor=\"#ffffff\">"

                    + "<form method=\"post\" action=\"/zad5_guestBook/loginValidate\">"

                    + "<h1>Logowanie</h1>");

            if(request.getAttribute("error_session_expired")!=null){
                out.println(request.getAttribute("error_session_expired"));
            }

            if(request.getAttribute("error3")!=null){               //bledne dane
                out.println(request.getAttribute("error3"));
            }

            if(request.getAttribute("error1")!=null){               //brak loginu
                out.println(request.getAttribute("error1"));
            }

            out.println("<h2>login:</h2>"

                    + "<input type=\"text\" name=\"form_login\" size=\"25\">"

                    + "<h2>haslo:</h2>");

            if(request.getAttribute("error2")!=null){               //brak hasla
                out.println(request.getAttribute("error2"));
            }
            out.println("<input type=\"text\" name=\"form_passwd\" size=\"25\">"

                    + "<p></p>"

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
