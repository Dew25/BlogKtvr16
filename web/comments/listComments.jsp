<%@page contentType="text/html" pageEncoding="UTF-8"%>

<c:forEach var="comment" items="${comments}">
    <c:if test="${role eq 'ADMIN' || role eq 'EDITOR'}">
        <c:if test="${regUser.login eq comment.author}">
              <%@include file="commentAuthor.jsp" %>
        </c:if>
        <c:if test="${regUser.login ne comment.author}">
              <%@include file="formAdmin.jsp" %>
        </c:if>
        
        <!--<input id="editBtn${comment.id}"  type="button" value="Редактирование..." onclick="changeCommentBtn(${comment.id})" class="edit-change-btn">-->
        
    </c:if>
    <c:if test="${role ne 'ADMIN' && role ne 'EDITOR'}">
        <c:if test="${regUser.login eq comment.author.login}">
              <%@include file="commentAuthor.jsp" %>
        </c:if>
        <c:if test="${regUser.login ne comment.author.login}">
              <%@include file="formNotAdmin.jsp" %>
        </c:if>
    </c:if>
</c:forEach>
