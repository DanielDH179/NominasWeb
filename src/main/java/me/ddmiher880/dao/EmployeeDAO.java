package me.ddmiher880.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import me.ddmiher880.config.SQLConnection;
import me.ddmiher880.exceptions.EmployeeException;
import me.ddmiher880.models.Employee;
import me.ddmiher880.utils.DBHandler;
import me.ddmiher880.utils.Payroll;
import me.ddmiher880.utils.SortCriterion;

public class EmployeeDAO {
	
	private PreparedStatement ps;
	private String query;

  /**
   * Obtiene una conexión a la base de datos.
   * 
   * @return conexión de base de datos
   * @throws SQLException si ocurre un error de conexión
   */
	private Connection getConnection() throws SQLException {
		return SQLConnection.getConnection();
	}
	
  /**
   * Registra un nuevo empleado en la base de datos.
   * 
   * @param emp empleado a registrar
   * @return true si se registra correctamente, false en caso contrario
   * @throws EmployeeException si ocurre un error en la consulta
   */
	public boolean register(Employee emp) throws EmployeeException {
		
		try (Connection conn = getConnection()) {
			query = "INSERT INTO employee (name, dni, sex, category, years, signed_up_date, modified_date) VALUES (?, ?, ?, ?, ?, ?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, emp.getName());
			ps.setString(2, emp.getDni());
			ps.setString(3, String.valueOf(emp.getSex()));
			ps.setInt(4, emp.getCategory());
			ps.setInt(5, emp.getYears());
			ps.setTimestamp(6, emp.getSignedUpDate());
			ps.setTimestamp(7, emp.getModifiedDate());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
      throw new EmployeeException(DBHandler.handleSQL(e));
		} catch (NullPointerException e) {
			// System.out.println(e.getMessage());
      throw new EmployeeException(DBHandler.getErrorMessage("23502"));
    }
		
	}

  /**
   * Da de alta a un empleado y calcula su nómina
   * 
   * @param emp empleado a dar de alta
   * @return true si se da de alta correctamente, false en caso contrario 
   * @throws EmployeeException si ocurre un error en la consulta
   */
  public boolean signUp(Employee emp) throws EmployeeException {
    
    boolean success = register(emp);
    if (!success) return false;

    try (Connection conn = getConnection()) {
			query = "INSERT INTO payroll (dni, salary) VALUES (?, ?)";
			ps = conn.prepareStatement(query);
			ps.setString(1, emp.getDni());
			ps.setInt(2, Payroll.getSalary(emp));
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
      throw new EmployeeException(DBHandler.handleSQL(e));
		}

  }
	
  /**
   * Actualiza un empleado en la base de datos.
   * 
   * @param emp empleado a actualizar
   * @return true si se actualiza correctamente, false en caso contrario
   * @throws EmployeeException si ocurre un error en la consulta
   */
	public boolean update(Employee emp) throws EmployeeException {
		
		try (Connection conn = getConnection()) {
			query = "UPDATE employee SET name=?, sex=?, category=?, years=?, modified_date=? WHERE dni=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, emp.getName());
			ps.setString(2, String.valueOf(emp.getSex()));
			ps.setInt(3, emp.getCategory());
      ps.setInt(4, emp.getYears());
			ps.setTimestamp(5, emp.getModifiedDate());
			ps.setString(6, emp.getDni());
      // boolean success = ps.executeUpdate() > 0;
      ps.executeUpdate();
      query = "UPDATE payroll SET salary=? WHERE dni=?";
      ps = conn.prepareStatement(query);
      ps.setInt(1, Payroll.getSalary(emp));
      ps.setString(2, emp.getDni());
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
      throw new EmployeeException(DBHandler.handleSQL(e));
		} catch (NullPointerException e) {
			// System.out.println(e.getMessage());
      throw new EmployeeException(DBHandler.getErrorMessage("23502"));
		}
		
	}
	
  /**
   * Elimina un empleado de la base de datos.
   * 
   * @param dni identificador del empleado
   * @return true si se elimina correctamente, false en caso contrario
   * @throws EmployeeException si ocurre un error en la consulta
   */
	public boolean delete(String dni) throws EmployeeException {

		try (Connection conn = getConnection()) {
			query = "DELETE FROM employee WHERE dni=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, dni);
			return ps.executeUpdate() > 0;
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
      throw new EmployeeException(DBHandler.handleSQL(e));
		}
		
	}
	
