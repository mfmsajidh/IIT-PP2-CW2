package IIT.PP2.CW2.DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class EmployeeDetailsDTO {

    private final SimpleStringProperty defaultId;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private Date dateOfBirth;
    private final SimpleStringProperty contactNumber;

    public EmployeeDetailsDTO(String defaultId, Integer id, String name, Date dateOfBirth, String contactNumber) {
        this.defaultId = new SimpleStringProperty(defaultId);
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.dateOfBirth = dateOfBirth;
        this.contactNumber  = new SimpleStringProperty(contactNumber);

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date thisDateOfBirth){
        dateOfBirth = thisDateOfBirth;
    }

    public String getContactNumber() {
        return  contactNumber.get();
    }

    public void setContactNumber(String thisContactNumber){
        contactNumber.set(thisContactNumber);
    }



}
