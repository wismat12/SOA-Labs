import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

@WebServlet("/actionValidate")
public class AnyParamsServlet extends HttpServlet {


    public void init() throws ServletException {


    }

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Enumeration enumeration = request.getParameterNames();
        boolean error = false;
        Vector<Double> doubles = new Vector<>();

        while (enumeration.hasMoreElements()) {

            String parameterName = (String) enumeration.nextElement();
            try
            {
                if(parameterName.contains("form_number")){
                    doubles.add(Double.parseDouble(request.getParameter(parameterName)));
                }

            }
            catch(NumberFormatException e)
            {
                error = true;
                break;
            }
        }

        response.setContentType("text/html");
        response.setBufferSize(8192);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html lang=\"en\">"
                    + "<head><title>zad1</title></head>");

            out.println("<body  bgcolor=\"#ffffff\">");

            if(!error){
                if(request.getParameter("form_action_mean")!=null){

                    int number_amount = doubles.size();
                    double sum = 0.0;
                    double mean = 0.0;

                    for(Double d : doubles){
                        sum += d;
                    }
                    mean = sum/number_amount;

                    out.println("<h2>Srednia: "+ String.valueOf(mean)+"</h2>");
                }
                if(request.getParameter("form_action_sort")!=null){

                    /*//compare(final Person lhs,Person rhs)
                    //TODO return 1 if rhs should be before lhs
                    //     return -1 if lhs should be before rhs
                    //     return 0 otherwise
                    Vector<Double> doublestmp = new Vector<>();
                    doublestmp.addAll(doubles);
                    doublestmp.sort((a, b) -> a > b  ? 1 : 0);
                    */
                    Vector<Double> doublestmp = new Vector<>();
                    doublestmp.addAll(doubles);

                    Collections.sort(doublestmp);
                    out.println("<h2>Liczby posortowane rosnaco:</h2>");

                    for(Double d : doublestmp){
                        out.println("<h3>"+String.valueOf(d)+"</h3>");
                    }
                }

            }else{
                out.println("<h2>ERROR:</h2>");
            }
            out.println("</body></html>");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
