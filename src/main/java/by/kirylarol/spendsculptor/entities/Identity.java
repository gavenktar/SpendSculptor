package by.kirylarol.spendsculptor.entities;


import jakarta.persistence.*;

@Entity
@Table (name = "identity")
public class Identity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "identity_id")
    private int id;

    @Column
    private String surname;

    @Column
    private String name;

    @OneToOne (mappedBy = "identity")
    private User user;

    public int id() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String surname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
