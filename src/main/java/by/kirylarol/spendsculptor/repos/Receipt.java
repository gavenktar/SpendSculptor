package by.kirylarol.spendsculptor.repos;


import jakarta.persistence.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Receipts")
public class Receipt {

    @Id
    @Column (name = "receipt_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int receiptId;
    @Column
    private Date date;

    @Column (name = "account_id")
    private int accountId = 0;

    @Column (name = "shop_id")
    private int shopId = 0;

    @OneToMany(mappedBy = "receipt" , fetch = FetchType.EAGER)
    private List<Position> positionList;

    public int receiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }

    public Date date() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int accountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int shopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public List<Position> positionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
}
