package IIT.PP2.CW2.DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class EmployeeDetailsDTO {

    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty dateOfBirth;
    private final SimpleStringProperty contactNumber;

    public EmployeeDetailsDTO(Integer id, String name, String dateOfBirth, String contactNumber) {
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.dateOfBirth = new SimpleStringProperty(dateOfBirth);
        this.contactNumber  = new SimpleStringProperty(contactNumber);

    }

    public Integer getId() {
        return id.get();
    }

    public String getName() {
        return name.get();
    }

    public String getDateOfBirth() {
        return dateOfBirth.get();
    }

    public String getContactNumber() {
        return  contactNumber.get();
    }

    public void setName(String thisName){
        name.set(thisName);
    }

    public void setDateOfBirth(String thisDateOfBirth){
        dateOfBirth.set(thisDateOfBirth);
    }


    public void setContactNumber(String thisContactNumber){
        contactNumber.set(thisContactNumber);
    }



}
