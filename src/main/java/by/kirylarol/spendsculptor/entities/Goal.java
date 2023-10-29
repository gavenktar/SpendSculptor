package by.kirylarol.spendsculptor.entities;


import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Date;

@Entity
@Table (name = "goal")
public class Goal {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "goal_id")
    private int id;

    @Column (name = "created_at")
    private Date created;

    @Column (name = "valid_until")
    private Date valid;

    @ManyToOne
    @JoinColumn (name = "account_id")
    private Account account;

    @Column(name = "goal_value")
    private BigDecimal goal;
    @Column (name = "current_state")
    private BigDecimal state;


    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date created() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public Date valid() {
        return valid;
    }

    public void setValid(Date valid) {
        this.valid = valid;
    }

    public Account account() {
        return account;
    }

    public void setAccount(Account accountG) {
        this.account = accountG;
    }

    public BigDecimal goal() {
        return goal;
    }

    public void setGoal(BigDecimal goal) {
        this.goal = goal;
    }

    public BigDecimal state() {
        return state;
    }

    public void setState(BigDecimal state) {
        this.state = state;
    }
}
