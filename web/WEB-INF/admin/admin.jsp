<%-- 
    Document   : admin
    Created on : 19.11.2017, 19:38:22
    Author     : jvm
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="resources/css/admin.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <div class="wrapper">

            <h3>Администрирование пользователей!</h3>
           
            <div class="content">
                
                <form action="controller?command=setRole" method="POST">
                    <select name="selectUser">
                        <c:forEach var="entry" items="${mapUsers}">
                            <option value="${entry.key.id}">${entry.key.login} (роль: ${entry.value}) [активен: ${entry.key.active}]</option>
                        </c:forEach>
                    </select>
                    <select name="selectRole">
                        <option value="activeFalse">Деактивировать</option>
                        <option value="activeTrue">Активировать</option>
                        <option value="delete">Удаление роли</option>
                        <c:forEach var="role" items="${roles}">
                            <option value="${role}">Назначить роль: ${role}</option>
                        </c:forEach>
                    </select>
                    <br><br>
                    <input type="submit" value="Назначить">
                </form>
                
            </div>    

        </div>
    </body>
</html>
