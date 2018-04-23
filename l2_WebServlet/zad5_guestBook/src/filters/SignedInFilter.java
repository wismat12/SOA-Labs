package filters;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/guestBookPage")
public class SignedInFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        RequestDispatcher dispatcherToLoginPage = request.getRequestDispatcher("/loginPage");
        RequestDispatcher dispatcherToGuestBookPage = request.getRequestDispatcher("/guestBookPage");

        if(request.getSession().getAttribute("user") == null){

            Cookie[] cookies = request.getCookies();

            if(cookies!=null){                                    //Przy pierwszym logowaniu nie pokaże się napis - twoja sesja wygasla

                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("userToken")){
                        //System.out.println(cookie.getValue());
                        request.setAttribute("error_session_expired", "<h2>Twoja sesja wygasla! Wprowadz dane ponownie:</h2>");
                        break;
                    }
                }
            }
            dispatcherToLoginPage.forward(request, response);

        }else
            dispatcherToGuestBookPage.forward(request, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
