<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 09.12.2019
  Time: 14:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:useBean id="person" class ="ru.potapov.Bean.Person" scope="application"/>

<jsp:setProperty name="person" property="name" value="Max"/>
<jsp:getProperty name="person" property="name"/>
</body>
</html>
