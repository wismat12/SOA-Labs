<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-03-27
  Time: 14:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
    <c:import var = "moviesList" url = "http://localhost:8080/zad5filmy/movies.xml"/>

    <x:parse xml = "${moviesList}" var = "output"/>
  <head>
    <title>moviesList</title>
  </head>
  <body>
        <h1>Lista filmów</h1>
        <table border="3">
            <thead>
            <tr>
                <th>Tytuł</th><th>Gatunek</th><th>Rok</th><th>Dochód</th>
            </tr>
            </thead>
            <x:forEach select = "$output/movies/movie" var = "movie">
                <tr>
                    <td><x:out select = "$movie/title" /></td>

                    <c:set var="movieGenre">                  <%-- zapisuje do zmiennej w var to co w out --%>
                        <x:out select = "$movie/genre" />
                    </c:set>

                    <c:choose>
                        <c:when test="${movieGenre == 'wojenny'}">
                            <td style="background-color: cadetblue">${movieGenre}</td>
                        </c:when>
                        <c:otherwise>
                            <td>${movieGenre}</td>
                        </c:otherwise>
                    </c:choose>

                    <td><x:out select = "$movie/year" /></td>

                    <c:set var="boxOfficeVar">                  <%-- zapisuje do zmiennej w var to co w out --%>
                        <x:out select = "$movie/boxOffice" />
                    </c:set>
                    <td align="right">
                        <fmt:setLocale value = "en_US"/>                     <%-- ustawienie formatowania waluty --%>
                        <fmt:formatNumber value = "${boxOfficeVar}" type = "currency"/>
                    </td>
                </tr>
            </x:forEach>
        </table>
  </body>
</html>
