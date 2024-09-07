package com.clearbill.peermanager.ms.dto.frienship;

import com.clearbill.peermanager.ms.dto.customer.CustomerResponseDTO;
import com.clearbill.peermanager.ms.dto.expenses.ExpenseResponseDTO;
import com.clearbill.peermanager.ms.dto.payments.PaymentsResponseDTO;
import com.clearbill.peermanager.ms.entities.Customer;
import com.clearbill.peermanager.ms.entities.MetaData;

import java.util.ArrayList;
import java.util.List;

public class ListOfFriendShipResponseDTO {

    public String id;

    public String category;

    public String status;

    public String createdByCustomer;

    public MetaData metaData;

    public List<Customer> customersInvolved;

    public String createdOn;

    public List<ExpenseResponseDTO> expenses;

    public List<PaymentsResponseDTO> payments;

    public List<CustomerResponseDTO> friendDetails;


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

    public MetaData getMetaData() {
        return metaData;
    }

    public void setMetaData(MetaData metaData) {
        this.metaData = metaData;
    }

    public List<Customer> getCustomersInvolved() {
        return customersInvolved;
    }

    public void setCustomersInvolved(List<Customer> customersInvolved) {
        this.customersInvolved = customersInvolved;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public List<ExpenseResponseDTO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseResponseDTO> expenses) {
        this.expenses = expenses;
    }

    public List<PaymentsResponseDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentsResponseDTO> payments) {
        this.payments = payments;
    }

    public List<CustomerResponseDTO> getFriendDetails() {
        return friendDetails;
    }

    public void setFriendDetails(List<CustomerResponseDTO> friendDetails) {
        this.friendDetails = friendDetails;
    }

    public void updateFriendDetails(CustomerResponseDTO friendDetail){
        if(this.friendDetails==null){
            this.friendDetails = new ArrayList<>();
        }
        this.friendDetails.add(friendDetail);
    }

    @Override
    public String toString() {
        return "ListOfFriendShipResponseDTO{" +
                "id='" + id + '\'' +
                ", category='" + category + '\'' +
                ", status='" + status + '\'' +
                ", createdByCustomer='" + createdByCustomer + '\'' +
                ", metaData=" + metaData +
                ", customersInvolved=" + customersInvolved +
                ", createdOn='" + createdOn + '\'' +
                ", expenses=" + expenses +
                ", payments=" + payments +
                ", friendDetails=" + friendDetails +
                '}';
    }
}
