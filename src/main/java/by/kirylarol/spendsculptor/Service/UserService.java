package by.kirylarol.spendsculptor.Service;

import by.kirylarol.spendsculptor.entities.User;
import by.kirylarol.spendsculptor.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    public User addUser(User user){
        return userRepository.save(user);
    }
}