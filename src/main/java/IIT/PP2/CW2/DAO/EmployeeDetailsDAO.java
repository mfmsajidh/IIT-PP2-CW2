package IIT.PP2.CW2.DAO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class EmployeeDetailsDAO {

    private final SimpleStringProperty defaultId;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private LocalDate dateOfBirth;
    private final SimpleStringProperty contactNumber;
    private final SimpleStringProperty jobRole;
    private final SimpleStringProperty salary;

    public EmployeeDetailsDAO(String defaultId, Integer id, String name, LocalDate dateOfBirth, String contactNumber, String jobRole, String salary) {
        this.defaultId = new SimpleStringProperty(defaultId);
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.dateOfBirth = dateOfBirth;
        this.contactNumber  = new SimpleStringProperty(contactNumber);
        this.jobRole  = new SimpleStringProperty(jobRole);
        this.salary  = new SimpleStringProperty(salary);
    }

    public String getDefaultId() {
        return defaultId.get();
    }

    public Integer getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String thisName){
        name.set(thisName);
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate thisDateOfBirth){
        dateOfBirth = thisDateOfBirth;
    }

    public String getContactNumber() {
        return  contactNumber.get();
    }

    public void setContactNumber(String thisContactNumber){
        contactNumber.set(thisContactNumber);
    }

    public String getJobRole() {
        return  jobRole.get();
    }

    public void setJobRole(String thisJobRole){
        jobRole.set(thisJobRole);
    }

    public String getSalary() {
        return  salary.get();
    }

    public void setSalary(String thisSalary){
        salary.set(thisSalary);
    }



}
