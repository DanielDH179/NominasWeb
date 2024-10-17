package me.ddmiher880.utils;

import java.util.regex.Pattern;

public class DNI {
  
  private static final Pattern REGEXP = Pattern.compile("[0-9]{8}[A-Z]");
  private static final String CONTROL = "TRWAGMYFPDXBNJZSQVHLCKE";

  /**
   * Devuelve el dígito de control del DNI.
   * 
   * @param dni código de 8 cifras y 1 letra 
   * @return letra correcta de la secuencia numérica
   */
  public static char getControlDigit(String dni) {
    return CONTROL.charAt(Integer.parseInt(dni.substring(0, 8)) % 23);
  }

  /**
   * Comprueba la validez del DNI.
   * 
   * @param dni código de 8 cifras y 1 letra
   * @return true si es válido, false en caso contrario
   */
  public static boolean check(String dni) {
    return REGEXP.matcher(dni).matches() && dni.charAt(8) == getControlDigit(dni);
  }

}
