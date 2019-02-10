<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Admin page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
        body {
            background: #eeeeee;
        }
        .header {
            padding: 0px 10px 0px 10px;
            margin-bottom: 0px;
        }
        .left-menu {
            background: white;
            padding: 20px 0 0 0;
            min-height: 94vh;
        }
        .left-menu ul li {
            margin: 0px;
        }
        .left-menu ul li a {
            border-radius: 0px;
        }
        .nav-content {
            margin-bottom: -1px;
        }
        .edit-form label {
            font-weight: 100;
            font-size: 20px;
            margin: 10px 0 10px 0;
        }
        .add-user-button {
            margin-top: 30px;
        }
    </style>

</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div class="navbar navbar-inverse navbar-static-top header">
            <a class="navbar-brand" href="/festivals">Business Festival</a>
            <a class="navbar-brand pull-right" href="/logout">Logout</a>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-2 col-md-1 col-xs-1 left-bar left-menu">
            <ul class="nav nav-pills nav-stacked nav-content">
                <li class="active">
                    <a href="/festivals" aria-controls="admin-page" role="tab" data-toggle="tab">Festivals</a>
                </li>
                <li>
                    <a href="/users" aria-controls="user-page" role="tab" data-toggle="tab">Users</a>
                </li>
            </ul>
        </div>

        <div class="col-lg-10 col-md-11 col-xs-11">
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="admin-page">
                    <h1>Admin panel</h1>

                    <ul class="nav nav-tabs nav-content" role="tablist">
                        <li id="festival-edit-nav" class="active">
                            <a href="#editFestival" aria-controls="festival-panel" role="tab" data-toggle="tab">Festival</a>
                        </li>
                        <li id="hotpoint-list-nav">
                            <a href="#hotpoints-panel" aria-controls="hotpoints-panel" role="tab" data-toggle="tab">Hot-points</a>
                        </li>
                        <li id="eventpoint-list-nav">
                            <a href="#eventpoints-panel" aria-controls="eventpoints-panel" role="tab" data-toggle="tab">Event-points</a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="editFestival">
                            <div class="panel panel-default table-panel">
                                <div class="tab-content">
                                    <div class="panel-body">
                                        <h4>Edit festival</h4>
                                        <form action="${pageContext.request.contextPath}/admin/editFestival" method="POST">
                                            <div class="form-group edit-group">
                                                <label for="id">Id</label>
                                                <input type="text" class="form-control" id="id" name="id"
                                                       value="${festival.id}" readonly>

                                                <label for="name">Name</label>
                                                <input type="text" class="form-control" id="name" name="name"
                                                       value="${festival.name}">

                                                <label for="description">Description</label>
                                                <textarea class="form-control" id="description" rows="3" name="description"
                                                          value="${festival.description}">${festival.description}</textarea>

                                                <label for="geometry">Geometry</label>
                                                <textarea class="form-control" id="geometry" rows="4" name="geometry"
                                                          value="${festival.geometry}">${festival.geometry}</textarea>

                                                <label for="color">Color</label>
                                                <input type="color" class="form-control" id="color" name="color"
                                                       value="${festival.color}">
                                            </div>
                                            <input type="submit" class="btn btn-primary btn-block" value="Сохранить"/>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane" id="hotpoints-panel">
                            <div class="panel panel-default table-panel">
                                <div class="tab-content">
                                    <div class="panel-body">
                                        <h4>Hot-points list</h4>
                                        <div class="container-fluid">
                                            <!-- HotPoints table -->
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane" id="eventpoints-panel">
                            <div class="panel panel-default table-panel">
                                <div class="tab-content">
                                    <div class="panel-body">
                                        <h4>Event-points list</h4>
                                        <div class="container-fluid">
                                            <table class="table table-striped table-responsive">
                                                <thead>
                                                <tr>
                                                    <th>id</th>
                                                    <th>Name</th>
                                                    <th>Description</th>
                                                    <th>Geometry</th>
                                                    <th>Color</th>
                                                    <th>FestivalId</th>
                                                    <th><a id="addEventpointButton" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/admin/eventpoints/create">Add</a></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="eventPoint" items="${eventPointsList}">
                                                    <tr>
                                                        <form>
                                                            <td width="5%">${eventPoint.id}</td>
                                                            <td width="15%">${eventPoint.name}</td>
                                                            <td width="30%">${eventPoint.description}</td>
                                                            <td width="30%">${eventPoint.geometry}</td>
                                                            <!--<td width="10%" style="background-color:${eventPoint.color};"></td> -->
                                                            <td width="20%">${eventPoint.festival.name} (id:${eventPoint.festival.id})</td>
                                                            <td>
                                                                <button name="eventPointId" value="${eventPoint.id}" formmethod="get" formaction="\admin\eventpoints\edit" class="btn btn-primary">Edit</button>
                                                                <button name="eventPointId" value="${eventPoint.id}" formmethod="post" formaction="\admin\eventpoints\delete" class="btn btn-primary">Delete</button>
                                                            </td>
                                                        </form>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div role="tabpanel" class="tab-pane" id="user-page">

                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>