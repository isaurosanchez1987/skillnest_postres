<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet"/>
</head>
<body class="container mt-5">
<h2>Postres - Login</h2>

<c:if test="${not empty error}">
    <div class="alert alert-danger">${error}</div>
</c:if>

<form action="/login" method="post" class="row g-3">
    <div class="col-md-6">
        <label class="form-label">Correo</label>
        <input type="email" name="correo" class="form-control" required>
    </div>

    <div class="col-md-6">
        <label class="form-label">Contraseña</label>
        <input type="password" name="password" class="form-control" required>
    </div>

    <div class="col-12">
        <button type="submit" class="btn btn-primary">Iniciar sesión</button>
        <a href="/registro" class="btn btn-link">¿No tienes cuenta? Regístrate</a>
    </div>
</form>
</body>
</html>
