package by.kirylarol.spendsculptor.entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
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

    public int getReceiptId() {
        return receiptId;
    }

    public Date getDate() {
        return date;
    }

    public Account getAccount() {
        return account;
    }

    public Shop getShop() {
        return shop;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }


    public void setDate(Date date) {
        this.date = date;
    }


    public void setAccount(Account account) {
        this.account = account;
    }


    public void setShop(Shop shop) {
        this.shop = shop;
    }


    public void setTotal(BigDecimal total) {
        this.total = total;
    }


    public void setPositionList(@NotNull List<Position> positionList) {
        this.positionList = positionList;
    }
}
