package IIT.PP2.CW2.DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.util.Date;

public class EmployeeDetailsDTO {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private Date dateOfBirth;
    private final SimpleStringProperty contactNumber;

    public EmployeeDetailsDTO(Integer id, String name, Date dateOfBirth, String contactNumber) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.dateOfBirth = dateOfBirth;
        this.contactNumber  = new SimpleStringProperty(contactNumber);

    }

    public Integer getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public String getContactNumber() {
        return  contactNumber.get();
    }

    public void setName(String thisName){
        name.set(thisName);
    }

    public void setDateOfBirth(Date thisDateOfBirth){
        dateOfBirth = thisDateOfBirth;
    }


    public void setContactNumber(String thisContactNumber){
        contactNumber.set(thisContactNumber);
    }



}
