<%--
  Created by IntelliJ IDEA.
  User: Mateusz
  Date: 2018-03-26
  Time: 18:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html; charset=utf-8" language="java"
         import="javax.xml.parsers.DocumentBuilderFactory,javax.xml.parsers.DocumentBuilder,org.w3c.dom.*"  %>
<%@ page import="java.io.File" %>


<html>
    <head>
      <title>Shop</title>
    </head>

    <body>

    <%
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

        try{
            File inputFile = new File("C:\\Users\\Mateusz\\Desktop\\SOA\\l3\\zad4_sklep\\src\\main\\resources\\articles.xml");

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(inputFile);

            doc.getDocumentElement().normalize();

            NodeList computerParts = doc.getElementsByTagName("part");

            %>

            <table border="1">
                <caption>Sklep</caption>
                <thead>
                <tr>
                    <th>Nazwa Produktu</th><th>Cena</th>
                </tr>
                </thead>
                <tbody>
                <%

                    for (int i = 0; i < computerParts.getLength(); i++) {
                        Node currentNode = computerParts.item(i);

                        if(currentNode.getNodeType() == Node.ELEMENT_NODE){

                            Element eElement = (Element)currentNode;

                            int partId = Integer.valueOf(eElement.getAttribute("id"));
                            String partName = eElement.getElementsByTagName("name").item(0).getTextContent();
                            String partPrice = eElement.getElementsByTagName("price").item(0).getTextContent();

                %>
                <tr>
                    <td><%= partName%></td>   <td><%= partPrice%></td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>
    <%

            }catch (Exception e) {
                e.printStackTrace();
            }
    %>
    </body>
</html>