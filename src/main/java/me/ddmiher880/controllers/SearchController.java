package me.ddmiher880.controllers;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import me.ddmiher880.dao.EmployeeDAO;
import me.ddmiher880.enums.ServerMessage;
import me.ddmiher880.exceptions.EmployeeException;

@WebServlet("/buscar")
public class SearchController extends HttpServlet {

	private static final long serialVersionUID = 827364512938475612L;
	
  /** Constructor por defecto. */
	public SearchController() {
		super();
	}

  /**
   * Maneja las solicitudes GET para empleados.
   * 
   * @param request solicitud HTTP
   * @param response respuesta HTTP
   * @throws ServletException en caso de error de servlet
   * @throws IOException en caso de error de entrada/salida
   */
  @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {    
    request.getRequestDispatcher("/views/search.jsp").forward(request, response);
	}

  /**
   * Maneja las solicitudes POST para empleados.
   * 
   * @param request solicitud HTTP
   * @param response respuesta HTTP
   * @throws ServletException en caso de error de servlet
   * @throws IOException en caso de error de entrada/salida
   */	
  @Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    request.setCharacterEncoding("UTF-8");

    try {
      request.setAttribute("list", new EmployeeDAO().getEmployees(
        "dni=" + request.getParameter("dni"),
        "name=" + request.getParameter("name"),
        "sex=" + request.getParameter("sex"),
        "minCategory=" + request.getParameter("minCategory"),
        "maxCategory=" + request.getParameter("maxCategory"),
        "minYears=" + request.getParameter("minYears"),
        "maxYears=" + request.getParameter("maxYears")
      ));
      request.setAttribute("msg", ServerMessage.RESULTS_FOUND);
      request.setAttribute("search", true);
    } catch (EmployeeException e) {
      // System.out.println(e.getMessage());
      request.setAttribute("more", e.getMessage());
    } finally {
      request.getRequestDispatcher("/views/list.jsp").forward(request, response);
    }
		
	}

}
