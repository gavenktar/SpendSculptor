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

    @ManyToOne
    @JoinColumn (name = "account_id")
    private Account account;
    @ManyToOne
    @JoinColumn (name = "shop_id")
    private Shop shop;

    @Column (name = "total_amount")
    private BigDecimal total;


    @OneToMany(mappedBy = "receipt" , fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
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

    public Account account() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Shop shop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public BigDecimal total() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @NotNull
    public List<Position> positionList() {
        return positionList;
    }

    public void setPositionList(@NotNull List<Position> positionList) {
        this.positionList = positionList;
    }
}
