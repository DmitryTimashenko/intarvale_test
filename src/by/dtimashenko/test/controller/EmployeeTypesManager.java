package by.dtimashenko.test.controller;

import by.dtimashenko.test.data.source.files.DataExtractor;
import by.dtimashenko.test.entity.EmployeeType;
import by.dtimashenko.test.entity.Entity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitry Timasheko
 */
public class EmployeeTypesManager {

    private static EmployeeTypesManager instance;
    public static final String EMPLOYEE_TYPES_DATA_FILE = "Types.txt";

    protected HashMap<Integer, EmployeeType> typesMap = new HashMap<Integer, EmployeeType>();

    private EmployeeTypesManager() {

        try {
            DataExtractor extractor = new DataExtractor(EMPLOYEE_TYPES_DATA_FILE);
            addTypes(extractor.getList(EmployeeType.class));

        } catch (IOException ex) {
            Logger.getLogger(EmploeyesManager.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static EmployeeTypesManager getInstance() {
        if (instance == null) {
            instance = new EmployeeTypesManager();
        }
        return instance;
    }

    public void addTypes(ArrayList<Entity> employeesList) {
        for (Entity item : employeesList) {
            EmployeeType et = (EmployeeType) item;
            typesMap.put(et.getId(), et);
        }
    }
    
    public HashMap<Integer, EmployeeType> getMap() {
        return typesMap;
    }
    
    public EmployeeType getType(int index) {
        if (index < 0 || index >= count()) {
            return null;
        }

        return typesMap.get(index);
    }
    
    public String getTypeDescription(int index) {
        EmployeeType type = getType(index);
        if(type != null) {
            return type.getDescriptioin();
        }
        return "";
    }
    
    public int count() {
        return typesMap.size();
    }
}
