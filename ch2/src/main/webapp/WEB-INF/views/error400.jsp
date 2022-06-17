<%@ page contentType="text/html;charset=utf-8" isErrorPage="false"%>  <!-- 예외를 보여주기 위한 page로 설정. Exception 객체 불러오기 가능 -->
													<!-- true면 상태코드 500이 된다. false 해주면 정해준 상태코드로 바뀜. -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<title>error400</title>
</head>
<body>
<h1>예외가 발생했습니다.</h1>
발생한 예외 : ${pageContext.exception}<br>
예외 메시지 : ${pageContext.exception.message}<br>
<ol>
<c:forEach items="${pageContext.exception.stackTrace}" var="i">
	<li>${i.toString()}</li>
</c:forEach>
</ol>
</body>
</html>
