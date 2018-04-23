import Car.CarChoiceHelper;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import Car.Car;

@WebServlet("/carChoice")
public class CarChoiceServlet extends HttpServlet {

    private CarChoiceHelper servletDB;

    public void init() throws ServletException {

        this.servletDB= new CarChoiceHelper();
    }

    @Override
    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");
        response.setBufferSize(8192);

        try (PrintWriter out = response.getWriter()) {
            out.println("<html lang=\"en\">"
                    + "<head><title>zad1</title></head>");

            // then write the data of the response
            out.println("<body  bgcolor=\"#ffffff\">"

                    + "<h2>Wybrales samochod:</h2>"

                    + request.getParameter("cars")

                    + "<h2>W przedziale cenowym :</h2>"

                    + request.getParameter("form_price_from") + "-" + request.getParameter("form_price_to")

                    + "<br/>"

                    + "<h2>Oto Twoje modele</h2>");

            for(Car car: this.servletDB.DB.get(request.getParameter("cars"))){

                if(car.priceMatcher(Double.valueOf(request.getParameter("form_price_from")),
                        Double.valueOf(request.getParameter("form_price_to")))){

                    out.println(car.getModel() + "  za  "  + String.valueOf(car.getPrice()) + "<br/>");
                }
            }
            out.println("</body></html>");
        }
    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
