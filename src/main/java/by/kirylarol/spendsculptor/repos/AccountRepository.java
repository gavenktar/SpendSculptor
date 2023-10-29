package by.kirylarol.spendsculptor.repos;


import by.kirylarol.spendsculptor.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
}
