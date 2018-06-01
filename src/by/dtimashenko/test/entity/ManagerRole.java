package by.dtimashenko.test.entity;

import java.util.HashMap;

/**
 *
 * @author Dmitry Timashenko
 */
public class ManagerRole extends EmployeeRole{
    protected HashMap<Integer, Employee> employers = new HashMap<Integer, Employee>();
  
    public static String KEY = "manager";
    
    public void addEmployee(Employee employee) {
        employers.put(employee.getId(), employee);
    }
    
    public void removeEmployee(Employee employee) {
        employers.remove(employee.getId());
    }
    
    public HashMap<Integer, Employee> getEmployees() {
        return employers;
    }
}
