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

}
