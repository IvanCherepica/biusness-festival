<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<ul class="nav nav-sidebar">
    <li role="presentation" <c:if test="${param.selected =='festivals'}">class="active"</c:if>>
        <a href="/admin/festivals">Фестивали</a>
    </li>
    <li role="presentation" <c:if test="${param.selected =='users'}">class="active"</c:if>>
        <a href="/admin/users">Пользователи</a>
    </li>
</ul>


