package IIT.PP2.CW2.DAO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class CustomerDetailsDAO {

    private final SimpleStringProperty defaultId;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty address;
    private final SimpleStringProperty contactNumber;

    public CustomerDetailsDAO(String defaultId, Integer id, String name, String address, String contactNumber) {
        this.defaultId = new SimpleStringProperty(defaultId);
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.contactNumber = new SimpleStringProperty(contactNumber);

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

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String thisAddress){
        address.set(thisAddress);
    }

    public String getContactNumber() {
        return  contactNumber.get();
    }

    public void setContactNumber(String thisContactNumber){
        contactNumber.set(thisContactNumber);
    }



}
