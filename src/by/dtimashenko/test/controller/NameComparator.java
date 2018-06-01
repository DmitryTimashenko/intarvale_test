package by.dtimashenko.test.controller;

import by.dtimashenko.test.entity.Employee;
import java.util.Comparator;

/**
 *
 * @author Dmitry Timashenko
 */
public class NameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee e1, Employee e2) {
        return e1.getFullName().compareTo(e2.getFullName());
    }
}
