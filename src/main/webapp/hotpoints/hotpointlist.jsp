
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>HotSPots</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >
</head>
<body>

</body>


<div class="panel-body">
    <h4>All Hot Spots</h4>
    <table class="table table-striped table-responsive">
        <thead>
        <tr>
            <th>id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Geometry</th>
            <th>Color</th>
            <th>FestivalId</th>
            <form>
                <button formmethod="get" formaction="/admin/hotpoints/addhot" class="btn btn-primary">Add</button>
            </form>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="hotPoint" items="${HotPointList}">
            <tr>
                <form>
                    <td>${hotPoint.id}</td>
                    <td>${hotPoint.name}</td>
                    <td>${hotPoint.description}</td>
                    <td>${hotPoint.geometry}</td>
                    <td  bgcolor="${hotPoint.color}"></td>
                    <td>${hotPoint.festival.name} (id:${hotPoint.festival.id})</td>
                    <td>
                        <button name="hPointId" value="${hotPoint.id}" formmethod="post" formaction="\admin\hotpoints\delete" class="btn btn-primary">Delete</button>
                        <button name="hPointId" value="${hotPoint.id}" formmethod="get" formaction="\admin\hotpoints\edit" class="btn btn-primary">Edit</button>
                    </td>
                </form>
            </tr>
        </c:forEach>
        </tbody>
    </table>

    <form>
        <button formmethod="get" formaction="\logout" class="btn btn-primary">Logout</button>
    </form>
</div>


</html>


