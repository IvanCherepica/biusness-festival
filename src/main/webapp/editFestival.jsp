<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>Admin page</title>
    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css" >

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
</head>

<body>

<div class="container-fluid">
    <div class="row">
        <div class="navbar navbar-inverse navbar-static-top header">
            <a class="navbar-brand" href="/admin/festivals">Business Festival</a>
            <a class="navbar-brand pull-right" href="/admin/users">Logout</a>
        </div>
    </div>
    <div class="row">
        <div class="col-lg-2 col-md-1 col-xs-1 left-bar left-menu">
            <ul class="nav nav-pills nav-stacked nav-content">
                <li class="active" role="presentation">
                    <a href="/admin/festivals" aria-controls="admin-page">Festivals</a>
                </li>
                <li>
                    <a href="/admin/users" aria-controls="user-page">Users</a>
                </li>
            </ul>
        </div>

        <div class="col-lg-10 col-md-11 col-xs-11">
            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="admin-page">
                    <h2>Admin panel</h2>
                    <h4> ${festival.name} (${festival.id})</h4>
                    <ul class="nav nav-tabs nav-content" role="tablist">
                        <li id="festival-edit-nav" class="active">
                            <a href="#festival-panel" aria-controls="festival-panel" role="tab" data-toggle="tab">Festival</a>
                        </li>
                        <li id="hotpoint-list-nav">
                            <a href="#hotpoints_panel" aria-controls="hotpoints_panel" role="tab" data-toggle="tab">Hot-points</a>
                        </li>
                        <li id="eventpoint-list-nav">
                            <a href="#eventpoints-panel" aria-controls="eventpoints-panel" role="tab" data-toggle="tab">Event-points</a>
                        </li>
                        <li id="event-list-nav">
                            <a href="#events-panel" aria-controls="events-panel" role="tab" data-toggle="tab">Events</a>
                        </li>
                    </ul>

                    <div class="tab-content">
                        <div role="tabpanel" class="tab-pane active" id="festival-panel">
                            <div class="panel panel-default table-panel">
                                <div class="tab-content">
                                    <div class="panel-body">

                                        <h4>Edit festival</h4>
                                        <form action="${pageContext.request.contextPath}/admin/editFestival" method="POST">
                                            <div class="form-group edit-group col-md-9">
                                                <div class="form-group row">
                                                    <div class="col-xs-9 col-md-2">
                                                        <label for="id">Id</label>
                                                    </div>
                                                    <div class="col-xs-9 col-md-6">
                                                        <input type="text" class="form-control" id="id" name="id"
                                                               value="${festival.id}" readonly>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div  class="col-xs-9 col-md-2">
                                                        <label for="name">Name</label>
                                                    </div>
                                                    <div  class="col-xs-9 col-md-6">
                                                        <input type="text" class="form-control" id="name" name="name"
                                                               value="${festival.name}">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div  class="col-xs-9 col-md-2">
                                                        <label for="description">Description</label>
                                                    </div>
                                                    <div  class="col-xs-9 col-md-6">
                                                        <textarea class="form-control" id="description" rows="3" name="description" style="max-width: 25vw;"
                                                                  value="${festival.description}">${festival.description}</textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div  class="col-xs-9 col-md-2">
                                                        <label for="geometry">Geometry</label>
                                                    </div>
                                                    <div  class="col-xs-9 col-md-6">
                                                        <textarea class="form-control" id="geometry" rows="4" name="geometry"
                                                                  style="white-space: nowrap;
                                                                overflow: hidden;
                                                                text-overflow: ellipsis;
                                                                max-width: 25vw;"
                                                                  value="${festival.geometry}">${festival.geometry}</textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div  class="col-xs-9 col-md-2">
                                                        <label for="center">Center</label>
                                                    </div>
                                                    <div  class="col-xs-9 col-md-6">
                                                        <textarea class="form-control" id="center" rows="4" name="center"
                                                                  style="white-space: nowrap;
                                                                overflow: hidden;
                                                                text-overflow: ellipsis;
                                                                max-width: 25vw;"
                                                                  value="${festival.center}">${festival.center}</textarea>
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div  class="col-xs-9 col-md-2">
                                                        <label for="radius">Radius</label>
                                                    </div>
                                                    <div  class="col-xs-9 col-md-6">
                                                        <input class="form-control" id="radius" name="radius"
                                                                  value="${festival.radius}">
                                                    </div>
                                                </div>
                                                <div class="form-group row">
                                                    <div  class="col-xs-9 col-md-2">
                                                        <label for="color">Color</label>
                                                    </div>
                                                    <div  class="col-xs-9 col-md-6">
                                                        <input type="color" class="form-control" id="color" name="color"
                                                               value="${festival.color}">
                                                    </div>
                                                </div>
                                                <div  class="col-xs-9 col-md-6">
                                                    <input type="submit" class="btn btn-primary btn-block" value="Сохранить"/>
                                                </div>
                                            </div>

                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div id="hotpoints_panel" role="tabpanel" class="tab-pane">
                            <div class="panel panel-default table-panel">
                                <div class="tab-content">
                                    <div class="panel-body">
                                        <h4>Hot-points list</h4>
                                        <div class="container-fluid">
                                            <table class="table table-striped table-responsive">
                                                <thead>
                                                <tr>
                                                    <th>id</th>
                                                    <th>Name</th>
                                                    <th>Description</th>
                                                    <th>Geometry</th>
                                                    <th>Color</th>

                                                    <th><a id="addHotpointButton" class="btn btn-primary" href="/admin/hotpoints/addhot">Add</a></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="hotPoint" items="${hotPointList}">
                                                    <tr id="hprow${hotPoint.id}">
                                                        <td width="5%">${hotPoint.id}</td>
                                                        <td width="15%">${hotPoint.name}</td>
                                                        <td width="25%">${hotPoint.description}</td>
                                                        <td width="25%">${hotPoint.geometry}</td>
                                                        <td width="5%" style="background-color:${hotPoint.color};"></td>
                                                        <td width="20%">
                                                            <form  class="form-inline hp-edit-form">
                                                                <a id="hpEditButton${hotPoint.id}" class="btn btn-primary">Edit</a>
                                                                <a id="hpDeleteButton" onclick="deleteHotPoint(${hotPoint.id}, ${hotPoint.festival.id})" name="hotPointId" class="btn btn-primary">Delete</a>
                                                            </form>
                                                        </td>
                                                        <script type="text/javascript">
                                                            jQuery(document).ready( function() {
                                                                jQuery("#hpEditButton${hotPoint.id}").click(function(){
                                                                    putHotpointValues("${hotPoint.id}", "${hotPoint.festival.id}", "${hotPoint.name}", "${hotPoint.description}", "${hotPoint.geometry}", "${hotPoint.color}");
                                                                    $("#editHotpointModal").modal('show');
                                                                });
                                                            })

                                                        </script>
                                                    </tr>
                                                </c:forEach>
                                                </tbody>
                                            </table>
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

                                                    <th><a id="addEventpointButton" class="btn btn-primary" href="${pageContext.servletContext.contextPath}/admin/eventpoints/create">Add</a></th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                <c:forEach var="eventPoint" items="${eventPointsList}">

                                                    <tr id="eprow${eventPoint.id}">
                                                        <td width="5%">${eventPoint.id}</td>
                                                        <td width="15%">${eventPoint.name}</td>
                                                        <td width="25%">${eventPoint.description}</td>
                                                        <td width="25%">${eventPoint.geometry}</td>
                                                        <td width="5%" style="background-color:${eventPoint.color};"></td>

                                                        <td width="20%">
                                                            <form  class="form-inline ep-edit-form">
                                                                <a id="epEditButton${eventPoint.id}" class="btn btn-primary">Edit</a>
                                                                <a id="epDeleteButton" onclick="deleteEventPoint(${eventPoint.id}, ${eventPoint.festival.id})" name="eventPointId" class="btn btn-primary">Delete</a>
                                                            </form>
                                                        </td>
                                                        <script type="text/javascript">
                                                            jQuery(document).ready( function() {
                                                                jQuery("#epEditButton${eventPoint.id}").click(function(){
                                                                    putEventpointValues("${eventPoint.id}", "${eventPoint.festival.id}", "${eventPoint.name}", "${eventPoint.description}", "${eventPoint.geometry}", "${eventPoint.color}");
                                                                    $("#editEventpointModal").modal('show');
                                                                });
                                                            })
                                                        </script>
                                                    </tr>

                                                </c:forEach>

                                                </tbody>
                                            </table>


                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div role="tabpanel" class="tab-pane" id="events-panel">
                            <div class="panel panel-default table-panel">
                                <div class="tab-content">
                                    <div class="panel-body">
                                        <h4>Events list</h4>
                                        <div class="container-fluid">
                                            <table class="table table-striped table-responsive">
                                                <thead>
                                                <tr>
                                                    <th>id</th>
                                                    <th>Name</th>
                                                    <th>Description</th>
                                                    <th>Event-point</th>

                                                    <th><a id="addEventButton" onclick="addEvent(${festival.id})"class="btn btn-primary">Add</a></th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <c:forEach var="event" items="${eventList}">
                                                    <tr id="erow${event.id}">
                                                        <td width="5%">${event.id}</td>
                                                        <td width="15%">${event.name}</td>
                                                        <td width="25%">${event.description}</td>
                                                        <td width="25%">${event.eventPoint.name}</td>
                                                        <td width="20%">
                                                            <form  class="form-inline ev-edit-form">
                                                                <a id="evEditButton${event.id}" class="btn btn-primary">Edit</a>
                                                                <a id="evDeleteButton" onclick="deleteEvent(${event.id}, ${festival.id})" class="btn btn-primary">Delete</a>
                                                            </form>

                                                        </td>
                                                        <script type="text/javascript">
                                                            jQuery(document).ready( function() {
                                                                jQuery("#evEditButton${event.id}").click(function(){
                                                                    putEventValues("${event.id}", "${event.eventPoint.id}", "${festival.id}", "${event.name}", "${event.description}", "${event.dateBegin}", "${event.dateEnd}");
                                                                    editEvent("${festival.id}");
                                                                });
                                                            })

                                                        </script>
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



                </div>
                <div role="tabpanel" class="tab-pane" id="user-page">

                </div>
            </div>
        </div>
    </div>



