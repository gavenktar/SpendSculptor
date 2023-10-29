package by.kirylarol.spendsculptor.repos;

import by.kirylarol.spendsculptor.entities.AccountUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Integer> {
}
