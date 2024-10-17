<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="home" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${home}/css/styles.css">
    <script src="https://kit.fontawesome.com/4358b9453c.js" crossorigin="anonymous"></script>
    <title>NominasWeb | Edición</title>
  </head>
  <body>
    <header>
      <h1>Editar empleado ${e.dni}</h1>
      <nav>
        <a id="back" href="javascript:window.location.replace(document.referrer);">
          Volver <i class="fa-solid fa-right-from-bracket"></i>
        </a>
      </nav>
    </header>
    <main>
      <form action="empresa" method="post">
        <input type="hidden" name="option" value="edit">
        <input type="hidden" name="dni" value="${e.dni}">
        <p>
          <i class="fa-solid fa-keyboard"></i>
          <input type="text" required name="name" placeholder required value="${e.name}">
          <label for="name">Nombre</label>
        </p>
        <p>
          <input id="male" type="radio" name="sex" value="M" ${e.sex == 77 ? 'checked' : ''}>
          <label for="male">Hombre</label>
          <input id="female" type="radio" name="sex" value="F" ${e.sex == 70 ? 'checked' : ''}>
          <label for="female">Mujer</label>
        </p>
        <p>
          <i class="fa-solid fa-briefcase"></i>
          <input type="number" min="1" max="10" name="category" placeholder required value="${e.category}">
          <label for="category">Categoría profesional</label>
        </p>
        <p>
          <i class="fa-solid fa-business-time"></i>
          <input type="number" min="0" name="years" placeholder required value="${e.years}">
          <label for="years">Años trabajados</label>
        </p>
        <div class="buttons">
          <input type="submit" value="Guardar">
          <input type="reset" value="Restablecer">
          <a href="empresa?option=list">
            <input type="button" value="Cancelar">
          </a>
        </div>
      </form>
    </main>
  </body>
</html>