<!-- Modal edit Eventpoint-->
<div id="editEventpointModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Event-point</h4>
            </div>
            <div class="modal-body">
                <div class="raw">
                    <label for="festival_id">Festival id:</label>
                    <input id="festival_id" name="festival_id" readonly>

                    <label for="ep-id">ID:</label>
                    <input id="ep-id" name="eventPointId" readonly>
                </div>

                <div class="raw">
                    <label for="ep-name">Name</label>
                    <input id="ep-name" placeholder="введите имя" name="name" class="form-control" required>
                </div>

                <label for="ep-description">Description</label>
                <textarea id="ep-description" placeholder="введите краткое описание" name="description"
                          class="form-control" rows="3"></textarea>

                <label for="ep-geometry">Geometry</label>
                <textarea id="ep-geometry" placeholder="введите координаты" name="geometry" class="form-control" rows="3"></textarea>

                <label for="ep-color">Color</label>
                <input id="ep-color" type="color" name="color" class="form-control">
            </div>
            <div class="modal-footer">
                <form  class="form-inline">
                    <button id="ep-save-btn" type="button" class="btn btn-primary">Save</button>
                    <button id="ep-close-btn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>

    </div>
</div>

<!-- Modal edit Hottpoint-->
<div id="editHotpointModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Event-point</h4>
            </div>
            <div class="modal-body">
                <div class="raw">
                    <label for="hfestival_id">Festival id:</label>
                    <input id="hfestival_id" name="festival_id" readonly>

                    <label for="hp-id">ID:</label>
                    <input id="hp-id" name="eventPointId" readonly>
                </div>

                <div class="raw">
                    <label for="hp-name">Name</label>
                    <input id="hp-name" placeholder="введите имя" name="name" class="form-control" required>
                </div>

                <label for="hp-description">Description</label>
                <textarea id="hp-description" placeholder="введите краткое описание" name="description"
                          class="form-control" rows="3"></textarea>

                <textarea id="hp-geometry" placeholder="введите координаты" name="geometry" class="form-control" rows="3"></textarea>
                <label for="hp-color">Color</label>
                <input id="hp-color" type="color" name="color" class="form-control">
            </div>
            <div class="modal-footer">
                <form  class="form-inline">
                    <button id="hp-save-btn" type="button" class="btn btn-primary">Save</button>
                    <button id="hp-close-btn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>

    </div>
