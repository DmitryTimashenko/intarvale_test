package by.dtimashenko.test.ui;

import by.dtimashenko.test.controller.EmploeyesManager;
import by.dtimashenko.test.controller.EmployeeTypesManager;
import by.dtimashenko.test.entity.Employee;
import java.util.ArrayList;

/**
 *
 * @author Dmitry Timasheno
 */
public class EmployesListConsole extends BaseConsole {

    protected ArrayList<Employee> employeesList; 
    protected int order = EmploeyesManager.ORDER_BY_ID;
    protected String leftAlignFormat = "| %-4d | %-30s | %-10s | %n";

    public EmployesListConsole() {

        menu = new String[] {
        "[1] Order by Name",
        "[2] Order by date of employment",
        "[3] Edit Employee Type",
        "[0] Return back"
        };
        
    }

    @Override
    public int actions(int choice) throws AssertionError {
        
        switch (choice) {
            case 1:
                order = EmploeyesManager.ORDER_BY_FULLNAME;
                System.out.println("Order by Name");
                break;   
            case 2:
                order = EmploeyesManager.ORDER_BY_EMPLOYEE_DATE;
                System.out.println("Order by date");
                break;
            case 3:
                editEmployeeType();
                break;
        }
        
        return choice;
    }
    
    @Override
    protected void printContent() {
        ArrayList<Employee> employeesList = employeeManager.getSortedList(order);

        for (Employee emp : employeesList) {
            
            System.out.format(leftAlignFormat, 
                    emp.getId(), 
                    emp.getFullName(),
                    typesManager.getTypeDescription(emp.getTypeId())
            );
        }
        System.out.println();
        
    }
    
    protected void editEmployeeType() {
        System.out.print("-= EDIT EMPLOYEE TYPE =-");
        System.out.println("Enter Employee ID: ");
        
        int userId = readInt(0, employeeManager.count());
        Employee employee = employeeManager.getEmployee(userId);
        
        EditTypeConsole editTypeConsole = new EditTypeConsole(employee);
        editTypeConsole.start();
    }
    
   
    
}
