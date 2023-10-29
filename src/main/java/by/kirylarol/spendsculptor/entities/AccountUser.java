package by.kirylarol.spendsculptor.entities;

import jakarta.persistence.*;

@Entity
@Table (name = "account_to_user")
public class AccountUser {

    @ManyToOne
    @JoinColumn (name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;



    @Column
    private double weight;
    @Column(name = "permission")
    @Enumerated(EnumType.STRING)
    private Account_enum permission;

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int id;


    public Account account() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public User user() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public double weight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Account_enum permission() {
        return permission;
    }

    public void setPermission(Account_enum permission) {
        this.permission = permission;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
