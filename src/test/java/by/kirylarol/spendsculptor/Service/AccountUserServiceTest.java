package by.kirylarol.spendsculptor.Service;

import by.kirylarol.spendsculptor.entities.*;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;

import java.util.List;

import static java.lang.Math.abs;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
@Transactional
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
        assert (accountUserService.getUsersByAccount(accountUser.getAccount()).size() == 1);
        assert (accountUser.getWeight() == 1);
    }

    @Test
    @Rollback (value = false)
    public void addUserTest(){
        addAccountTest();
        accountUserService.addUser(accountUser.getAccount(), user2,0.8, ACCOUNT_ENUM.ACCOUNT_USER);
        accountUser = accountUserService.getByUserAndAccount(accountUser.getAccount(), user1);
        assert (accountUserService.getUsersByAccount(accountUser.getAccount()).size() == 2);
        assert (abs(accountUser.getWeight() - 0.2) < 0.01);
    }


    @Test
    @Rollback (value = true)
    public void deleteUser(){
        addUserTest();
        user1 = accountUserService.getByUserAndAccount(accountUser.getAccount(), user1).getUser();
        accountUserService.removeUser(accountUser.getAccount(), user1);
        accountUser = accountUserService.getByUserAndAccount(accountUser.getAccount(),user2);
        assert ((abs(accountUser.getWeight()) - 1) < 0.01);
        List<AccountUser> userList = accountUserService.getUsersByAccount(accountUser.getAccount());
        Assert.assertEquals(userList.size(),1);
    }

}
