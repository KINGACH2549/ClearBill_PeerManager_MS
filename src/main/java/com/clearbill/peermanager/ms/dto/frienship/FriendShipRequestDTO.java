package com.clearbill.peermanager.ms.dto.frienship;


import jakarta.validation.constraints.NotEmpty;

public class FriendShipRequestDTO {


    @NotEmpty
    public String action;

    public String email;

    public String contact;

    @NotEmpty
    public String requestedBy;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String category;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(String requestedBy) {
        this.requestedBy = requestedBy;
    }
}
