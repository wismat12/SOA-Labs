<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-03-26
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <head>
      <title>Shop</title>
    </head>
    <body>
        <table border="3">
            <caption>Sklep</caption>
            <thead>
            <tr>
                <th>Identyfikator</th><th>Nazwa Produktu</th><th>Cena</th><th>Akcja</th>
            </tr>
            </thead>
            <tbody>

                <c:import var = "computerParts" url = "http://localhost:8080/zad4_sklep/articles.xml"/>

                <x:parse xml = "${computerParts}" var = "output"/>

                <x:forEach select = "$output/computer_parts/part" var = "part">

                    <form method="post" action="/zad4_sklep/basket.jsp">

                        <input type="hidden" name="addedToBasket" value="<x:out select = "$part/@id" />"/>
                        <tr>
                            <td><x:out select = "$part/@id" /></td>
                            <td><x:out select = "$part/name" /></td>

                            <c:set var="priceVar">                  <%-- zapisuje do zmiennej w var to co w out --%>
                                <x:out select = "$part/price" />
                            </c:set>
                            <td align="right">
                                <fmt:setLocale value = "pl_PL"/>                     <%-- ustawienie formatowania waluty --%>
                                <fmt:formatNumber value = "${priceVar}" type = "currency"/>
                            </td>
                            <td><input type="submit" value="kupuję"></td>
                        </tr>
                    </form>
                </x:forEach>
            </tbody>
        </table>
        <form method="post" action="/zad4_sklep/basket.jsp">
            <br/><input type="submit" value="Przejdz do koszyka">
        </form>
    </body>
</html>