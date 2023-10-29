package by.kirylarol.spendsculptor.entities;



import jakarta.persistence.*;
import jakarta.persistence.Id;
import org.hibernate.annotations.Cascade;
import org.springframework.data.annotation.*;

import javax.management.relation.Role;
import java.util.List;

@Entity
@Table(name = "userprofile")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "user_id")
    private int id;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn (name = "identity_id")
    private Identity identity;

    @Column
    private String login;

    @Column
    private String password;

    @Column
    @Enumerated(EnumType.STRING)
    private RolesSystem role;

    @OneToMany (mappedBy = "user")
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<AccountUser> accountUsers;

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Identity identity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    public String login() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String password() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RolesSystem role() {
        return role;
    }

    public void setRole(RolesSystem role) {
        this.role = role;
    }

    public List<AccountUser> accountUsers() {
        return accountUsers;
    }

    public void setAccountUsers(List<AccountUser> accountUsers) {
        this.accountUsers = accountUsers;
    }
}
