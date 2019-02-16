<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://sargue.net/jsptags/time" prefix="javatime" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Редактирование фестивалей</title>

    <link rel="stylesheet" href="https://bootswatch.com/3/united/bootstrap.min.css">
    <style type="text/css">
        <%@ include file="/css/dashboard.css" %>
    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.24.0/moment.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-datetimepicker/4.17.47/js/bootstrap-datetimepicker.min.js"></script>
</head>

<body>


<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <jsp:include page="/admin/admin-top-panel.jsp"/>
    </div>
</nav>



<div class="container-fluid">
    <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
            <jsp:include page="/admin/admin-left-panel.jsp">
                <jsp:param name='selected' value='festivals'/>
            </jsp:include>
        </div>


        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
            <h1 class="page-header">Редакция фестиваля</h1>

            <div class="tab-content">
                <div role="tabpanel" class="tab-pane active" id="admin-page">
                    <h3> ${festival.name}</h3>
                    <ul class="nav nav-tabs nav-content" role="tablist">
                    <li id="festival-edit-nav">
                        <a id="festival-tab" href="#festival-panel" aria-controls="festival-panel" role="tab" data-toggle="tab">Фестиваль</a>
                    </li>
                    <li id="hotpoint-list-nav">
                        <a id="hotpoint-tab" href="#hotpoints_panel" aria-controls="hotpoints_panel" role="tab" data-toggle="tab">Хот-поинт</a>
                    </li>
                    <li id="eventpoint-list-nav">
                        <a id="eventpoint-tab" href="#eventpoints-panel" aria-controls="eventpoints-panel" role="tab" data-toggle="tab">Место Событий</a>
                    </li>
                    <li id="event-list-nav">
                        <a id="event-tab" href="#events-panel" aria-controls="events-panel" role="tab" data-toggle="tab">События</a>
                    </li>
                </ul>

                    <div class="tab-content">
                    <div role="tabpanel" class="tab-pane" id="festival-panel">
                        <div class="panel panel-default table-panel">
                            <div class="tab-content">
                                <div class="panel-body">

                                    <h4>Редакция Фестиваля</h4>
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
                                                    <label for="name">Название</label>
                                                </div>
                                                <div  class="col-xs-9 col-md-6">
                                                    <input type="text" class="form-control" id="name" name="name"
                                                           value="${festival.name}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div  class="col-xs-9 col-md-2">
                                                    <label for="description">Описание</label>
                                                </div>
                                                <div  class="col-xs-9 col-md-6">
                                                        <textarea class="form-control" id="description" rows="3" name="description" style="max-width: 25vw;"
                                                                  value="${festival.description}">${festival.description}</textarea>
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div  class="col-xs-9 col-md-2">
                                                    <label for="geometry">Координаты</label>
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
                                                    <label for="center">Центр</label>
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
                                                    <label for="radius">Радиус</label>
                                                </div>
                                                <div  class="col-xs-9 col-md-6">
                                                    <input class="form-control" id="radius" name="radius"
                                                           value="${festival.radius}">
                                                </div>
                                            </div>
                                            <div class="form-group row">
                                                <div  class="col-xs-9 col-md-2">
                                                    <label for="color">Цвет</label>
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
                                    <h4>Список Хот-Поинтов</h4>
                                    <div class="container-fluid">
                                        <table class="table table-striped table-responsive">
                                            <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>Название</th>
                                                <th>Описание</th>
                                                <th>Координты</th>
                                                <th>Цвет</th>
                                                <th><a id="addHotpointButton" onclick="addHotpoint(${festival.id})" class="btn btn-primary">Добавить</a></th>
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
                                                            <a id="hpEditButton${hotPoint.id}" class="btn btn-primary">Изменить</a>
                                                            <a id="hpDeleteButton" onclick="deleteHotPoint(${hotPoint.id}, ${hotPoint.festival.id})" class="btn btn-primary">Удалить</a>
                                                        </form>
                                                    </td>
                                                    <script type="text/javascript">
                                                        jQuery(document).ready( function() {
                                                            jQuery("#hpEditButton${hotPoint.id}").click(function(){
                                                                putHotpointValues("${hotPoint.id}", "${hotPoint.festival.id}", "${hotPoint.name}", "${hotPoint.description}", "${hotPoint.geometry}", "${hotPoint.color}");
                                                                editHotpoint("${festival.id}");
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
                                    <h4>Список Мест Событий</h4>
                                    <div class="container-fluid">

                                        <table class="table table-striped table-responsive">
                                            <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>Название</th>
                                                <th>Описание</th>
                                                <th>Координаты</th>
                                                <th>Цвет</th>
                                                <th><a id="addEventpointButton" onclick="addEventpoint(${festival.id})" class="btn btn-primary">Добавить</a></th>
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
                                                            <a id="epEditButton${eventPoint.id}" class="btn btn-primary">Изменить</a>
                                                            <a id="epDeleteButton" onclick="deleteEventPoint(${eventPoint.id}, ${eventPoint.festival.id})" name="eventPointId" class="btn btn-primary">Удалить</a>
                                                        </form>
                                                    </td>
                                                    <script type="text/javascript">
                                                        jQuery(document).ready( function() {
                                                            jQuery("#epEditButton${eventPoint.id}").click(function(){
                                                                putEventpointValues("${eventPoint.id}", "${eventPoint.festival.id}", "${eventPoint.name}", "${eventPoint.description}", "${eventPoint.geometry}", "${eventPoint.color}");
                                                                editEventPoint("${festival.id}");
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
                                    <h4>Список Событий</h4>
                                    <div class="container-fluid">
                                        <table class="table table-striped table-responsive">
                                            <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>Название</th>
                                                <th>Описсание</th>
                                                <th>Место Событий</th>

                                                <th><a id="addEventButton" onclick="addEvent(${festival.id})"class="btn btn-primary">Добавить</a></th>
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
                                                            <a id="evEditButton${event.id}" class="btn btn-primary">Изменить</a>
                                                            <a id="evDeleteButton" onclick="deleteEvent(${event.id}, ${festival.id})" class="btn btn-primary">Удалить</a>
                                                        </form>

                                                    </td>
                                                    <script type="text/javascript">
                                                        jQuery(document).ready( function() {
                                                            jQuery("#evEditButton${event.id}").click(function(){
                                                                putEventValues("${event.id}", "${event.eventPoint.id}", "${festival.id}", "${event.name}", "${event.description}");
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
                <h4 class="modal-title">Место Событий</h4>
            </div>
            <div class="modal-body">
                <div class="raw">
                    <input id="ep-festival_id" name="festival_id" readonly type="hidden">
                    <input id="ep-id" name="eventPointId" readonly type="hidden">
                </div>

                <div class="raw">
                    <label for="ep-name">Название</label>
                    <input id="ep-name" placeholder="введите имя" name="name" class="form-control" required>
                </div>
                <div class="raw">
                    <label for="ep-description">Описание</label>
                    <textarea id="ep-description" placeholder="введите краткое описание" name="description"
                              class="form-control" rows="3"></textarea>
                </div>
                <div class="raw">
                    <label for="ep-geometry">Координаты</label>
                    <textarea id="ep-geometry" placeholder="введите координаты" name="geometry" class="form-control" rows="3"></textarea>
                </div>
                <div class="raw">
                    <label for="ep-color">Цвет</label>
                    <input id="ep-color" type="color" name="color" class="form-control">
                </div>
            </div>
            <div class="modal-footer">
                <form  class="form-inline">
                    <button id="ep-save-btn" type="button" class="btn btn-primary">Сохранить</button>
                    <button id="ep-close-btn" type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                </form>
            </div>
            <input id="ep-q-type" type="hidden" readonly>
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
                <h4 class="modal-title">Хот-Поинт</h4>
            </div>

            <div class="modal-body">
                <div class="raw">
                    <input type="hidden" id="hfestival_id" name="festival_id" readonly>
                    <input type="hidden" id="hp-id" name="eventPointId" readonly>
                </div>
                <div class="raw">
                    <label for="hp-name">Название</label>
                    <input id="hp-name" placeholder="введите имя" name="name" class="form-control" required>
                </div>
                <div class="raw">
                    <label for="hp-description">Описание</label>
                    <textarea id="hp-description" placeholder="введите краткое описание" name="description"
                              class="form-control" rows="3"></textarea>
                </div>
                <div class="raw">
                    <label for="hp-geometry">Координаты</label>
                    <textarea id="hp-geometry" placeholder="введите координаты" name="geometry" class="form-control" rows="3"></textarea>
                </div>
                <div class="raw">
                    <label for="hp-color">Цвет</label>
                    <input id="hp-color" type="color" name="color" class="form-control">
                </div>
            </div>
            <div class="modal-footer">
                <form  class="form-inline">
                    <button id="hp-save-btn" type="button" class="btn btn-primary">Сохранить</button>
                    <button id="hp-close-btn" type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                </form>
            </div>
            <input id="hp-q-type" type="hidden" readonly>
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
                <h4 class="modal-title">События</h4>
            </div>
            <div class="modal-body">
                <div class="raw">
                    <input type="hidden" id="efestival_id" name="festival_id" readonly>
                    <input type="hidden" id="ev-id" name="eventPointId" readonly>
                </div>

                <div class="raw">
                    <label for="ev_eventPoint">id Места Событий:</label>
                    <select id="ev_eventPoint" name="eventpoint_id">
                        <c:forEach var="item" items="${eventPointsList}">
                            <option value="${item.id}">${item.name} (id:${item.id})</option>

                        </c:forEach>
                    </select>
                </div>

                <div class="raw">
                    <label for="ev-name">Название</label>
                    <input id="ev-name" placeholder="введите имя" name="name" class="form-control" required>
                </div>

                <div class="raw">
                    <label for="ev-description">Описание</label>
                    <textarea id="ev-description" placeholder="введите краткое описание" name="description"
                          class="form-control" rows="3"></textarea>
                </div>
            <!--
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
            -->
                <input id="ev-q-type" type="hidden" readonly>
            </div>

            <div class="modal-footer">
                <form  class="form-inline">
                    <button id="ev-save-btn" type="button" class="btn btn-primary">Сохранить</button>
                    <button id="ev-close-btn" type="button" class="btn btn-default" data-dismiss="modal">Закрыть</button>
                </form>
            </div>
        </div>

    </div>
</div>

<script type="text/javascript">

    $( document ).ready(function() {
        var url = new URL(window.location.href);
        var activePanel = url.searchParams.get("active-panel");

        /*if (activePanel === null) {
            window.location.href = url.toString() + '&active-panel=edit-fest';
        }*/

        switch (activePanel) {
            case 'edit-fest':
                resetAllActivePanels();
                $('#festival-edit-nav').addClass('active');
                $('#festival-panel').addClass('active');
                break;
            case 'edit-hp':
                resetAllActivePanels();
                $('#hotpoint-list-nav').addClass('active');
                $('#hotpoints_panel').addClass('active');
                break;
            case 'edit-ep':
                resetAllActivePanels();
                $('#eventpoint-list-nav').addClass('active');
                $('#eventpoints-panel').addClass('active');
                break;
            case 'edit-ev':
                resetAllActivePanels();
                $('#event-list-nav').addClass('active');
                $('#events-panel').addClass('active');
                break;
            default:
                resetAllActivePanels();
                $('#festival-edit-nav').addClass('active');
                $('#festival-panel').addClass('active');
        }



        $("#festival-edit-nav").click(function(){
            var path = '/' + window.location.href.split('/')[3] + '/' + window.location.href.split('/')[4].split('?')[0];
            var param = window.location.href.split('/')[4].split('?')[1].split('&')[0];
            window.history.pushState("object or string", "Title", path + '?' + param + '&' + 'active-panel=edit-fest');
        });

        $("#hotpoint-list-nav").click(function(){
            var path = '/' + window.location.href.split('/')[3] + '/' + window.location.href.split('/')[4].split('?')[0];
            var param = window.location.href.split('/')[4].split('?')[1].split('&')[0];
            window.history.pushState("object or string", "Title", path + '?' + param + '&' + 'active-panel=edit-hp');
        });

        $("#eventpoint-list-nav").click(function(){
            var path = '/' + window.location.href.split('/')[3] + '/' + window.location.href.split('/')[4].split('?')[0];
            var param = window.location.href.split('/')[4].split('?')[1].split('&')[0];
            window.history.pushState("object or string", "Title", path + '?' + param + '&' + 'active-panel=edit-ep');
        });

        $("#event-list-nav").click(function(){
            var path = '/' + window.location.href.split('/')[3] + '/' + window.location.href.split('/')[4].split('?')[0];
            var param = window.location.href.split('/')[4].split('?')[1].split('&')[0];
            window.history.pushState("object or string", "Title", path + '?' + param + '&' + 'active-panel=edit-ev');
        });

    });

    function resetAllActivePanels() {
        $('#festival-edit-nav').removeClass('active');
        $('#festival-panel').removeClass('active');
        $('#hotpoint-list-nav').removeClass('active');
        $('#hotpoints_panel').removeClass('active');
        $('#eventpoint-list-nav').removeClass('active');
        $('#eventpoints-panel').removeClass('active');
        $('#event-list-nav').removeClass('active');
        $('#events-panel').removeClass('active');
    }

    function putHotpointValues(id, festival_id, name, description, geometry, color ) {
        $('#hp-id').val(id);
        $('#hfestival_id').val(festival_id);
        $('#hp-name').val(name);
        $('#hp-description').val(description);
        $('#hp-geometry').val(geometry);
        $('#hp-color').val(color);
    }
    function addHotpoint(festivalId) {
        $('#hp-q-type').val("new");
        $('#hfestival_id').val(festivalId);
        $('#hp-name').val("");
        $('#hp-description').val("");
        $('#hp-geometry').val("");
        $('#hp-color').val("");
        $("#editHotpointModal").modal('show');
    }
    function editHotpoint(festivalId) {
        $('#hp-q-type').val("");
        $('#hfestival_id').val(festivalId);
        $("#editHotpointModal").modal('show');
    }
    function deleteHotPoint(id, festivalId) {
        console.log("Delete hotpoint button "+festivalId);
        var data = { festivalId: festivalId, hotPointId: id };
        $.get( "/admin/hotpoints/delete", data );
        console.log($("#hprow"+id));
        $("#hprow"+id).remove();
    }


    function putEventpointValues(id, festival_id, name, description, geometry, color ) {
        $('#ep-id').val(id);
        $('#ep-festival_id').val(festival_id);
        $('#ep-name').val(name);
        $('#ep-description').val(description);
        $('#ep-geometry').val(geometry);
        $('#ep-color').val(color);
    }
    function addEventpoint(festivalId) {
        $('#ep-q-type').val("new");
        $('#ep-festival_id').val(festivalId);
        $('#ep-name').val("");
        $('#ep-description').val("");
        $('#ep-geometry').val("");
        $('#ep-color').val("");
        $("#editEventpointModal").modal('show');
    }
    function editEventPoint(festivalId) {
        $('#ep-q-type').val("");
        $('#ep-festival_id').val(festivalId);
        $("#editEventpointModal").modal('show');
    }
    function deleteEventPoint(id, festivalId) {
        console.log("Delete event point button "+festivalId);
        var data = { festivalId: festivalId, eventPointId: id };
        $.get( "/admin/eventpoints/delete", data );
        $("#eprow"+id).remove();

    }


    function putEventValues(id, eventpoint_id, festival_id, name, description) {
        $('#efestival_id').val(festival_id);
        $('#ev_eventPoint').val(eventpoint_id);
        $('#ev-id').val(id);
        $('#ev-name').val(name);
        $('#ev-description').val(description);
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


    $(document).ready(function($){

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

        $("#hp-save-btn").click(function(){
            console.log("Hotpoint save button = "+$('#hp-q-type').val());
            $.ajax({
                url : ($('#hp-q-type').val()=="new") ? '/admin/hotpoints/addhot' : '/admin/hotpoints/edit' ,
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
                    (location).reload();
                    $('#hotpoint-list-nav').show();
                    $('#hp-close-btn').click();


                },
                error : function (error) {
                    console.log(error);
                }
            });
        })

        $("#ep-save-btn").click(function(){
            $.ajax({
                url : ($('#ep-q-type').val()=="new") ? '/admin/eventpoints/create' : '/admin/eventpoints/edit',
                type : "POST",
                data : {                 // передаваемые сервлету данные
                    festivalId : $('#ep-festival_id').val(),
                    eventPointId : $('#ep-id').val(),
                    name : $('#ep-name').val(),
                    description : $('#ep-description').val(),
                    geometry : $('#ep-geometry').val(),
                    color : $('#ep-color').val()
                },
                success : function() {
                    // обработка ответа от сервера
                    (location).reload();
                    //$('#eventpoint-list-nav').show();
                    $('#ep-close-btn').click();
                },
                error : function (error) {
                    console.log(error.message);
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
                    eventPointId : $('#ev_eventPoint').val()
                },
                success : function() {
                    // обработка ответа от сервера
                    (location).reload();
                    //$('event-list-nav').click();
                    $('#ev-close-btn').click();
                },
                error : function (error) {
                    console.log(error);
                }

            });
        })

    })

</script>

</body>
</html>