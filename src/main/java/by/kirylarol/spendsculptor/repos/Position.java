package by.kirylarol.spendsculptor.repos;

import java.math.BigDecimal;

public class Position {
    private String name;
    private BigDecimal price;

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
        this.name = name;
    }

    public BigDecimal price() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
