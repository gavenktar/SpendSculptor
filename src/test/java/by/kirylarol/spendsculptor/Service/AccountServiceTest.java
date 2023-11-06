package by.kirylarol.spendsculptor.Service;


import by.kirylarol.spendsculptor.entities.Account;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(
        locations = "classpath:application-integrationtest.properties")
public class AccountServiceTest {
    @Autowired
    private AccountService accountService;

    @Test
    public void addAccountTest(){
        Account account = new Account();
        account.setName("Alexander");
        account = accountService.addAccount(account);
        assert(account.getId() != 0);
    }

    @Test
    public void removeAccountTest(){
        Account account = new Account();
        addAccountTest();
        accountService.removeAccount(account);
        assert (accountService.getAccount(account) == null);

    }
}
