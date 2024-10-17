<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="home" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <link rel="stylesheet" type="text/css" href="${home}/css/styles.css">
    <script src="https://kit.fontawesome.com/4358b9453c.js" crossorigin="anonymous"></script>
    <script defer src="${home}/js/logger.js"></script>
    <title>500</title>
  </head>
  <body>
    <header>
 		  <h1>Error 500 :(</h1>
      <nav>
        <a id="back" href="${home}">
          Volver <i class="fa-solid fa-right-from-bracket"></i>
        </a>
      </nav>
    </header>
    <div id="top">
      <div class="dot red"><i class="fa-solid fa-xmark"></i></div>
      <div class="dot amber"><i class="fa-solid fa-minus"></i></div>
      <div class="dot green"><i class="fa-solid fa-plus"></i></div>
    </div>
    <pre id="logger">
      <i class="fa-solid fa-bug fa-fade"></i>
      ERROR INTERNO DEL SERVIDOR: Inténtalo de nuevo más tarde
    </pre>
  </body>
</html>
