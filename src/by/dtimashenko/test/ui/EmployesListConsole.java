package by.dtimashenko.test.ui;

import by.dtimashenko.test.controller.EmploeyesManager;
import by.dtimashenko.test.data.source.files.DataExtractor;
import by.dtimashenko.test.entity.Employee;
import by.dtimashenko.test.entity.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitry Timasheno
 */
public class EmployesListConsole extends BaseConsole {

    protected ArrayList<Employee> employeesList;
    protected int order = EmploeyesManager.ORDER_BY_ID;
    protected String leftAlignFormat = "| %-4d | %-30s | %-10s | %n";

    public EmployesListConsole() {
        menu = new String[]{
            "[0] Return back",
            "[1] Add Employees from file",
            "[2] Remove Employee by Id",
            "[3] Order by Name",
            "[4] Order by date of employment",
            "[5] Edit Employee Type"
        };
    }

    @Override
    public int actions(int choice) throws AssertionError {

        switch (choice) {
            case 1:
                addEmployee();
                break;
            case 2:
                break;
            case 4:
                order = EmploeyesManager.ORDER_BY_FULLNAME;
                System.out.println("Order by Name");
                break;
            case 5:
                order = EmploeyesManager.ORDER_BY_EMPLOYEE_DATE;
                System.out.println("Order by date");
                break;
            case 6:
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
    
    private void addEmployee() {
        System.out.println("Enter data file path: ");
        String filePath = sc.nextLine();

        HashMap<Integer, Employee> employeeMap = null;
        try {
            DataExtractor dataExtractor = new DataExtractor(filePath);
            ArrayList<Entity> employeesList = dataExtractor.getList(Employee.class);
            employeeManager.addEmployees(employeesList);
        } catch (Exception ex) {
            Logger.getLogger(EmployesConsole.class.getName()).log(Level.SEVERE, null, ex);
        }

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
