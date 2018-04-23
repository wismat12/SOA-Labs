package controller;

import model.DaneOsobowe;
import model.Feedback;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Vector;

@WebServlet("/guestBookPage")
public class GuestBookPageServlet extends HttpServlet {

    private ServletContext context;
                    /*client, set of posts*/
    private HashMap<String,Vector<Feedback>> feedbackDB;

    public void init() throws ServletException {

        ServletContext context=getServletContext();
        this.feedbackDB = new HashMap<>();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DaneOsobowe dane = (DaneOsobowe)request.getAttribute("userLogged");
        if(dane!=null){ //initialization of client feedback DB
            if(this.feedbackDB.get(dane.getLogin())==null){
                this.feedbackDB.put(dane.getLogin(), new Vector<>());
            }
        }
        String login ="";
        String name ="";

        response.setContentType("text/html");
        response.setBufferSize(8192);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html lang=\"en\">"
                    + "<head><title>zad5</title></head>");

            // then write the data of the response
            out.println("<body  bgcolor=\"#ffffff\">"

                    + "<form method=\"post\" action=\"/zad5_guestBook/guestBookPage\">"

                    + "<h1>Please submit your feedback</h1>");

            Cookie[] cookies = request.getCookies();
            if(cookies!=null){                                           // if cookies are enabled
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("userLogin")){
                        login = cookie.getValue();
                    }
                    if(cookie.getName().equals("userName")){
                        name = cookie.getValue();
                    }
                }
                if(request.getParameter("form_book_name")!=null){
                    this.feedbackDB.get(login).add(new Feedback(request.getParameter("form_book_name"),
                            request.getParameter("form_book_email"),
                            request.getParameter("form_book_content"),
                            login));
                }

            }else{                                                       //if cookies are disabled - private mode
                if(request.getParameter("form_book_name")!=null){
                    this.feedbackDB.get(request.getSession().getAttribute("userLogin")).add(
                            new Feedback(request.getParameter("form_book_name"),
                            request.getParameter("form_book_email"),
                            request.getParameter("form_book_content"),
                                    (String)request.getSession().getAttribute("userLogin")));
                }
            }

            out.println("Your name: "

                    + "<input type=\"text\" name=\"form_book_name\" size=\"25\">"
                    + "</br>"+ "</br>");

            out.println("Your email: <input type=\"text\" name=\"form_book_email\" size=\"25\">"

                    + "</br>" + "</br>"

                    + "Comment: <input type=\"text\" name=\"form_book_content\" size=\"25\">"

                    + "<input type=\"submit\" value=\"Submit\">"

                    + "<input type=\"reset\" value=\"Reset\">"

                    + "</form>"

                    + "<br/>");

            out.println("<br/>FEEDBACKS:<br/>");

            for(Feedback f: this.feedbackDB.get(login)){
                out.println("<strong>" + f.getName() + "</strong>"
                        + " (" + f.getEmail() + ") says" + "<br/>"
                        + f.getContent()+ "<br/>");
            }
            out.println("</body></html>");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request,response);
    }

}
