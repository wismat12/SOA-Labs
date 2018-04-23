<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-03-26
  Time: 13:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.Random" %>
<%@ page import="beans.NumberToGuess" %>
<html>
  <head>
    <title>zad3</title>

  </head>
  <%--
      <jsp:useBean id="numberToGuess" class="beans.NumberToGuess" scope="session"/>
      <jsp:useBean id="counter" class="beans.Counter" scope="session"/>
   --%>
  <body>
        <%
            int numberToGuess;
            int counter;
            if(request.getParameter("numberToGuess")!= null){
                numberToGuess = Integer.valueOf(request.getParameter("numberToGuess"));
            }else{
                numberToGuess = (int)(Math.random() * 101);
            }
            if(request.getParameter("counter")!= null){
                counter = Integer.valueOf(request.getParameter("counter")) + 1;
            }else{
                counter = 0;
            }
        %>

        <form method="post" action="/zad3_zgadywanieLiczby/zad3.jsp">

            Podaj liczbe: <%-- <%=numberToGuess.getNumber()%> ilosc razy <%=counter.getCounterNumber()%> --%>

            <input type="text" name="user_number" />
            <input type="hidden" name="numberToGuess" value="<%=numberToGuess%>"/>
            <input type="hidden" name="counter" value="<%=counter%>"/>
            <input type="submit" value="Zgaduj"><br />
        </form>
            <%
            if(request.getParameter("user_number")!=null){

                if(Integer.valueOf(request.getParameter("user_number")) > numberToGuess){
                    out.print("Twoja liczba ("+Integer.valueOf(request.getParameter("user_number"))+") jest <b>wieksza</b> niz zagadka");

                }else if(Integer.valueOf(request.getParameter("user_number")) < numberToGuess){
                    out.print("Twoja liczba ("+Integer.valueOf(request.getParameter("user_number"))+") jest <b>mniejsza</b> niz zagadka");

                }else{
                    out.println("Brawo, zadla(e)s po "+counter +" probach.");
                    %>
                        Sprobuj <a href="zad3.jsp">jeszcze raz.</a>
                    <%
                }
            }
            %>
  </body>
</html>
