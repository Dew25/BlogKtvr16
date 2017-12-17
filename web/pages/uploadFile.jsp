<%-- 
    Document   : uploadFile
    Created on : 19.11.2017, 16:27:45
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

        <h1>Загрузить файл!</h1>
        <form action="upload" method="POST" enctype="multipart/form-data" >
            <input type="file" name="file">
            <br>
            <input type="submit" value="Загрузить">
        </form>


