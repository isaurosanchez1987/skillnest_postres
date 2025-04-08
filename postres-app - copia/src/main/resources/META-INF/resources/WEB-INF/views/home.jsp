<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Postres - Home</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">
<div class="d-flex justify-content-between align-items-center mb-4">
    <h2>Postres</h2>
    <div>
        <span class="me-3">Hola, ${usuario.nombre} 👋</span>
        <a href="/logout" class="btn btn-outline-danger">Cerrar sesión</a>
    </div>
</div>

<!-- Menú -->
<nav class="mb-4">
    <a href="/home" class="btn btn-outline-primary">Todos los postres</a>
    <a href="/postres/nuevo" class="btn btn-outline-success">Agregar postre</a>
    <a href="/postres/mis-postres" class="btn btn-outline-secondary">Mis postres</a>
</nav>

<!-- Barra de búsqueda -->
<form action="/home" method="get" class="input-group mb-4">
    <input type="text" name="filtro" class="form-control" placeholder="Buscar por título..." value="${filtro}">
    <button class="btn btn-primary" type="submit">Filtrar</button>
    <a href="/home" class="btn btn-secondary">Limpiar</a>
</form>

<!-- Lista de postres -->
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

                <c:if test="${postre.usuario.id == usuario.id}">
                    <a href="/postres/editar/${postre.id}" class="btn btn-warning btn-sm">Editar</a>
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>
