
<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Регистрация</h1>
        <form name="controller?command=addNewUser" action="controller?command=addNewUser" method="POST">
            Логин: <input type="text" name="login" value="pupil"><br>
            Пароль: <input type="password" name="password1" value="123"><br>
            Повторите пароль: <input type="password" name="password2" value="123"><br>
            Email: <input type="email" name="email" value="admin@ktvr.ee"><br>
            Имя: <input type="submit" value="Зарегистрировать">
        </form>

