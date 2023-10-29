package by.kirylarol.spendsculptor.Service;

import by.kirylarol.spendsculptor.entities.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import static java.lang.Math.abs;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountUserServiceTest {
    @Autowired
    AccountUserService accountUserService;
    @Autowired
    UserService userService;
    static User user1;
    static User user2;
    static AccountUser accountUser;
    @BeforeClass
    public static void init (){
        user1 = new User();
        Identity identity = new Identity();
        identity.setName("Alex");
        identity.setSurname("Ivanov");
        user1.setIdentity(identity);
        user1.setLogin("login");
        user1.setPassword("password");

        user2 = new User();
        Identity identity1 = new Identity();
        identity1.setName("Alexa");
        identity1.setSurname("Ivanova");
        user2.setIdentity(identity1);
        user2.setLogin("login1");
        user2.setPassword("password1");
    }

    @Test
    @Rollback(value = true)
    public void addAccountTest(){
        user1 = userService.addUser(user1);
        user2 = userService.addUser(user2);

        accountUser = accountUserService.addAccount("Schet odin", user1);
        assert (accountUserService.getUsersByAccount(accountUser.account()).size() == 1);
        assert (accountUser.weight() == 1);
    }

    @Test
    @Rollback (value = false)
    public void addUserTest(){
        addAccountTest();
        accountUserService.addUser(accountUser.account(), user2,0.8, Account_enum.ACCOUNT_USER);
        accountUser = accountUserService.getByUserAndAccount(accountUser.account(), user1);
        assert (accountUserService.getUsersByAccount(accountUser.account()).size() == 2);
        assert (abs(accountUser.weight() - 0.2) < 0.01);
    }
}
