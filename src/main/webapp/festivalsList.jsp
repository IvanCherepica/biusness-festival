<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <title>Festivals</title>

    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>

</head>


<body>

<div class="container-fluid">
    <div class="row">
        <div class="panel panel-info">
            <div class="navbar navbar-inverse navbar-static-top header">
                <a class="navbar-brand" href="/user">Business Festival</a>
                <a class="navbar-brand pull-right" href="/logout">Logout</a>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-2 col-md-1 col-xs-1 left-bar left-menu">
            <ul class="nav nav-pills nav-stacked nav-content">
                <li class="active" role="presentation">
                    <a href="/admin/festivals" aria-controls="admin-page">Festivals</a>
                </li>
                <li role="presentation">
                    <a href="/admin/users" aria-controls="user-page" role="tab" data-toggle="tab">Users</a>
                </li>
            </ul>
        </div>

        <div class="col-lg-10 col-md-11 col-xs-11">
            <h4>All festivals</h4>
            <table class="table table-striped table-responsive">
                <thead>
                    <tr>
                        <th>id</th>
                        <th>Name</th>
                        <th>Description</th>
                        <th>Geometry</th>
                        <th>Color</th>
                        <th><a id="addButton" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/admin/addFest">Add</a></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="festival" items="${FestivalsList}">
                        <tr>
                            <td width="5%" scope="row">${festival.id}</td>
                            <td width="15%">${festival.name}</td>
                            <td width="25%">${festival.description}</td>
                            <td width="25%"
                                style="white-space: nowrap;
                                overflow: hidden;
                                text-overflow: ellipsis;
                                max-width: 25vw;">
                                    ${festival.geometry}</td>
                            <td width="5%" bgcolor="${festival.color}"></td>
                            <td width="20%" class="form-group">
                                <form  class="form-inline">
                                    <button type="submit" class="btn btn-primary" formmethod="get" formaction="${pageContext.request.contextPath}/admin/editFestival" name="festivalId" value="${festival.id}">Edit</button>
                                    <button type="submit" class="btn btn-primary" formmethod="get" formaction="${pageContext.request.contextPath}/admin/deleteFestival" name="festivalId" value="${festival.id}">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>


