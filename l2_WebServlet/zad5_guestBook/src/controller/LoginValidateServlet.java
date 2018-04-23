package controller;

import model.DaneOsobowe;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.UUID;
import java.util.Vector;

@WebServlet("/loginValidate")
public class LoginValidateServlet extends HttpServlet {

    private ServletContext context;

    private Vector<DaneOsobowe> DB;

    private RequestDispatcher dispatcherBookPage;

    private RequestDispatcher dispatcherLoginPage;


    public void init() throws ServletException {

        this.DB = new Vector<>();

        this.context=getServletContext();

        this.DB.add(new DaneOsobowe(context.getInitParameter("login"),context.getInitParameter("passwd"),
                context.getInitParameter("name"),context.getInitParameter("surname"),
                "none"));

        this.dispatcherBookPage = getServletContext().getRequestDispatcher("/guestBookPage");
        this.dispatcherLoginPage = getServletContext().getRequestDispatcher("/loginPage");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        boolean error = true;
        for(DaneOsobowe dane: this.DB){
            if(dane.getLogin().equals(request.getParameter("form_login"))){

                if(dane.getPassword().equals(request.getParameter("form_passwd"))){

                    HttpSession session = request.getSession();
                    dane.setToken(UUID.randomUUID().toString());

                    session.setAttribute("user",dane.getToken());
                    session.setAttribute("userLogin",dane.getLogin());

                    //System.out.println("ok");

                    //setting session to expiry in 20 seconds
                    session.setMaxInactiveInterval(20);

                    Cookie userToken = new Cookie("userToken", dane.getToken());
                    userToken.setMaxAge(60);
                    response.addCookie(userToken);

                    Cookie userLogin = new Cookie("userLogin", dane.getLogin());
                    userToken.setMaxAge(60);
                    response.addCookie(userLogin);

                    Cookie userName = new Cookie("userName", dane.getName());
                    userToken.setMaxAge(60);
                    response.addCookie(userName);

                    request.setAttribute("userLogged", dane);

                    dispatcherBookPage.forward(request, response);
                    //System.out.println("ok2");
                   // response.sendRedirect("/guestBookPage");
                    error = false;
                }
            }
        }
        if(error){
            request.setAttribute("error3", "<h2>Bledne dane logowania!</h2>");
            this.dispatcherLoginPage.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

}
