package by.kirylarol.spendsculptor.repos;

import by.kirylarol.spendsculptor.entities.Account;
import by.kirylarol.spendsculptor.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.Date;
import java.util.List;

public interface GoalRepository extends JpaRepository<Goal, Integer> {


    List<Goal> findGoalsByAccountAndValidBefore(Account account, Date date);

    List<Goal> findGoalsByAccount(Account account);
}
