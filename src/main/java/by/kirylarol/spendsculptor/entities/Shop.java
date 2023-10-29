package by.kirylarol.spendsculptor.entities;


import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "shop")
public class Shop {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    @Column (name = "shop_id")
    private int id;

    @Column
    private String name;

    @OneToMany (mappedBy = "shop")
    private List<Receipt> receiptList;


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

    public List<Receipt> receiptList() {
        return receiptList;
    }

    public void setReceiptList(List<Receipt> receiptList) {
        this.receiptList = receiptList;
    }
}
