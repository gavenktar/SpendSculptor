package by.kirylarol.spendsculptor.repos;

import by.kirylarol.spendsculptor.entities.Receipt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;


@Repository
public interface ReceiptRepository extends JpaRepository<Receipt, Integer> {
    List<Receipt> findAllByAccountId(int id);

    List<Receipt> findAllByDateBetween(Date date1, Date date2);
}
