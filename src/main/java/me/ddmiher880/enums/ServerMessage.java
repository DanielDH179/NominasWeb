package me.ddmiher880.enums;

public enum ServerMessage {

  CREATED("Empleado %s registrado correctamente", false),
  UPDATED("Empleado %s actualizado correctamente", false),
  DELETED("Empleado %s eliminado correctamente", false),
  
  ERROR_CREATE("ERROR: No se ha podido registar", true),
  ERROR_UPDATE("ERROR: No se ha podido actualizar", true),
  ERROR_DELETE("ERROR: No se ha podido eliminar", true),

  CONTROL_DIGIT("ERROR: Letra del DNI incorrecta (recibido: %c, esperado: %c)", true),
  RESULTS_FOUND("Mostrando resultados de la búsqueda...", false);

  private final String msg;
  private final boolean error;

  /**
   * Constructor con parámetros.
   * 
   * @param msg mensaje a mostrar
   * @param error indica si es un mensaje de error
   */
  private ServerMessage(String msg, boolean error) {
    this.msg = msg;
    this.error = error;
  }

  /** @return true si es un error, false si no */  
  public boolean isError() {
    return error;
  }

  /** @return representación del mensaje en String */
  @Override
  public String toString() {
    return msg;
  }

}
