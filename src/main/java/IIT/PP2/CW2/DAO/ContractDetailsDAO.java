package IIT.PP2.CW2.DAO;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

import java.time.LocalDate;

public class ContractDetailsDAO {

    private final SimpleStringProperty defaultId;
    private final SimpleIntegerProperty id;
    private final SimpleStringProperty name;
    private final SimpleStringProperty description;
    private LocalDate creationDate;
    private final SimpleStringProperty jobType;
    private final SimpleStringProperty projectLeader;
    private final SimpleStringProperty customer;

    public ContractDetailsDAO(String defaultId, Integer id, String name, String description, LocalDate creationDate, String jobType, String projectLeader, String customer) {
        this.defaultId = new SimpleStringProperty(defaultId);
        this.id = new SimpleIntegerProperty(id);
        this.name = new SimpleStringProperty(name);
        this.description = new SimpleStringProperty(description);
        this.creationDate = creationDate;
        this.jobType = new SimpleStringProperty(jobType);
        this.projectLeader = new SimpleStringProperty(projectLeader);
        this.customer = new SimpleStringProperty(customer);

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

    public String getDescription() {
        return description.get();
    }

    public void setDescription(String thisDescription){
        description.set(thisDescription);
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDate thisCreationDate){
        creationDate = thisCreationDate;
    }

    public String getJobType() {
        return  jobType.get();
    }

    public void setJobType(String thisJobType){
        jobType.set(thisJobType);
    }

    public String getProjectLeader() {
        return  projectLeader.get();
    }

    public void setProjectLeader(String thisProjectLeader){
        projectLeader.set(thisProjectLeader);
    }

    public String getCustomer() {
        return  customer.get();
    }

    public void setCustomer(String thisCustomer){
        customer.set(thisCustomer);
    }



}
