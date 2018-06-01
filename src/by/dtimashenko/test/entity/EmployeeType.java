package by.dtimashenko.test.entity;

/**
 *
 * @author Dmitry Timashenko
 */
public class EmployeeType implements Entity{
    protected int id;
    protected String title;
    protected String descriptioin = "";

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescriptioin() {
        return descriptioin;
    }

    public void setDescriptioin(String descriptioin) {
        this.descriptioin = descriptioin;
    }
    
    @Override
    public String toString() {
        return "Id=" + this.id + 
                ",Title=" + this.title + 
                ",Description=" + this.descriptioin;
    }
}
