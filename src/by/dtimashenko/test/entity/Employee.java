package by.dtimashenko.test.entity;

/**
 *
 * @author Dmitry Timashenko
 */
public class Employee implements Entity {
    protected int id;
    protected String fullName;
    protected String birthDate;
    protected String employmentDate;
    protected int typeId = 0;
    protected EmployeeRole role;
    protected int managerId = 0;
    protected Employee manager;

    public Employee() {
    }

    public int getId() {
        return id;
    }

    public void setId(String id) {
        this.id = Integer.parseInt(id);
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmploymentDate() {
        return employmentDate;
    }

    public void setEmploymentDate(String employmentDate) {
        this.employmentDate = employmentDate;
    }

    public int getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = Integer.parseInt(typeId);
    }
    
    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(String managerId) {
        this.managerId = Integer.parseInt(managerId);
    }
    
    

    @Override
    public String toString() {
        return "Id=" + id + 
                ",FullName=" + fullName + 
                ",BirthDate=" + birthDate + 
                ",EmploymentDate=" + employmentDate +
                ",TypeId=" + typeId +
                ",ManagerId=" + managerId;
    }


}
