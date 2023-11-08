package by.kirylarol.spendsculptor.controllers;


import by.kirylarol.spendsculptor.Service.AccountService;
import by.kirylarol.spendsculptor.Service.AccountUserService;
import by.kirylarol.spendsculptor.Service.GoalService;
import by.kirylarol.spendsculptor.dto.AccountDTO;
import by.kirylarol.spendsculptor.dto.FullAccountDTO;
import by.kirylarol.spendsculptor.dto.ReceiptsDTO;
import by.kirylarol.spendsculptor.entities.*;
import by.kirylarol.spendsculptor.security.UserCredentials;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/accounts")
public class AccountControllers {

    AccountUserService accountUserService;

    UserDetailsService userDetailsService;

    AccountService accountService;

    GoalService goalService;

    @Autowired
    public AccountControllers(AccountUserService accountUserService, UserDetailsService userDetailsService) {
        this.accountUserService = accountUserService;
        this.userDetailsService = userDetailsService;
    }


    User getUser() throws Exception {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null) {
            return null;
        }
        String login;
        try {
            login = (String) auth.getPrincipal();

        } catch (Exception err) {
            throw new BadCredentialsException("Incorrect login");
        }

        return ((UserCredentials)userDetailsService.loadUserByUsername(login)).user();
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<?> leaveUser(@PathVariable int id) throws Exception {
            User user = getUser();
            accountUserService.removeUser(id,user);
            return ResponseEntity.noContent().build();
    }


    @GetMapping
    @RequestMapping(produces = "application/json")
    public List<Account> getAllAccounts() throws Exception {
        User user = getUser();
        if (user == null) {
             throw new Exception("Please login before");
        }
        List<Account> accountList = accountUserService.getAccountByUser(user);
        return accountList;
    }

    @PostMapping ("/new")
    public Map<String,String> createNewAccount (@RequestBody @Valid AccountDTO accountDTO, BindingResult bindingResult) throws Exception{

        if (bindingResult.hasErrors()){
           return Map.of("message",bindingResult.getAllErrors()
                        .stream()
                        .map(ObjectError::getDefaultMessage)
                        .collect(Collectors.joining(", ")));
        }

        Account account = toAccount(accountDTO);
        User user = getUser();
        accountUserService.addAccount(account,user);
        return Map.of("message","Аккаунт " + account.getName() + " успешно добавлен");
    }

    @GetMapping ("/{id}")
    public FullAccountDTO accountPage(@PathVariable int id) throws Exception {
        User user = getUser();
        if (!accountUserService.accessForUser(id, user.getId())) throw new BadCredentialsException("Нет доступа к этому счету");
        AccountUser current = accountUserService.getByUserAndAccount(id,user.getId());
        Account account = current.getAccount();
        FullAccountDTO fullAccountDTO = new FullAccountDTO();
        fullAccountDTO.setUserRole(current.getPermission());
        fullAccountDTO.setGoalList(account.getGoalList());
        fullAccountDTO.setReceiptList(current.getReceiptList());
        fullAccountDTO.setUserList(accountUserService.getUsersByAccount(current.getAccount()));
        fullAccountDTO.setAccount(current.getAccount());
        fullAccountDTO.setWeight(current.getWeight());
        return fullAccountDTO;
    }


    @GetMapping ("/{id}/receipts")
    public ReceiptsDTO accountReceipts(@PathVariable int id) throws Exception{
        User user = getUser();
        if (!accountUserService.accessForUser(id, user.getId())) throw new BadCredentialsException("Нет доступа к этому счету");
        AccountUser current = accountUserService.getByUserAndAccount(id,user.getId());
        ReceiptsDTO receiptsDTO = new ReceiptsDTO();
        receiptsDTO.setAccountUser(current);
        receiptsDTO.setReceiptList(current.getReceiptList());
        return receiptsDTO;
    }

    Account toAccount (AccountDTO accountDTO){
        return new Account(accountDTO.getName(), accountDTO.getDate());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String,String>> handeExc (Exception exception){
        return ResponseEntity.badRequest().body(Map.of("message",exception.getMessage()));
    }

}
