package me.ddmiher880.models;

import java.sql.Timestamp;

import me.ddmiher880.utils.Payroll;

public final class Employee implements Comparable<Employee> {

	private String dni, name;
  private Character sex;
  private Integer category, years, salary;
	private Timestamp signedUpDate, modifiedDate;

  /**
   * Constructor con parámetros.
   * 
   * @param dni identificador del empleado
   * @param name nombre del empleado
   * @param sex sexo del empleado
   * @param category categoría profesional del empleado
   * @param years años trabajados del empleado
   * @param signedUpDate fecha de alta del empleado
   * @param modifiedDate fecha de última modificación
   */
	public Employee(String dni, String name, char sex, int category, int years, Timestamp signedUpDate, Timestamp modifiedDate) {
    this.dni = dni;
    this.name = name;
    this.sex = sex;
    this.category = category;
    this.years = years;
    setSalary();
    this.signedUpDate = signedUpDate;
    this.modifiedDate = modifiedDate;
  }

  /**
   * Constructor con parámetros.
   * 
   * @param dni identificador del empleado
   * @param name nombre del empleado
   * @param sex sexo del empleado
   * @param category categoría profesional del empleado
   * @param years años trabajados del empleado
   * @param salary salario del empleado
   */
	public Employee(String dni, String name, char sex, int category, int years, int salary) {
    this.dni = dni;
    this.name = name;
    this.sex = sex;
    this.category = category;
    this.years = years;
    this.salary = salary;
  }
	
  /** Constructor por defecto. */
	public Employee() {
  }

  /** @return identificador del empleado */
	public String getDni() {
		return dni;
	}

  /** @param dni identificador del empleado */
	public void setDni(String dni) {
		this.dni = dni;
	}

  /** @return nombre del empleado */
	public String getName() {
		return name;
	}

  /** @param name nombre del empleado */
	public void setName(String name) {
		this.name = name;
	}

  /** @return sexo del empleado */
	public char getSex() {
		return sex;
	}

  /** @param sex sexo del empleado */
	public void setSex(char sex) {
		this.sex = sex;
	}

  /** @return categoría del empleado */
	public int getCategory() {
		return category;
	}

  /** @param category categoría del empleado */
	public void setCategory(int category) {
		this.category = category;
	}

  /** @return años trabajados del empleado */
	public int getYears() {
		return years;
	}

  /** @param years años trabajados del empleado */
	public void setYears(int years) {
		this.years = years;
	}

  /** @return salario del empleado */
	public int getSalary() {
		return salary;
	}

	public void setSalary() {
		salary = Payroll.getSalary(this);
	}

  /** @return fecha de alta del empleado */
	public Timestamp getSignedUpDate() {
		return signedUpDate;
	}

  /** @param signedUpDate fecha de alta del empleado */
	public void setSignedUpDate(Timestamp signedUpDate) {
		this.signedUpDate = signedUpDate;
	}

  /** @return fecha de última modificación */
	public Timestamp getModifiedDate() {
		return modifiedDate;
	}

  /** @param modifiedDate fecha de última modificación */
	public void setModifiedDate(Timestamp modifiedDate) {
		this.modifiedDate = modifiedDate;
	}

  /** @return representación del empleado en String */
	@Override
	public String toString() {
		return String.format("Employee[%s, %s, %c, %d, %d, %s, %s]", dni, name, sex, category, years, signedUpDate, modifiedDate);
	}
  
  /**
   * Compara empleados por su identificador.
   * 
   * @param other el empleado a comparar
   * @return la diferencia de sus identificadores
   */
  @Override
  public int compareTo(Employee other) {
    return this.dni.compareTo(other.dni);
  }
	
}
