package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/*configuration in web.xml*/
public class CorrectFormFilter implements Filter {

    private boolean error = false;

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {


        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("/loginPage");

        if(request.getParameter("form_login").isEmpty()){

            request.setAttribute("error1", "<h2>Podaj login jeszcze raz:</h2>");
            this.error = true;
        }
        if(request.getParameter("form_passwd").isEmpty()){

            request.setAttribute("error2", "<h2>Podaj haslo jeszcze raz:</h2>");
            this.error = true;
        }
        if(this.error) {
            //response.sendRedirect("/zad5_guestBook/loginPage");
            this.error = false;
            dispatcherToLoginPage.forward(request, response);

        }else{
            //forwarding request to next filters till appropriate servlet class
            chain.doFilter(req, resp);
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
