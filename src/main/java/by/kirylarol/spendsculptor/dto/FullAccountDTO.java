package by.kirylarol.spendsculptor.dto;

import by.kirylarol.spendsculptor.entities.*;

import java.util.List;

public class FullAccountDTO {

    Account_enum userRole;
    Account account;
    List<AccountUser> userList;
    List<Goal> goalList;
    List<Receipt> receiptList;

    double weight;

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public FullAccountDTO() {

    }

    public Account_enum getUserRole() {
        return userRole;
    }

    public void setUserRole(Account_enum userRole) {
        this.userRole = userRole;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }


    public List<AccountUser> getUserList() {
        return userList;
    }

    public void setUserList(List<AccountUser> userList) {
        this.userList = userList;
    }

    public List<Goal> getGoalList() {
        return goalList;
    }

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }

    public List<Receipt> getReceiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }
}
