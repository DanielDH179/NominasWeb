package me.ddmiher880.controllers;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import me.ddmiher880.dao.LoginDAO;
import me.ddmiher880.enums.ServerMessage;
import me.ddmiher880.utils.DBHandler;
import me.ddmiher880.utils.FormattedMessage;

@WebServlet("/login")
public class LoginController extends HttpServlet {

  private static final long serialVersionUID = 948273645198273645L;

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    String username = request.getParameter("user");
    String password = request.getParameter("pass");
    LoginDAO dao = new LoginDAO();

    try {
      if (dao.authenticate(username, password)) {
        request.getSession().setAttribute("session", username);
        request.setAttribute("msg", new FormattedMessage(ServerMessage.LOGGED_IN, dao.getAlias(username, password)));
      } else {
        request.setAttribute("msg", ServerMessage.ERROR_LOGIN);
      }
    } catch (SQLException e) {
      request.setAttribute("more", DBHandler.handleSQL(e));
    } finally {
      request.getRequestDispatcher("/index.jsp").forward(request, response);
    }

  }

}
