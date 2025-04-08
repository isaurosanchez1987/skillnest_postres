<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Detalle del postre</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-4">
<h2>${postre.titulo}</h2>

<c:if test="${not empty postre.imagen}">
    <img src="${postre.imagen}" alt="imagen" class="img-fluid mb-3" style="max-height: 300px;">
</c:if>

<p><strong>Ingredientes:</strong> ${postre.ingredientes}</p>
<p><strong>Descripción:</strong> ${postre.descripcion}</p>

<div class="mt-3">
    <c:if test="${postre.usuario.id == usuario.id}">
        <a href="/postres/editar/${postre.id}" class="btn btn-warning">Editar</a>
        <a href="/postres/eliminar/${postre.id}" class="btn btn-danger"
           onclick="return confirm('¿Estás seguro que deseas eliminar este postre?');">Eliminar</a>
    </c:if>

    <form action="/postres/like/${postre.id}" method="post" style="display:inline;">
        <button type="submit" class="btn btn-outline-primary"
                <c:if test="${leGusta}">disabled</c:if>>
            <c:choose>
                <c:when test="${leGusta}">¡Te gusta!</c:when>
                <c:otherwise>Me gusta</c:otherwise>
            </c:choose>
        </button>
    </form>
</div>

<hr/>
<h5>Le gusta a:</h5>
<ul>
    <c:forEach var="u" items="${likes}">
        <li>${u.nombre} (${u.correo})</li>
    </c:forEach>
</ul>

<a href="/home" class="btn btn-secondary mt-3">Volver</a>
</body>
</html>
