package com.clearbill.peermanager.ms.entities;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class Customer {

    public String id;
    public String joinedGroupAt;
    public boolean isAccepted;
    private String role;

    public boolean isAccepted() {
        return isAccepted;
    }

    public void setAccepted(boolean accepted) {
        isAccepted = accepted;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public boolean getIsAccepted() {
        return isAccepted;
    }

    public void setIsAccepted(boolean isAccepted) {
        this.isAccepted = isAccepted;
    }

    public String getJoinedGroupAt() {
        return joinedGroupAt;
    }

    public void setJoinedGroupAt(String joinedGroupAt) {
        this.joinedGroupAt = joinedGroupAt;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id='" + id + '\'' +
                ", joinedGroupAt='" + joinedGroupAt + '\'' +
                ", isAccepted=" + isAccepted +
                ", role='" + role + '\'' +
                '}';
    }
}
