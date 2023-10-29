package by.kirylarol.spendsculptor.Service;

import by.kirylarol.spendsculptor.entities.Identity;
import by.kirylarol.spendsculptor.entities.RolesSystem;
import by.kirylarol.spendsculptor.entities.User;
import by.kirylarol.spendsculptor.repos.UserRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional (readOnly = true)
public class UserService {
    private UserRepository userRepository;



    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public User addUser(User user){
        return userRepository.save(user);
    }


    @Transactional
    public  User addUser (String name, String surname, String login, String password, RolesSystem rolesSystem){
        Identity identity = new Identity();
        identity.setSurname(surname);
        identity.setName(name);
        User user = new User();
        user.setLogin(login);
        user.setPassword(password);
        user.setIdentity(identity);
        user.setRole(rolesSystem);
        return addUser(user);
    }

    public User getUser(User user){
        return userRepository.findById(user.id()).orElse(null);
    }
    @Transactional
    public void deleteUser (User user){
        userRepository.delete(user);
    }

}
