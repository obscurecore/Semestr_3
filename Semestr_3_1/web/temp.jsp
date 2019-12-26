<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 09.12.2019
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="hello.jsp"%><!--preprocessin-->
<jsp:include page="hello.jsp"></jsp:include><!--postprocessing-->

<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:forward page="hello.jsp"></jsp:forward><!--redirect use 302 status browswe change page-->
<%=new Date()// application.addFilter()  context%>
<%! int i=5;
    %>//declaration
<%=5%>//expression
</body>
</html>
