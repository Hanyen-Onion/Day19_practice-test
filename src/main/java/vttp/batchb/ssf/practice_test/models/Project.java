package vttp.batchb.ssf.practice_test.models;

public class Project {
    private String id;
    private String projName;
    private String description;
    private String dueDate;
    private String priority;
    private String status;
    private String createdDate;
    private String updateDate;
    
    //getter setter
    public String getId() {    return id;}
    public void setId(String id) {this.id = id;}
    
    public String getProjName() {    return projName;}
    public void setProjName(String projName) {    this.projName = projName;}
    
    public String getDescription() {    return description;}
    public void setDescription(String description) {    this.description = description;}
    
    public String getDueDate() {    return dueDate;}
    public void setDueDate(String dueDate) {    this.dueDate = dueDate;}
    
    public String getPriority() {    return priority;}
    public void setPriority(String priority) {    this.priority = priority;}
    
    public String getStatus() {    return status;}
    public void setStatus(String status) {    this.status = status;}
    
    public String getCreatedDate() {    return createdDate;}
    public void setCreatedDate(String createdDate) {    this.createdDate = createdDate;}
    
    public String getUpdateDate() {    return updateDate;}
    public void setUpdateDate(String updateDate) {    this.updateDate = updateDate;}

    @Override
    public String toString() {
        return "Project name: %s".formatted(getProjName());
    }
    
}
