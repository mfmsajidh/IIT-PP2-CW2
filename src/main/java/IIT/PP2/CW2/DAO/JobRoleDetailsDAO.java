package IIT.PP2.CW2.DAO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class JobRoleDetailsDAO {

    private final SimpleStringProperty defaultId;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty hourlyPay;

    public JobRoleDetailsDAO(String defaultId, Integer id, String name, String hourlyPay) {
        this.defaultId = new SimpleStringProperty(defaultId);
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.hourlyPay = new SimpleStringProperty(hourlyPay);
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

    public String getHourlyPay() {
        return hourlyPay.get();
    }

    public void setHourlyPay(String thisHourlyPay){
        hourlyPay.set(thisHourlyPay);
    }


}
