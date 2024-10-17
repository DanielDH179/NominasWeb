<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="home" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="${home}/css/styles.css">
    <script src="https://kit.fontawesome.com/4358b9453c.js" crossorigin="anonymous"></script>
    <title>NominasWeb | Buscar</title>
  </head>
  <body>
    <header>
      <h1>Búsqueda por filtros</h1>
      <nav>
        <a id="back" href="javascript:window.location.replace(document.referrer);">
          Volver <i class="fa-solid fa-right-from-bracket"></i>
        </a>
      </nav>
    </header>
    <main>
      <form action="buscar" method="post">
        <input type="hidden" name="option" value="search">
        <p>
          <i class="fa-regular fa-id-card"></i>
          <input type="text" name="dni" placeholder pattern="[0-9]{8}[A-Z]">
          <label for="dni">DNI</label>
        </p>
        <p>
          <i class="fa-solid fa-keyboard"></i>
          <input type="text" name="name" placeholder>
          <label for="name">Nombre</label>
        </p>
        <p>
          <input id="male" type="radio" name="sex" value="M">
          <label for="male">Hombre</label>
          <input id="female" type="radio" name="sex" value="F">
          <label for="female">Mujer</label>
        </p>
        <p>
          <i class="fa-solid fa-briefcase"></i>
          <input type="number" min="1" max="10" name="minCategory" placeholder>
          <label for="minCategory">Categoría profesional (min)</label>
        </p>
        <p>
          <i class="fa-solid fa-briefcase"></i>
          <input type="number" min="1" max="10" name="maxCategory" placeholder>
          <label for="maxCategory">Categoría profesional (max)</label>
        </p>
        <p>
          <i class="fa-solid fa-business-time"></i>
          <input type="number" min="0" name="minYears" placeholder>
          <label for="minYears">Años trabajados (min)</label>
        </p>
        <p>
          <i class="fa-solid fa-business-time"></i>
          <input type="number" min="0" name="maxYears" placeholder>
          <label for="maxYears">Años trabajados (max)</label>
        </p>
        <div class="buttons">
          <input type="submit" value="Buscar">
          <input type="reset" value="Borrar">
        </div>
      </form>
    </main>
  </body>
</html>
