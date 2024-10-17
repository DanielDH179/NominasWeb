package me.ddmiher880.utils;

import me.ddmiher880.models.Employee;

public class Payroll {
  
  private static final int[] BASE_SALARY = {50000, 70000, 90000, 110000, 130000, 150000, 170000, 190000, 210000, 230000};

  /**
   * Calcula la nómina del empleado.
   * 
   * @param e empleado
   * @return sueldo del empleado
   */
  public static int getSalary(Employee e) {
    return BASE_SALARY[e.getCategory() - 1] + 5000 * e.getYears();
  }

}
