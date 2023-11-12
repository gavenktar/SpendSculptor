package by.kirylarol.spendsculptor.security;

import by.kirylarol.spendsculptor.Service.CategoryService;
import by.kirylarol.spendsculptor.Service.PositionService;
import by.kirylarol.spendsculptor.Service.ShopService;
import by.kirylarol.spendsculptor.dto.ReceiptDTO;
import by.kirylarol.spendsculptor.entities.Category;
import by.kirylarol.spendsculptor.entities.Receipt;
import by.kirylarol.spendsculptor.entities.Shop;
import by.kirylarol.spendsculptor.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

@Component
public class Util {

    UserDetailsService userDetailsService;
    ShopService shopService;
    CategoryService categoryService;

    @Autowired
    PositionService positionService;

    @Autowired
    public Util(UserDetailsService userDetailsService, ShopService shopService, CategoryService categoryService) {
        this.userDetailsService = userDetailsService;
        this.shopService = shopService;
        this.categoryService = categoryService;
    }

    public User getUser() throws Exception {
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

        return ((UserCredentials) userDetailsService.loadUserByUsername(login)).user();
    }

    public void toReceipt(Receipt receipt, ReceiptDTO receiptDTO) {
        if (receiptDTO.getDate() != null) {
            receipt.setDate(receiptDTO.getDate());
        }
        if (receiptDTO.getShop() != null) {
            Shop shop = shopService.addShop(receiptDTO.getShop().getName());
            receipt.setShop(shop);
        }
        if (receiptDTO.getTotal() != null) {
            receipt.setTotal(receiptDTO.getTotal());
        }
        if (receiptDTO.getPositionList() != null) {
            receipt.setPositionList(receiptDTO.getPositionList());
        }
    }
}
