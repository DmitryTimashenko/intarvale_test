package by.dtimashenko.test.controller;

import by.dtimashenko.test.data.source.files.DataExtractor;
import by.dtimashenko.test.entity.Employee;
import by.dtimashenko.test.entity.Entity;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 * @author Dmitry Timashenko
 */
public final class EmploeyesManager {

    private static EmploeyesManager instance;
    public static final String EMPLOYEES_DATA_FILE = "Employees.txt";

    public static final int ORDER_BY_ID = 0;
    public static final int ORDER_BY_FULLNAME = 1;
    public static final int ORDER_BY_EMPLOYEE_DATE = 2;

    protected HashMap<Integer, Employee> employeeMap = new HashMap<Integer, Employee>();

    private EmploeyesManager() {

        try {
            DataExtractor extractor = new DataExtractor(EMPLOYEES_DATA_FILE);
            addEmployees(extractor.getList(Employee.class));

        } catch (IOException ex) {
            Logger.getLogger(EmploeyesManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static EmploeyesManager getInstance() {
        if (instance == null) {
            instance = new EmploeyesManager();
        }
        return instance;
    }

    public int addEmployees(ArrayList<Entity> employeesList) {

        for (Entity item : employeesList) {
            Employee e = (Employee) item;
            employeeMap.put(e.getId(), e);
        }

        // TODO: Move to strategy
        saveToFile();

        return count();
    }
    
    public void removeEmployee(int employeeId) {
        employeeMap.remove(employeeId);
        saveToFile();
    }

    public void saveToFile() {
        Path path = Paths.get(EMPLOYEES_DATA_FILE);
        try (Writer writer = Files.newBufferedWriter(path)) {
            this.employeeMap.forEach((key, employee) -> {
                try {
                    writer.write(employee.toString() + "\n");
                } catch (IOException ex) {
                    System.out.println("ERROR: Writing to " + EMPLOYEES_DATA_FILE + " file is not possible");
                }
            });
        } catch (Exception ex) {
            Logger.getLogger(EmploeyesManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     *
     * @return
     */
    public int count() {
        return employeeMap.size();
    }

    /**
     *
     * @param index
     * @return
     */
    public Employee getEmployee(int index) {
        if (index < 0 || index >= count()) {
            return null;
        }

        return employeeMap.get(index);
    }
    
    public ArrayList<Employee> getSortedList(int order) {
        ArrayList<Employee> list = new ArrayList<>(employeeMap.values());
        Comparator<Employee> comparator = getComparator(order);
        list.sort(comparator);
        return list;
    }
    
    public ArrayList<Employee> getManagers() {
        ArrayList<Employee> list = new ArrayList<>(employeeMap.values());
        List<Employee> result = list.stream().filter(employee -> employee.getTypeId() == 5)
                .collect(Collectors.toList());
        return (ArrayList<Employee>) result;
    }

    protected Comparator<Employee> getComparator(int order) {
        switch (order) {
            case ORDER_BY_FULLNAME:
                return new NameComparator(); 
            case ORDER_BY_EMPLOYEE_DATE:
                return new EmploymentDateComparator();
            default:
                return new IdComparator();
        }       
    }
}
