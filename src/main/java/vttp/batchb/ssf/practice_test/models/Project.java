package vttp.batchb.ssf.practice_test.models;

import java.util.Date;

import jakarta.validation.constraints.*;

public class Project {
    
    @NotNull(message = "Id is required")
    @Size( max = 50, message = "Id cannot be more than 50 characters")
    private String id;

    @NotNull(message = "Name is required")
    @Size( min = 10, max = 50, message = "Name cannot be more than 50 characters")
    private String projName;
    
    @Size( max = 255, message = "Description cannot be more than 255 characters")
    private String description;

    private String priority;
    private String status;

    @FutureOrPresent
    private Date dueDate;

    private Date createdDate;
    private Date updatedDate;
    
    //getter setter
    public String getId() {    return id;}
    public void setId(String id) {this.id = id;}
    
    public String getProjName() {    return projName;}
    public void setProjName(String projName) {    this.projName = projName;}
    
    public String getDescription() {    return description;}
    public void setDescription(String description) {    this.description = description;}
    
    public String getPriority() {    return priority;}
    public void setPriority(String priority) {    this.priority = priority;}
    
    public String getStatus() {    return status;}
    public void setStatus(String status) {    this.status = status;}

    public Date getDueDate() {    return dueDate;}
    public void setDueDate(Date dueDate) {    this.dueDate = dueDate;}
    
    public Date getCreatedDate() {    return createdDate;}
    public void setCreatedDate(Date createdDate) {    this.createdDate = createdDate;}
    
    public Date getUpdatedDate() {    return updatedDate;}
    public void setUpdatedDate(Date updateDate) {    this.updatedDate = updateDate;}

    @Override
    public String toString() {
        return "\n" +
        "Id: %s\n".formatted(id) +
        "ProjectName: %s\n".formatted(projName) +
        "Description: %s\n".formatted(description) +
        "DueDate: %s\n".formatted(dueDate) +
        "Priority level: %s\n".formatted(priority) +
        "Status: %s\n".formatted(status) +
        "CreateDate: %s\n".formatted(createdDate) +
        "UpdatedDate: %s\n".formatted(updatedDate); 
    }
    
}
