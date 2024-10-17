<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="home" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${home}/css/styles.css">
    <script src="https://kit.fontawesome.com/4358b9453c.js" crossorigin="anonymous"></script>
    <script defer src="${home}/js/logger.js"></script>
    <title>NominasWeb | Menu</title>
    <style>.preload * { transition: none !important; }</style>
  </head>
  <body class="preload">
    <header>
      <h1>Portal de empleados</h1>
      <nav>
        <a href="https://www.youtube.com/@DanielDH179" target="_blank">
          YouTube <i class="fa-brands fa-youtube"></i>
        </a>
        <a href="https://github.com/DanielDH179/NominasWeb" target="_blank">
          GitHub <i class="fa-brands fa-github"></i>
        </a>
        <a id="back" href="empresa?option=list">
          Consultar datos <i class="fa-solid fa-database"></i>
        </a>
      </nav>
    </header>
    <main>
      <div id="top">
        <div class="dot red"><i class="fa-solid fa-xmark"></i></div>
        <div class="dot amber"><i class="fa-solid fa-minus"></i></div>
        <div class="dot green"><i class="fa-solid fa-plus"></i></div>
      </div>
      <pre id="logger">
        <c:choose>
          <c:when test="${session != null && msg != null}">${msg}</c:when>
          <c:when test="${msg != null}">
            <i class="fa-solid ${msg.isError() ? "fa-bug" : "fa-circle-info"} fa-fade"></i>
            ${msg.toString()}
          </c:when>
          <c:when test="${more != null}">
            <i class="fa-solid fa-bug fa-fade"></i> ${more}
          </c:when>
          <c:otherwise>
            <i class="fa-solid fa-circle-info fa-fade"></i>
            Esta ventana se puede minimizar, cerrar y redimensionar (botones y esquina inferior derecha)
            <br />
            <br />
            <i class="fa-solid fa-circle-info fa-fade"></i>
            La navegación está desactivada en el sitio web. Inicia sesión aquí para acceder a los datos
            <br />
            <br />
            <i class="fa-solid fa-circle-info fa-fade"></i>
            Práctica de 'Desarrollo Web en Entorno Servidor' por Daniel Dmitrienco Herrera :)
          </c:otherwise>
        </c:choose>
      </pre>
      <c:choose>
        <c:when test="${session == null}">
          <form action="login" method="post">
            <p>
              <i class="fa-solid fa-user"></i>
              <input type="text" id="user" name="user" placeholder required>
              <label for="user">Usuario</label>
            </p>
            <p>
              <i class="fa-solid fa-lock"></i>
              <input type="password" id="pass" name="pass" placeholder required>
              <label for="pass">Contraseña</label>
            </p>
            <div class="buttons">
              <input type="submit" value="Iniciar Sesión">
              <input type="reset" value="Borrar">
            </div>
          </form>
        </c:when>
        <c:otherwise>
          <form action="logout" method="get">
            <div class="buttons">
              <input type="submit" value="Cerrar Sesión">
            </div>
          </form>
        </c:otherwise>
      </c:choose>
    </main>
    <script>
      window.onload = setTimeout(() => document.body.classList.remove("preload"), 100);
    </script>
  </body>
</html>
