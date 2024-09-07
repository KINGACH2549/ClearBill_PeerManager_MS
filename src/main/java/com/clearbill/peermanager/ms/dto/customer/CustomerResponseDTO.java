package com.clearbill.peermanager.ms.dto.customer;

public class CustomerResponseDTO {

    private String id;
    private String name;
    private String profilePicture;
    private String createdOn;
    private String joinedGroupAt;
    private boolean isAccepted;
    private String role;

    public CustomerResponseDTO(){
        this.role ="acceptor";
    }

    public String getRole() {
        return role;
    }

    public String getJoinedGroupAt() {
        return joinedGroupAt;
    }

    public void setJoinedGroupAt(String joinedGroupAt) {
        this.joinedGroupAt = joinedGroupAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }



    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "CustomerResponseDTO{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", profilePicture='" + profilePicture + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", joinedGroupAt='" + joinedGroupAt + '\'' +
                ", isAccepted=" + isAccepted +
                ", role='" + role + '\'' +
                '}';
    }
}
