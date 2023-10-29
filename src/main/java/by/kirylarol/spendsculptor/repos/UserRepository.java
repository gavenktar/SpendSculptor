package by.kirylarol.spendsculptor.repos;

import by.kirylarol.spendsculptor.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
