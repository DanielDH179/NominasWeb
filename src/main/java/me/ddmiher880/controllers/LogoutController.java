package me.ddmiher880.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import me.ddmiher880.enums.ServerMessage;

@WebServlet("/logout")
public class LogoutController extends HttpServlet {

  private static final long serialVersionUID = 568340406283983032L;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    HttpSession hs = request.getSession(false);

    if (hs != null) {
      hs.invalidate();
      request.setAttribute("msg", ServerMessage.LOGGED_OUT);
    }
    
    response.sendRedirect(request.getContextPath() + "/index.jsp");

  }

}
