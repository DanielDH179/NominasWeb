package me.ddmiher880.utils;

public class SortCriterion {

  private final String value, DEFAULT = "dni";
  private final boolean descending;

  /**
   * Constructor con parámetros.
   * 
   * @param value el campo por el que se ordena (por defecto "dni")
   * @param desc indica si el orden es descendente
   */
  public SortCriterion(String value, String desc) {
    this.value = value != null ? value : DEFAULT;
    this.descending = desc != null;
  }

  /** Constructor por defecto. */
	public SortCriterion() {
    value = null;
    descending = false;
  }

  /** @return representación del criterio de ordenación en String */
  @Override
  public String toString() {
    return String.format(" ORDER BY %s %s", value, descending ? "DESC" : "");
  }

}
