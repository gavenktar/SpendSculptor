package by.kirylarol.spendsculptor.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;

import java.sql.Date;
import java.util.List;

@Entity
@Table (name = "Account")
public class Account {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "account_id")
    int id;


    @Column(name = "name")
    private String name;

    @Column (name = "created_at")
    private Date dateCreated;

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date dateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public List<Receipt> receiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }

    public List<AccountUser> accountUsers() {
        return accountUsers;
    }

    public void setAccountUsers(List<AccountUser> accountUsers) {
        this.accountUsers = accountUsers;
    }

    public List<Goal> goalList() {
        return goalList;
    }

    public void setGoalList(List<Goal> goalList) {
        this.goalList = goalList;
    }

    @OneToMany (mappedBy = "account")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<Receipt> receiptList;

    @OneToMany (mappedBy = "account")
    @Cascade({org.hibernate.annotations.CascadeType.ALL})
    private List<AccountUser> accountUsers;

    @OneToMany (mappedBy = "account")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Goal> goalList;

}
