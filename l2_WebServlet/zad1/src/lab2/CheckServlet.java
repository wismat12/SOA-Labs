package lab2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/check")
public class CheckServlet extends HttpServlet {

    boolean isWoman = false;
    boolean isAdult = true;
    String name;
    String age;


    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setBufferSize(8192);
        try (PrintWriter out = response.getWriter()) {
            out.println("<html lang=\"en\">"
                    + "<head><title>zad1</title></head>");

            this.name = request.getParameter("form_surname");
            this.age = request.getParameter("form_age");

            if(name.charAt(this.name.length()-1) == 'a'){
                this.isWoman = true;

            }
            if(Integer.valueOf(this.age) < 18){
                this.isAdult = false;
            }

            if(this.isWoman){
                out.println( "<h2>you are a woman</h2>");

            }else{
                out.println( "<h2>you aren't a woman</h2>");
            }

            if(this.isAdult && this.isWoman){
                out.println( "<h2>you are adult</h2>");

            }else{
                out.println( "<h2>you aren't adult</h2>");
            }

            out.println("</body></html>");
        }
    }
}