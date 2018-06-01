package by.dtimashenko.test.ui;

import by.dtimashenko.test.entity.Employee;
import by.dtimashenko.test.entity.EmployeeType;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Dmitry Timasheno
 */
public class EditTypeConsole extends BaseConsole {

    protected Employee employee; 
    
    public EditTypeConsole(Employee emp) {
        employee = emp;
        
        menu = new String[typesManager.count()];
        HashMap<Integer, EmployeeType> types = typesManager.getMap();
        
        for(Map.Entry<Integer, EmployeeType> entry: types.entrySet()) {
            EmployeeType type = entry.getValue();
            menu[type.getId()] = "[" + type.getId() + "] " + type.getDescriptioin();
        }
    }

    @Override
    public int actions(int choice) throws AssertionError {
       employee.setTypeId(choice);      
       employeeManager.saveToFile();
       return 0;
    }
    
    @Override
    protected void printContent() {
    }

}
