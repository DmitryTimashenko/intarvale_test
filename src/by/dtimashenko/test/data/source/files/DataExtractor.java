package by.dtimashenko.test.data.source.files;

import by.dtimashenko.test.entity.Employee;
import by.dtimashenko.test.entity.Entity;
import java.io.*;
import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dmitry Timashenko
 */
public class DataExtractor {

    protected File file;
    protected BufferedReader bufferReader;

    public DataExtractor(String filePath) throws IOException {
        this.file = new File(filePath);
        this.bufferReader = new BufferedReader(new FileReader(filePath));
    }

    public ArrayList<Entity> getList(Class entityClass) throws IOException {

        String line;
        Entity entity = null;
        ArrayList<Entity> list = new ArrayList<Entity>();
        do {
            line = this.bufferReader.readLine();

            if (line != null) {
                try {
                    entity = (Entity) entityClass.newInstance();
                } catch (Exception ex) {
                    Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
                    break;
                }

                line = line.replaceAll("\\s*,\\s*", ",");
                String[] parts = line.split(",");

                for (String part : parts) {
                    String[] partArray = part.split("=");
                    String fieldName = partArray[0];
                    String fieldValue = partArray[1];

                    try {
                        Method method = entityClass.getMethod("set" + fieldName, String.class);
                        method.invoke(entity, fieldValue);
                    } catch (Exception ex) {
                        Logger.getLogger(DataExtractor.class.getName()).log(Level.SEVERE, null, ex);
                        break;
                    }
                }
                list.add(entity);
            }
        } while (line != null);
        return  list;

    }


}
