package by.kirylarol.spendsculptor.repos;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.math.BigDecimal;

@Entity
@Table (name = "positions")
public class Position {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column (name = "position_id")
    private int positionId;

    @Column
    private String name;

    @Column
    private BigDecimal price;

    @ManyToOne(optional = true)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(optional = true)
    @JoinColumn (name = "receipt_id")
    private Receipt receipt;

    public Position(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Position{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    public Position() {
    }

    public Position(BigDecimal price) {
        this.price = price;
    }

    public String name() {
        return name;
    }

    public void setName(String name) {
        name = name.replaceAll(" [\"0-9].+","");
        this.name = name;
    }

    public BigDecimal price() {
        return price;
    }

    public int positionId() {
        return positionId;
    }

    public void setPositionId(int positionId) {
        this.positionId = positionId;
    }

    public Category category() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Receipt receipt() {
        return receipt;
    }

    public void setReceipt(Receipt receipt) {
        this.receipt = receipt;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
