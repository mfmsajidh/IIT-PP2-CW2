package IIT.PP2.CW2.DTO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;
import java.util.Date;

public class EmployeeDetailsDTO {

    private final SimpleStringProperty defaultId;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private LocalDate dateOfBirth;
    private final SimpleStringProperty contactNumber;

    public EmployeeDetailsDTO(String defaultId, Integer id, String name, LocalDate dateOfBirth, String contactNumber) {
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



}
