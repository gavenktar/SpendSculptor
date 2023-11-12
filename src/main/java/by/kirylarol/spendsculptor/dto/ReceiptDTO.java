package by.kirylarol.spendsculptor.dto;

import by.kirylarol.spendsculptor.entities.Position;
import by.kirylarol.spendsculptor.entities.Shop;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class ReceiptDTO {
    @JsonFormat(pattern = "dd.MM.yyyy")
    private LocalDate date;
    private List<Position> positionList;

    private Shop shop;

    private BigDecimal total;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Shop getShop() {
        return shop;
    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public List<Position> getPositionList() {
        return positionList;
    }

    public void setPositionList(List<Position> positionList) {
        this.positionList = positionList;
    }
}
