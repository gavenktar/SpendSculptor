package by.kirylarol.spendsculptor.repos;

import by.kirylarol.spendsculptor.entities.Account;
import by.kirylarol.spendsculptor.entities.AccountUser;
import by.kirylarol.spendsculptor.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Repository
public interface AccountUserRepository extends JpaRepository<AccountUser, Integer> {
    List<AccountUser> findAccountUsersByAccount(Account account);


    @Query("SELECT ua.user FROM AccountUser  ua WHERE ua.account = :account")
    List<User> findUserByAccount(@Param("account")Account account);

    AccountUser findAccountUserByAccountAndUser(Account accountid, User userid);
}
