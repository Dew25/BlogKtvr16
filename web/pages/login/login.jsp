<%@page contentType="text/html" pageEncoding="UTF-8"%>

    <div class="login">
        <h4>Введите логин и пароль</h4>
        <div>${infoReg}</div>
        <div>${info}</div>
        <div>${error}</div>
        <form action="controller?command=checkLogin" method="POST" name="loginForm">
            <table class="login-tbl">
                <tr>
                    <td class="login-tbl-title">
                        Логин:
                    </td>
                    <td class="login-tbl-input">
                        <input type="text" name="login" id="input-login" >
                    </td>
                </tr>
                <tr>
                    <td class="login-tbl-title">
                        Пароль:
                    </td>
                    <td class="login-tbl-input">
                        <input type="password" name="password" id="input-password">
                    </td>
                </tr>
                <tr>
                    <td class="login-tbl-title">
                        &nbsp;
                    </td>
                    <td class="login-tbl-submit">
                        <input type="submit" id="input-submit" value="Вход">
                    </td>
                </tr>
                <tr>
                    <td class="login-tbl-registration" colspan="2">
                        <a href="controller?command=registration" id="registration-link">Зарегистрироваться</a>
                    </td>
                </tr>
            </table>
        </form>
    </div>


