<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Add User</title>

    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
   </head>

<body>
<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-6 col-xs-11 col-md-offset-3 col-xs-offset-2 edit-form">
            <div class="col-xs-8 col-md-offset-2">
                <h2>Создание пользователя</h2>
                <form action="${pageContext.request.contextPath}/admin/addUser" method="POST">
                    <div class="form-group edit-group">

                        <label for="name">Name</label>
                        <input type="text" class="form-control" id="name" name="name"
                               placeholder="${user.name}">

                        <label for="password">Password</label>
                        <input class="form-control" id="password" rows="3" name="password"
                                  placeholder="${user.password}">

                        <label for="role">Role</label>
                        <select class="form-control" id="role" rows="2" name="role"
                                  placeholder="${user.role}">
                            <option>user</option>
                            <option>admin</option>
                        </select>
                    </div>
                    <input type="submit" class="btn btn-primary btn-block" value="Создать"/>
                </form>
            </div>
        </div>
    </div>
</div>
</body>
</html>