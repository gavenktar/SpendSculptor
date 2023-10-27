package by.kirylarol.spendsculptor.entities;


import jakarta.persistence.*;
import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.sql.Date;
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


    @Column (name = "total_amount")
    private BigDecimal total;


    @NotNull
    @OneToMany(mappedBy = "receipt" , fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REFRESH, CascadeType.MERGE})
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

    public BigDecimal total() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
