package by.kirylarol.spendsculptor.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
@Entity
@Table(name = "goal")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "goal_id")
    private int id;

    @Column(name = "goal_name")
    String name;

    @Column(name = "created_at")
    private LocalDate created;

    @Column(name = "valid_until")
    private LocalDate valid;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @Column(name = "goal_value")
    private BigDecimal goal;
    @Column(name = "current_state")
    private BigDecimal state;


    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @JsonGetter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public LocalDate created() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    @JsonGetter
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public LocalDate valid() {
        return valid;
    }

    @JsonGetter
    @Transient
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setValid(LocalDate valid) {
        this.valid = valid;
    }

    public Account account() {
        return account;
    }

    public void setAccount(Account accountG) {
        this.account = accountG;
    }

    @JsonGetter
    public BigDecimal goal() {
        return goal;
    }

    public void setGoal(BigDecimal goal) {
        this.goal = goal;
    }

    @JsonGetter
    public BigDecimal state() {
        return state;
    }

    public void setState(BigDecimal state) {
        this.state = state;
    }
}
