package com.clearbill.peermanager.ms.entities;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.data.annotation.Id;
import org.springframework.data.couchbase.core.mapping.Document;
import org.springframework.data.couchbase.core.mapping.id.GeneratedValue;
import org.springframework.data.couchbase.core.mapping.id.GenerationStrategy;
import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import java.util.List;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Document
public class FriendShip {


    @Id
    @GeneratedValue(strategy = GenerationStrategy.UNIQUE)
    public String id;

    @NotNull
    public String category;

    @NotNull
    public String status;

    @NotNull
    public String createdByCustomer;

    @NotNull
    public String createdOn;

    @NotNull
    public String updatedOn;

    @NotNull
    public List<StatusHistory> statusHistory;

    @NotNull
    public List<Actions> actions;

    @NotNull
    public MetaData metaData;

    @NotNull
    public List<Customer> customersInvolved;


    public void updateActions(Actions action){
        actions.add(action);
    }

    public void updateStatusHistory(StatusHistory history){
        statusHistory.add(history);
    }

    public String getId() {
        return id;
    }


    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCreatedByCustomer() {
        return createdByCustomer;
    }

    public void setCreatedByCustomer(String createdByCustomer) {
        this.createdByCustomer = createdByCustomer;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getUpdatedOn() {
        return updatedOn;
    }

    public void setUpdatedOn(String updatedOn) {
        this.updatedOn = updatedOn;
    }

    public List<StatusHistory> getStatusHistory() {
        return statusHistory;
    }

    public void setStatusHistory(List<StatusHistory> statusHistory) {
        this.statusHistory = statusHistory;
    }

    public List<Actions> getActions() {
        return actions;
    }

    public void setActions(List<Actions> actions) {
        this.actions = actions;
    }

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<Customer> getAcceptedByCustomers() {
        return customersInvolved;
    }

    public void setAcceptedByCustomers(List<Customer> acceptedByCustomers) {
        this.customersInvolved = acceptedByCustomers;
    }

    public List<Customer> getCustomersInvolved() {
        return customersInvolved;
    }

    public void setCustomersInvolved(List<Customer> customersInvolved) {
        this.customersInvolved = customersInvolved;
    }

    @Override
    public String toString() {
        return "FriendShip{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", createdByCustomer='" + createdByCustomer + '\'' +
                ", createdOn='" + createdOn + '\'' +
                ", updatedOn='" + updatedOn + '\'' +
                ", statusHistory=" + statusHistory +
                ", actions=" + actions +
                ", metaData=" + metaData +
                ", customersInvolved=" + customersInvolved +
                '}';
    }
}
