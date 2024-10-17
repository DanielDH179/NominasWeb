<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="home" value="${pageContext.request.contextPath}" />
<c:set var="dateFormat" value="dd/MM/yyyy HH:mm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="${home}/css/styles.css">
    <script src="https://kit.fontawesome.com/4358b9453c.js" crossorigin="anonymous"></script>
    <script defer src="https://unpkg.com/swup@latest/dist/Swup.umd.js"></script>
    <script defer src="${home}/js/logger.js"></script>
    <script defer src="${home}/js/swup.js"></script>
    <title>NominasWeb | Nóminas</title>
    <style>.preload * { transition: none !important; }</style>
	</head>
  <body class="preload">
    <header>
      <c:choose>
        <c:when test="${e != null}">
          <h1>Empleado encontrado</h1>
        </c:when>
        <c:otherwise>
          <h1>${list.size()} empleado<c:if test="${list.size() != 1}">s</c:if></h1>
        </c:otherwise>
      </c:choose>
      <nav>
        <a href="empresa?option=create">
          Darse de alta <i class="fa-solid fa-user-plus"></i>
        </a>
        <a href="buscar">
          Buscar <i class="fa-solid fa-magnifying-glass"></i>
        </a>
        <p>Ordenar por:</p>
        <a class="tooltip" href="empresa?option=list">
          <p>DNI (Ascendente)</p>
          <i class="fa-regular fa-id-card"></i>
        </a>
        <a class="tooltip" href="empresa?option=list&sortBy=name">
          <p>Nombre (A-Z)</p>
          <i class="fa-solid fa-arrow-down-a-z"></i>
        </a>
        <a class="tooltip" href="empresa?option=list&sortBy=category">
          <p>Categoría profesional</p>
          <i class="fa-solid fa-briefcase"></i>
        </a>
        <a class="tooltip" href="empresa?option=list&sortBy=years">
          <p>Años trabajados</p>
          <i class="fa-solid fa-business-time"></i>
        </a>
        <a class="tooltip" href="empresa?option=list&sortBy=signed_up_date&desc=true">
          <p>Añadido recientemente</p>
          <i class="fa-regular fa-calendar-plus"></i>
        </a>
        <a class="tooltip" href="empresa?option=list&sortBy=modified_date&desc=true">
          <p>Editado recientemente</p>
          <i class="fa-regular fa-clock"></i>
        </a>
        <a id="back" href="${home}">
          Inicio <i class="fa-solid fa-house"></i>
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
          <c:when test="${msg != null}">
            <i class="fa-solid ${msg.isError() ? "fa-bug" : "fa-circle-info"} fa-fade"></i>
            ${msg.toString()}
          </c:when>
          <c:when test="${more != null}">
            <i class="fa-solid fa-bug fa-fade"></i> ${more}
          </c:when>
          <c:otherwise>
            <i class="fa-solid fa-circle-info fa-fade"></i>
            Aquí se mostrarán los mensajes de las interacciones en el sitio web
            <br />
            <br />
            <i class="fa-solid fa-circle-info fa-fade"></i>
            Puedes ordenar los datos de la tabla inferior con el menú superior
          </c:otherwise>
        </c:choose>
      </pre>
      <c:if test="${!list.isEmpty()}">
        <table id="swup" class="transition-main">
          <tr>
            <th>DNI</th>
            <th>Nombre</th>
            <th>Sexo</th>
            <th>Categoría</th>
            <th>Años</th>
            <c:choose>
              <c:when test="${search}">
                <th>Salario</th>
              </c:when>
              <c:otherwise>
                <th>Registrado</th>
                <th>Modificado</th>
              </c:otherwise>
            </c:choose>
            <th>Editar</th>
            <th>Borrar</th>
          </tr>
          <c:choose>
            <c:when test="${e != null}">
              <c:set var="list" value="${[e]}" />
            </c:when>
            <c:otherwise>
              <c:set var="list" value="${list}" />
            </c:otherwise>
          </c:choose>
          <c:forEach var="e" items="${list}">
            <tr>
              <td>${e.dni}</td>
              <td>${e.name}</td>
              <td>
                <c:choose>
                  <c:when test="${e.sex == 77}">
                    <i class="fa-solid fa-mars"></i>
                  </c:when>
                  <c:otherwise>
                    <i class="fa-solid fa-venus"></i>
                  </c:otherwise>
                </c:choose>
              </td>
              <td>${e.category}</td>
              <td>${e.years}</td>
              <c:choose>
                <c:when test="${search}">
                  <td>${e.salary}</td>
                </c:when>
                <c:otherwise>
                  <td>
                    <fmt:formatDate value="${e.signedUpDate}" pattern="${dateFormat}" />
                  </td>
                  <td>
                    <c:choose>
                      <c:when test="${e.modifiedDate == null}">N/A</c:when>
                      <c:otherwise>
                        <fmt:formatDate value="${e.modifiedDate}" pattern="${dateFormat}" />
                      </c:otherwise>
                    </c:choose>
                  </td>
                </c:otherwise>
              </c:choose>
              <td>
                <a class="edit" href="empresa?option=edit&dni=${e.dni}">
                  <i class="fa-regular fa-pen-to-square"></i>
                </a>
              </td>
              <td>
                <a class="delete" href="empresa?option=delete&dni=${e.dni}" data-no-swup onclick="return confirm('¿Estás seguro? Esta acción no puede deshacerse')">
                  <i class="fa-regular fa-trash-can"></i>
                </a>
              </td>
            </tr>
          </c:forEach>
        </table>
      </c:if>
    </main>
    <script>
      window.onload = setTimeout(() => document.body.classList.remove("preload"), 100);
    </script>
	</body>
</html>
