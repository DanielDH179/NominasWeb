package me.ddmiher880.utils;

import java.sql.SQLException;

public class DBHandler {
  
  /**
   * Controla los mensajes de excepción a la base de datos.
   *
   * @param e excepción general de MySQL
   * @return mensaje del estado de MySQL
   */
  public static String handleSQL(SQLException e) {

    // https://dev.mysql.com/doc/mysql-errors/5.7/en/server-error-reference.html

    switch (e.getSQLState()) {
      case "22001":
        return "ERROR: Valor demasiado largo";
      case "22005":
        return "ERROR: Tipo de dato incorrecto";
      case "22018":
        return "ERROR: Cadena de caracteres inválida";
      case "23000":
        return "ERROR: Valor incorrecto";
      case "23502":
        return "ERROR: El valor no puede ser nulo";
      case "42000":
        return "ERROR: Sintaxis de la consulta incorrecta";
      case "42S02":
        return "ERROR: Datos no encontrados";
      case "42S22":
        return "ERROR: Campo no encontrado";
      case "45000": // Disparador de MySQL
        return "ERROR: " + e.getMessage();
      case "HYT00":
        return "ERROR: Tiempo de espera expirado";
      default:
        return "ERROR: " + e.getErrorCode();
    }
    
  }

  /**
   * Obtiene el mensaje asociado al estado de la excepción.
   *
   * @param code el código MySQL de la excepción
   * @return mensaje correspondiente al código MySQL
   */
  public static String getErrorMessage(String code) {
    return handleSQL(new SQLException("", code, null));
  }

}
