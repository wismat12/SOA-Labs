package jms;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//http://localhost:8080/Zad1ListProducer/producer?subjectList=topic1&chosen=none&msg=mojaWiadomosc

@WebServlet("/producer")
public class ProducerServlet extends HttpServlet{
    
    @EJB
    private MessageSender messageSender;
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String subject = req.getParameter("subjectList");
        String receiver = req.getParameter("chosen");
        String msg = req.getParameter("msg");
        messageSender.sendMsg(subject, receiver, msg);
    }

    @Override
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
