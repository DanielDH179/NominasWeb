package me.ddmiher880.utils;

import me.ddmiher880.enums.ServerMessage;

public class FormattedMessage {

  private final String msg;
  private final boolean error;

  /**
   * Constructor con parámetros.
   * 
   * @param sm mensaje del servidor sin formato
   * @param args colección indefinida de datos
   * @param error indica si es un mensaje de error
   */
  public FormattedMessage(ServerMessage sm, String... args) {
    msg = String.format(sm.toString(), (Object[]) args);
    error = sm.isError();
  }

  /**
   * Constructor con parámetros.
   * 
   * @param sm mensaje del servidor sin formato
   * @param args colección indefinida de String
   * @param error indica si es un mensaje de error
   */
  public FormattedMessage(ServerMessage sm, Character... args) {
    msg = String.format(sm.toString(), (Object[]) args);
    error = sm.isError();
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
