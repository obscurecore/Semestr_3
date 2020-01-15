<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 09.12.2019
  Time: 15:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<!--SCOPE :Req,Session,Application-->
<jsp:useBean id="Student" class="ru.potapov.MVC.Bean.StudentB" ></jsp:useBean>
${Student.name}
${Student["name"]}
</body>
</html>
