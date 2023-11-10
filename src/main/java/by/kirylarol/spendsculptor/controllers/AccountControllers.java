package by.kirylarol.spendsculptor.controllers;


import by.kirylarol.spendsculptor.Service.*;
import by.kirylarol.spendsculptor.dto.AccountDTO;
import by.kirylarol.spendsculptor.dto.FullAccountDTO;
import by.kirylarol.spendsculptor.dto.ReceiptDTO;
import by.kirylarol.spendsculptor.dto.PartAccountDTO;
import by.kirylarol.spendsculptor.entities.*;
import by.kirylarol.spendsculptor.security.Util;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class AccountControllers {

    AccountUserService accountUserService;
    ReceiptService receiptService;
    ShopService shopService;

    CategoryService categoryService;

    Util util;


    AccountService accountService;

    GoalService goalService;

    @Autowired
    public AccountControllers(AccountUserService accountUserService, ReceiptService receiptService, ShopService shopService, CategoryService categoryService, Util util, AccountService accountService, GoalService goalService) {
        this.accountUserService = accountUserService;
        this.receiptService = receiptService;
        this.shopService = shopService;
        this.categoryService = categoryService;
        this.util = util;
        this.accountService = accountService;
        this.goalService = goalService;
    }

    @DeleteMapping("account/delete/{id}")
    ResponseEntity<?> leaveUser(@PathVariable int id) throws Exception {
        User user = util.getUser();
        accountUserService.removeUser(id, user);
        return ResponseEntity.noContent().build();
    }


    @GetMapping("/accounts")
    public List<Account> getAllAccounts() throws Exception {
        User user = util.getUser();
        if (user == null) {
            throw new Exception("Please login before");
        }
        List<Account> accountList = accountUserService.getAccountByUser(user);
        return accountList;
    }

    @PostMapping("accounts/new")
    public Map<String, String> createNewAccount(@RequestBody @Valid AccountDTO accountDTO, BindingResult bindingResult) throws Exception {

        if (bindingResult.hasErrors()) {
            return Map.of("message", bindingResult.getAllErrors()
                    .stream()
                    .map(ObjectError::getDefaultMessage)
                    .collect(Collectors.joining(", ")));
        }

        Account account = toAccount(accountDTO);
        User user = util.getUser();
        accountUserService.addAccount(account, user);
        return Map.of("message", "Аккаунт " + account.getName() + " успешно добавлен");
    }

    @GetMapping("account/{id}")
    public FullAccountDTO accountPage(@PathVariable int id) throws Exception {
        User user = util.getUser();
        if (!accountUserService.accessForUser(id, user.getId()))
            throw new BadCredentialsException("Нет доступа к этому счету");
        AccountUser current = accountUserService.getByUserAndAccount(id, user.getId());
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


    @GetMapping("account/{id}/receipts")
    public PartAccountDTO accountReceipts(@PathVariable int id) throws Exception {
        User user = util.getUser();
        if (!accountUserService.accessForUser(id, user.getId()))
            throw new BadCredentialsException("Нет доступа к этому счету");
        AccountUser current = accountUserService.getByUserAndAccount(id, user.getId());
        PartAccountDTO receiptsDTO = new PartAccountDTO();
        receiptsDTO.setAccountUser(current);
        receiptsDTO.setReceiptList(current.getReceiptList());
        return receiptsDTO;
    }

    @PostMapping("account/{id}/receipt/new")
    public void addReceipt(@PathVariable int id, @RequestBody ReceiptDTO receiptDTO) throws Exception {
        User user = util.getUser();
        AccountUser accountUser = accountUserService.getByUserAndAccount(id, user.getId());
        Receipt newReceipt = new Receipt();
        util.toReceipt(newReceipt, receiptDTO);
        newReceipt.setAccount(accountUser);
        receiptService.addReceipt(newReceipt);

    }


    Account toAccount(AccountDTO accountDTO) {
        return new Account(accountDTO.getName(), accountDTO.getDate());
    }

    @ExceptionHandler
    public ResponseEntity<Map<String, String>> handeExc(Exception exception) {
        return ResponseEntity.badRequest().body(Map.of("message", exception.getMessage()));
    }

}
