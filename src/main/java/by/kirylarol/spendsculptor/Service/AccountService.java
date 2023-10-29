package by.kirylarol.spendsculptor.Service;


import by.kirylarol.spendsculptor.entities.Account;
import by.kirylarol.spendsculptor.entities.User;
import by.kirylarol.spendsculptor.repos.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Date;

@Transactional (readOnly = true)
@Service
public class AccountService {
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Transactional
    public Account addAccount (Account account) {
       return accountRepository.save(account);
    }

    @Transactional
    public void removeAccount (Account account){
        accountRepository.delete(account);
    }

    public Account getAccount (Account account){
        return accountRepository.findById(account.id()).orElse(null);
    }

}
