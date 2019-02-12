<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!-- 현재 페이지의 데이터는 세션에 담지 않는다.  -->
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello world!  
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
