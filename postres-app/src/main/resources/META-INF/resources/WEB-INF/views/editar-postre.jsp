<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Editar postre</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-5">
<h2>Editar postre</h2>

<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<form action="/postres/editar/${postre.id}" method="post" class="row g-3">

    <div class="col-md-6">
        <label class="form-label">Título</label>
        <input type="text" name="titulo" class="form-control" value="${postre.titulo}" required>
    </div>

    <div class="col-md-6">
        <label class="form-label">Imagen (URL)</label>
        <input type="text" name="imagen" class="form-control" value="${postre.imagen}">
    </div>

    <div class="col-12">
        <label class="form-label">Ingredientes</label>
        <textarea name="ingredientes" class="form-control" rows="2" required>${postre.ingredientes}</textarea>
    </div>

    <div class="col-12">
        <label class="form-label">Descripción</label>
        <textarea name="descripcion" class="form-control" rows="3" required>${postre.descripcion}</textarea>
    </div>

    <div class="col-12">
        <button type="submit" class="btn btn-primary">Actualizar</button>
        <a href="/home" class="btn btn-secondary">Cancelar</a>
    </div>
</form>
</body>
</html>
