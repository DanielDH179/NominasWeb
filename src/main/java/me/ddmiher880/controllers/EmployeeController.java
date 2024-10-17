package me.ddmiher880.controllers;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import me.ddmiher880.dao.EmployeeDAO;
import me.ddmiher880.enums.ServerMessage;
import me.ddmiher880.exceptions.EmployeeException;
import me.ddmiher880.models.Employee;
import me.ddmiher880.utils.DNI;
import me.ddmiher880.utils.FormattedMessage;
import me.ddmiher880.utils.SortCriterion;

@WebServlet("/empresa")
public class EmployeeController extends HttpServlet {

	private static final long serialVersionUID = 182736451827364512L;
	
  /** Constructor por defecto. */
	public EmployeeController() {
		super();
	}

  /**
   * Redirige la petición a la página de listado de empleados.
   * 
   * @param request solicitud HTTP
   * @param response respuesta HTTP
   * @param sc criterio de ordenación
   * @throws ServletException en caso de error de servlet
   * @throws IOException en caso de error de entrada/salida
   */
  private void fetchList(HttpServletRequest request, HttpServletResponse response, SortCriterion sc) throws ServletException, IOException {

    try {
      request.setAttribute("list", new EmployeeDAO().getEmployees(sc));
    } catch (EmployeeException e) {
      // System.out.println(e.getMessage());
      request.setAttribute("more", e.getMessage());
    } finally {
      request.getRequestDispatcher("/views/list.jsp").forward(request, response);
    }

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
    
    request.setCharacterEncoding("UTF-8");
		
		String option = request.getParameter("option");
		String sortBy = request.getParameter("sortBy");
		String desc = request.getParameter("desc");
    String dni = request.getParameter("dni");
    SortCriterion sc = new SortCriterion(sortBy, desc);
		EmployeeDAO dao = new EmployeeDAO();
    boolean success;

    if (option == null) {
      request.getRequestDispatcher("/index.jsp").forward(request, response);
      return;
    }

    switch (option) {
      case "create":
        request.getRequestDispatcher("/views/create.jsp").forward(request, response);
        break;
      case "list":
        fetchList(request, response, sc);
        break;
      case "edit":
        try {
          request.setAttribute("e", dao.getEmployeeByDni(dni));
          request.getRequestDispatcher("/views/edit.jsp").forward(request, response);
        } catch (EmployeeException e) {
          // System.out.println(e.getMessage());
        }
        break;
      case "delete":
        try {
          success = dao.delete(dni);
          request.setAttribute("msg", success ? new FormattedMessage(ServerMessage.DELETED, dni) : ServerMessage.ERROR_DELETE);
        } catch (EmployeeException e) {
          // System.out.println(e.getMessage());
          request.setAttribute("more", e.getMessage());
        } finally {
          fetchList(request, response, sc);
        }
        break;
      default:
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
		
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

    String option = request.getParameter("option");
    String dni = request.getParameter("dni");
    String name = request.getParameter("name");
    String sex = request.getParameter("sex");
    Integer category = Integer.valueOf(request.getParameter("category"));
    Integer years = Integer.valueOf(request.getParameter("years"));
    SortCriterion sc = new SortCriterion();
		EmployeeDAO dao = new EmployeeDAO();
		Date currentDate = new Date();
		Employee emp = new Employee();
    boolean success;

    if (option == null) {
      request.getRequestDispatcher("/index.jsp").forward(request, response);
      return;
    }

    switch (option) {
      case "create":
        if (!DNI.check(dni)) {
          request.setAttribute("msg", new FormattedMessage(ServerMessage.CONTROL_DIGIT, dni.charAt(8), DNI.getControlDigit(dni)));
          request.getRequestDispatcher("/views/list.jsp").forward(request, response);
        } else try {
          emp.setDni(dni);
          emp.setName(name);
          if (sex != null) emp.setSex(sex.charAt(0));
          emp.setCategory(category);
          emp.setYears(years);
          emp.setSignedUpDate(new Timestamp(currentDate.getTime()));
          success = dao.signUp(emp);
          request.setAttribute("msg", success ? new FormattedMessage(ServerMessage.CREATED, dni) : ServerMessage.ERROR_CREATE);
        } catch (EmployeeException e) {
          // System.out.println(e.getMessage());
          request.setAttribute("more", e.getMessage());
        } finally {
          fetchList(request, response, sc);
        }
        break;
      case "edit":
        try {
          emp.setDni(dni);
          emp.setName(name);
          if (sex != null) emp.setSex(sex.charAt(0));
          emp.setCategory(category);
          emp.setYears(years);
          emp.setModifiedDate(new Timestamp(currentDate.getTime()));
          success = dao.update(emp);
          request.setAttribute("msg", success ? new FormattedMessage(ServerMessage.UPDATED, dni) : ServerMessage.ERROR_UPDATE);
        } catch (EmployeeException e) {
          // System.out.println(e.getMessage());
          request.setAttribute("more", e.getMessage());
        } finally {
          fetchList(request, response, sc);
        }
        break;
      default:
        request.getRequestDispatcher("/index.jsp").forward(request, response);
    }
		
	}

}
