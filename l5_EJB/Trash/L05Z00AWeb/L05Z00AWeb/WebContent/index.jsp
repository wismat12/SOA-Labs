<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Egzaminy</title>
</head>
<body>
	<form action="ExecuteEjbServlet">
		<input type="submit" value="Dostępne egzaminy" />
	</form>
	<hr>
	<c:forEach items="${exams}" var="exam">
		<p>
			<a href="ExecuteEjbServlet?exam=${exam.name}">${exam.name}</a>
		</p>
	</c:forEach>
</body>
</html>