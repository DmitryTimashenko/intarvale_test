package by.dtimashenko.test.ui;

import by.dtimashenko.test.data.source.files.DataExtractor;
import by.dtimashenko.test.entity.Employee;
import by.dtimashenko.test.entity.Entity;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitry Timashenko
 */
public class EmployesConsole extends BaseConsole {

    public EmployesConsole() {
        content = "---Accounting Staff Menu---";
        menu = new String[]{
            "[1] Add employee",
            "[2] Show list of employes",
            "[3] Remove Employee",
            "[0] Exit"
        };
    }

    @Override
    public int actions(int choice) throws AssertionError {
        switch (choice) {
            case 0:
                System.exit(0);
                break;
            case 1:
                addEmployee();
                break;
            case 2:
                showEmployesList();
                break;
        }
        return choice;
    }

    private void addEmployee() {
        System.out.println("Enter data file path: ");
        String filePath = sc.nextLine();

        HashMap<Integer, Entity> employeeMap = null;
        try {
            DataExtractor dataExtractor = new DataExtractor(filePath);
            ArrayList<Entity> employeesList = dataExtractor.getList(Employee.class);
            employeeManager.addEmployees(employeesList);
        } catch (Exception ex) {
            Logger.getLogger(EmployesConsole.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private void showEmployesList() {
        EmployesListConsole lc = new EmployesListConsole();
        lc.start();
    }
}
