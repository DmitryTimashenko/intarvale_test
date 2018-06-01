package by.dtimashenko.test.ui;

import by.dtimashenko.test.controller.EmploeyesManager;
import by.dtimashenko.test.controller.EmployeeTypesManager;
import java.util.Arrays;
import java.util.Scanner;

/**
 *
 * @author Dmitry Timashenko
 */
public abstract class BaseConsole {

    protected Scanner sc;
    protected EmploeyesManager employeeManager;
    protected EmployeeTypesManager typesManager;
    protected String[] menu = {};
    protected String content = "";

    public BaseConsole() {
        sc = new Scanner(System.in);
        employeeManager = EmploeyesManager.getInstance();
                typesManager = EmployeeTypesManager.getInstance();
    }

    public void start() {
        int choice = -999;
        while (choice != 0) {
            printContent();
            printMenu();
            choice = readInt(0, menu.length - 1);
            choice = actions(choice);
        }
    }

    protected int readInt(int min, int max) {
        int choice;
        while (true) {
            try {
                choice = Integer.parseInt(sc.nextLine());
                if (choice >= min && choice <= max) {
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return choice;
    }

    protected void printMenu() {
        System.out.println(Arrays.toString(menu));
    }

    protected void printContent() {
        System.out.println(content);
    }

    public abstract int actions(int choice);

}
