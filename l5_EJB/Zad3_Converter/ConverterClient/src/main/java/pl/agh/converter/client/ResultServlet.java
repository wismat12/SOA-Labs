package pl.agh.converter.client;

import pl.agh.converter.ejb.Converter;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Hashtable;

@WebServlet("/result")
public class ResultServlet extends HttpServlet {


    public void init() throws ServletException {

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        double output = 0.0001;
        String method = request.getParameter("conv_dir");

        final Hashtable jndiProperties = new Hashtable();
        jndiProperties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        jndiProperties.put("jboss.naming.client.ejb.context", true);
        final Context context;

        try {
            context = new InitialContext(jndiProperties);

            final Converter service = lookupConverterEJB(context);

            if(method.equals("Fahr2Cels")){
                output = service.Fahr2Cels(Double.valueOf(request.getParameter("temperature_val")));
            }else{
                output = service.CEls2Fahr(Double.valueOf(request.getParameter("temperature_val")));
            }

        } catch (NamingException e) {
            e.printStackTrace();
        }

        response.setContentType("text/html");
        response.setBufferSize(8192);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html lang=\"en\">"
                    + "<head><title>converter</title></head>");

            // then write the data of the response
            out.println("<body  bgcolor=\"#ffffff\">"

                    + "<form method=\"post\" action=\"/ConverterClient/result\">"

                    + "<h2>Wybrałeś konwersje:</h2>"

                    + method

                    + "<h2>Wynik:</h2>"

                    + String.valueOf(output)

                    + "</form></body></html>");
        }

    }

    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }

    private static Converter lookupConverterEJB(Context context)  throws NamingException {

        return (Converter) context.lookup("ejb:/EJBConverter/ConverterBean!pl.agh.converter.ejb.Converter");
    }

}
