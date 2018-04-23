<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-03-26
  Time: 21:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <jsp:useBean id="userBasket" class="beans.Basket" scope="session"/>

    <head>
        <title>Basket</title>
    </head>
        <%
            if(request.getParameter("addedToBasket") != null)
                userBasket.addProduct(request.getParameter("addedToBasket"));

            if(request.getParameter("deletedFromBasket") != null)
                userBasket.deleteProduct(request.getParameter("deletedFromBasket"));
        %>
    <body>
        <h2>Twoj koszyk <% if(userBasket.getUserBasket().isEmpty()){
                                out.print("jest pusty");
                            }else out.print(":");  %>
        </h2>
        <form method="post" action="/zad4_sklep/shop.jsp">
            <br/><input type="submit" value="Przejdz do sklepu">
        </form>

        <c:import var = "computerParts" url = "http://localhost:8080/zad4_sklep/articles.xml"/>
        <x:parse xml = "${computerParts}" var = "output"/>

        <table border="3">
            <thead>
                <tr>
                    <th>Nazwa Produktu</th><th>Ilosc sztuk</th><th>Cena sumaryczna</th><th>Akcje</th>
                </tr>
            </thead>
            <tbody>
                <%--<c:forEach var="productKey" items="${userBasket.userBasket.keySet()}">--%>
                <%
                    for(String keyStr : userBasket.getUserBasket().keySet()){
                        int keyAtrr = Integer.valueOf(keyStr);                       //musi byc int,  nie String
                %>
                    <c:set var="keyAtrr" value="<%=keyAtrr%>"/>                         <%-- zeby miec dostep do zmiennej javowej ze scriptletu w <x:out select --%>
                    <c:set var="priceAtrr">                                             <%-- Atrybut wartosci ceny przedmiotu--%>
                         <x:out select = "$output/computer_parts/part[$keyAtrr]/price" />   <%--po keyAttr czyli id wybieram odpowiednia czesc z xmla --%>
                    </c:set>

                    <form method="post" action="/zad4_sklep/basket.jsp">
                        <input type="hidden" name="deletedFromBasket" value="<%=keyAtrr%>" />  <%-- mowi ktora pozycja ma zostac usunieta --%>

                        <tr>
                            <td><x:out select = "$output/computer_parts/part[$keyAtrr]/name" /></td>
                            <%
                                int amount = userBasket.getProductAmount(keyStr);
                            %>
                            <td><%=amount%></td>
                            <%
                                String pay = (String)pageContext.getAttribute("priceAtrr");  // wczytanie atrybutu ktory byl wczesniej ustawiony w set - cena z bazy
                                Double payment = amount * Double.valueOf(pay);
                            %>
                            <td align="right">
                                <fmt:setLocale value = "pl_PL"/>                     <%-- ustawienie formatowania waluty --%>
                                <fmt:formatNumber value = "<%=payment%>" type = "currency"/>
                            </td>
                            <td><input type="submit" value="X"></td>
                        </tr>
                    </form>
                <%
                    }
                %>
            </tbody>
       <%-- </c:forEach>--%>
    </body>
</html>