  /**
   * Obtiene una lista de empleados según los criterios de ordenación.
   * 
   * @param sc criterio de ordenación
   * @return lista de empleados
   * @throws EmployeeException si ocurre un error en la consulta
   */
	public List<Employee> getEmployees(SortCriterion sc) throws EmployeeException {
		
		List<Employee> employees = new ArrayList<>();
		
		try (Connection conn = getConnection()) {
			query = "SELECT * FROM employee" + sc;
			ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
        String name = rs.getString(1);
        String dni = rs.getString(2);
        char sex = rs.getString(3).charAt(0);
        int category = rs.getInt(4);
        int years = rs.getInt(5);
        Timestamp signedUpDate = rs.getTimestamp(6);
        Timestamp modifiedDate = rs.getTimestamp(7);
				employees.add(new Employee(dni, name, sex, category, years, signedUpDate, modifiedDate));
			}
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
      throw new EmployeeException(DBHandler.handleSQL(e));
		}
		
		return employees;
		
	}

  /**
   * Obtiene una lista de empleados según los parámetros de búsqueda.
   * 
   * @param args colección indefinida de datos
   * @return lista de empleados
   * @throws EmployeeException si ocurre un error en la consulta
   */
	public List<Employee> getEmployees(String... args) throws EmployeeException {
    
    StringBuilder sb = new StringBuilder();
    sb.append("SELECT e.dni, e.name, e.sex, e.category, e.years, p.salary");
    sb.append(" FROM employee e NATURAL JOIN payroll p WHERE 1=1");

    List<Employee> employees = new ArrayList<>();
    List<Object> params = new ArrayList<>();

    for (String arg : args) {
      
      String[] line = arg.split("=");
      if (line.length < 2) continue;

      String key = line[0], value = line[1];
      if ("null".equals(value)) continue;

      switch (key) {
        case "dni":
          sb.append(" AND dni=?");
          params.add(value);
          break;
        case "name":
          sb.append(" AND name=?");
          params.add(value);
          break;
        case "sex":
          sb.append(" AND sex=?");
          params.add(value);
          break;
        case "minCategory":
          sb.append(" AND category>=?");
          params.add(Integer.valueOf(value));
          break;
        case "maxCategory":
          sb.append(" AND category<=?");
          params.add(Integer.valueOf(value));
          break;
        case "minYears":
          sb.append(" AND years>=?");
          params.add(Integer.valueOf(value));
          break;
        case "maxYears":
          sb.append(" AND years<=?");
          params.add(Integer.valueOf(value));
          break;
      }

    }

		try (Connection conn = getConnection()) {
      ps = conn.prepareStatement(sb.toString());
      for (int i = 0; i < params.size(); i++) {
        ps.setObject(i + 1, params.get(i));
      }
			ResultSet rs = ps.executeQuery();
      while (rs.next()) {
        String dni = rs.getString(1);
        String name = rs.getString(2);
        char sex = rs.getString(3).charAt(0);
        int category = rs.getInt(4);
        int years = rs.getInt(5);
        int salary = rs.getInt(6);
				employees.add(new Employee(dni, name, sex, category, years, salary));
			}
    } catch (SQLException e) {
			// System.out.println(e.getMessage());
      throw new EmployeeException(DBHandler.handleSQL(e));
    }

    return employees;

  }
	
  /**
   * Obtiene un empleado por su identificador.
   * 
   * @param dni identificador del empleado
   * @return empleado encontrado o null si no existe
   * @throws EmployeeException si ocurre un error en la consulta
   */
	public Employee getEmployeeByDni(String dni) throws EmployeeException {
		
		Employee emp = null;
		
		try (Connection conn = getConnection()) {
			query = "SELECT * FROM employee WHERE dni=?";
			ps = conn.prepareStatement(query);
			ps.setString(1, dni);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
        String name = rs.getString(1);
        char sex = rs.getString(3).charAt(0);
        int category = rs.getInt(4);
        int years = rs.getInt(5);
        Timestamp signedUpDate = rs.getTimestamp(6);
        Timestamp modifiedDate = rs.getTimestamp(7);
				emp = new Employee(dni, name, sex, category, years, signedUpDate, modifiedDate);
			}
		} catch (SQLException e) {
			// System.out.println(e.getMessage());
      throw new EmployeeException(DBHandler.handleSQL(e));
		}
		
		return emp;
		
	}

}
