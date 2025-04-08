<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Mis postres</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">

<div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Mis postres</h2>
    <a href="/home" class="btn btn-secondary">Volver a Inicio</a>
</div>

<c:if test="${empty postres}">
    <div class="alert alert-info">Aún no has creado ningún postre.</div>
</c:if>

<c:if test="${not empty postres}">
    <table class="table table-bordered table-hover">
        <thead class="table-light">
        <tr>
            <th>Título</th>
            <th>Ingredientes</th>
            <th>Acciones</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="postre" items="${postres}">
            <tr>
                <td>${postre.titulo}</td>
                <td>${postre.ingredientes}</td>
                <td>
                    <a href="/postres/detalle/${postre.id}" class="btn btn-info btn-sm">Ver</a>
                    <a href="/postres/editar/${postre.id}" class="btn btn-warning btn-sm">Editar</a>
                    <a href="/postres/eliminar/${postre.id}" class="btn btn-danger btn-sm"
                       onclick="return confirm('¿Estás seguro de eliminar este postre?');">Eliminar</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>
