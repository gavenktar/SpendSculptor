package by.kirylarol.spendsculptor.repos;

import by.kirylarol.spendsculptor.entities.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal, Integer> {
}
