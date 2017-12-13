<%-- 
    Document   : loginForm
    Created on : Oct 2, 2017, 9:33:22 AM
    Author     : Melnikov
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Вход</title>
    </head>
    <body>
        <h1>Введите логин и пароль</h1>
        <div>${infoReg}</div>
        <div>${info}</div>
        <div>${error}</div>
        <form action="controller?command=checkLogin" method="POST" name="loginForm">
            Логин: <input type="text" name="login" id="input-login" ><br>
            Пароль: <input type="password" name="password" id="input-password"><br>
            <input type="submit" id="input-submit" value="Вход"><br>
            <a href="controller?command=registration" id="registration-link">Зарегистрироваться</a>
        </form>
    </body>
</html>
