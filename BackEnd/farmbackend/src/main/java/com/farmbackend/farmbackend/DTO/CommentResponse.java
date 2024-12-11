package com.farmbackend.farmbackend.DTO;

import java.util.Date;

public class CommentResponse {
    private String content;
    private Date createDate;
    private Integer supplierId; // Nullable
    private Integer farmerId;   // Nullable
    private String firstName;
    private String lastName;

    // Constructor
    public CommentResponse(String content, Date createDate, Integer supplierId, Integer farmerId, String firstName, String lastName) {
        this.content = content;
        this.createDate = createDate;
        this.supplierId = supplierId;
        this.farmerId = farmerId;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    // Getters and setters
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }

    public Integer getFarmerId() {
        return farmerId;
    }

    public void setFarmerId(Integer farmerId) {
        this.farmerId = farmerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