</div>

<!-- Modal edit Events-->
<div id="editEventModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Event</h4>
            </div>
            <div class="modal-body">
                <div class="raw">
                    <label for="efestival_id">Festival id:</label>
                    <input id="efestival_id" name="festival_id" readonly>

                    <label for="ev-id">ID:</label>
                    <input id="ev-id" name="eventPointId" readonly>
                </div>

                <div class="raw">
                    <label for="ev_eventPoint">Event-point id:</label>
                    <select id="ev_eventPoint" name="eventpoint_id">
                        <c:forEach var="item" items="${eventPointsList}">
                            <option value="${item.id}">${item.name} (id:${item.id})</option>

                        </c:forEach>
                    </select>
                    <!-- ${item.festival_id == id ? 'selected="selected"' : ''} -->
                </div>

                <div class="raw">
                    <label for="ev-name">Name</label>
                    <input id="ev-name" placeholder="введите имя" name="name" class="form-control" required>
                </div>

                <div class="raw">
                    <label for="ev-description">Description</label>
                    <textarea id="ev-description" placeholder="введите краткое описание" name="description"
                          class="form-control" rows="3"></textarea>
                </div>

                <div class="raw">
                    <div class="col-xs-6">
                        <div class="form-group">
                            <div class="input-group date" id="datetimepicker7">
                                <input id="ev-date-from" type="text" pattern="dd.MM.yyyy HH:mm" class="form-control"/>
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                    <div class="col-xs-6">
                        <div class="form-group">
                            <div class="input-group date" id="datetimepicker8">
                                <input id="ev-date-to" type="text" pattern="dd.MM.yyyy HH:mm" class="form-control"/>
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                </span>
                            </div>
                        </div>
                    </div>
                </div>


            <!--
                    <div class="input-group mb-2">
                        <div class="input-group" id="datetimepicker2">
                            <div class="input-group-prepend">
                                <label class="input-group-text">From-To</label>
                            </div>
                            <input id="ev-date-from" type="datetime-local" class="form-control">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                </span>
                            <input id="ev-date-to" type="datetime-local" class="form-control">
                                <span class="input-group-addon">
                                    <i class="glyphicon glyphicon-calendar"></i>
                                </span>
                    </div>
                </div>-->
                 <input id="ev-q-type" type="hidden" readonly>


            </div>


            <div class="modal-footer">
                <form  class="form-inline">
                    <button id="ev-save-btn" type="button" class="btn btn-primary">Save</button>
                    <button id="ev-close-btn" type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                </form>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">

    function deleteEventPoint(id, festivalId) {
        console.log("Delete event point button "+festivalId);
        var data = { festivalId: festivalId, eventPointId: id };
        $.get( "/admin/eventpoints/delete", data );
        $("#eprow"+id).remove();

    }

    function deleteHotPoint(id, festivalId) {
        console.log("Delete hotpoint button "+festivalId);
        var data = { festivalId: festivalId, hotPointId: id };
        $.get( "/admin/hotpoints/delete", data );
        console.log($("#hprow"+id));
        $("#hprow"+id).remove();
    }
    function addEvent(festivalId) {
        $('#ev-q-type').val("new");
        $('#efestival_id').val(festivalId);
        $('#ev-name').val("");
        $('#ev-description').val("");
        $('#ev-date-from').val("");
        $('#ev-date-to').val("");
        $("#editEventModal").modal('show');
    }
    function editEvent(festivalId) {
        $('#ev-q-type').val("");
        $('#efestival_id').val(festivalId);
        $("#editEventModal").modal('show');
    }
    function deleteEvent(id, festivalId) {
        console.log("Delete button "+festivalId);
        var data = { festivalId: festivalId, eventId: id };
        $.get( "/admin/events/delete", data );
        console.log($("#erow"+id));
        $("#erow"+id).remove();
    }

    function putEventpointValues(id, festival_id, name, description, geometry, color ) {
        $('#ep-id').val(id);
        $('#festival_id').val(festival_id);
        $('#ep-name').val(name);
        $('#ep-description').val(description);
        $('#ep-geometry').val(geometry);
        $('#ep-color').val(color);
    }

    function putHotpointValues(id, festival_id, name, description, geometry, color ) {
        $('#hp-id').val(id);
        $('#hfestival_id').val(festival_id);
        $('#hp-name').val(name);
        $('#hp-description').val(description);
        $('#hp-geometry').val(geometry);
        $('#hp-color').val(color);
    }

    function putEventValues(id, eventpoint_id, festival_id, name, description, dateFrom, dateTo) {
        $('#efestival_id').val(festival_id);
        $('#ev_eventPoint').val(eventpoint_id);
        $('#ev-id').val(id);
        $('#ev-name').val(name);
        $('#ev-description').val(description);
        $('#ev-date-from').val(dateFrom);
        $('#ev-date-to').val(dateTo);
    }

    $(document).ready(function($){

        //задание заполнителя с помощью параметра placeholder
        // $("#ev-date-from").mask("99.99.9999 99:99", {placeholder: "дд.мм.гггг чч:мм" });
        // $("#ev-date-to").mask("99.99.9999 99:99", {placeholder: "дд.мм.гггг чч:мм" });

        $("#datetimepicker7").datetimepicker({
            locale: 'ru',
            stepping: 10,
            format: "DD.MM.YYYY HH:mm"
        });
        $("#datetimepicker8").datetimepicker({
            locale: 'ru',
            stepping: 10,
            format: "DD.MM.YYYY HH:mm"
        });

        $("#ep-save-btn").click(function(){
            $.ajax({
                url : '/admin/eventpoints/edit',     // URL - сервлет
                type : "POST",
                data : {                 // передаваемые сервлету данные
                    festivalId : $('#festival_id').val(),
                    eventPointId : $('#ep-id').val(),
                    name : $('#ep-name').val(),
                    description : $('#ep-description').val(),
                    geometry : $('#ep-geometry').val(),
                    color : $('#ep-color').val()
                },
                success : function() {
                    // обработка ответа от сервера
                    $('#ep-close-btn').click();

                }
            });
        })

        $("#hp-save-btn").click(function(){
            $.ajax({
                url : '/admin/hotpoints/edit',     // URL - сервлет
                type : "POST",
                data : {                 // передаваемые сервлету данные
                    festivalId : $('#hfestival_id').val(),
                    hotPointId : $('#hp-id').val(),
                    name : $('#hp-name').val(),
                    description : $('#hp-description').val(),
                    geometry : $('#hp-geometry').val(),
                    color : $('#hp-color').val()
                },
                success : function(data) {
                    //alert('wow');
                    //window.location.href = "/admin/editFestival";
                },
                error : function (error) {
                    console.log(error);
                }
            });
        })

        $("#ev-save-btn").click(function(){
            console.log("Event save button = "+$('#ev-q-type').val());
            $.ajax({
                type : "POST",
                url : ($('#ev-q-type').val()=="new") ? '/admin/events/add' : '/admin/events/edit' ,
                data : {                 // передаваемые сервлету данные
                    festivalId : $('#efestival_id').val(),
                    eventId : $('#ev-id').val(),
                    name : $('#ev-name').val(),
                    description : $('#ev-description').val(),
                    eventPointId : $('#ev_eventPoint').val(),
                    dateBegin : $('#ev-date-from').val(),
                    dateEnd  :$('#ev-date-to').val()
                },
                success : function() {
                    // обработка ответа от сервера
                    $('#ev-close-btn').click();
                    $("#admin-page").click();
                }
            });
        })

        $("#hotpoints_panel").click(function() {
            console.log("${festival.id}");
            $.ajax({
                url : '/admin/hotpoints/list',     // URL - сервлет
                type : "GET",
                data : {                 // передаваемые сервлету данные
                    id : "${festival.id}"
                },
                success : function(response) {
                    // обработка ответа от сервера
                    $("#hotpoints_panel").appendChild(response.response.outputStream.ob.cb.toString());
                }
            });
        })
    })

</script>

</body>
</html>