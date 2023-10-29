package by.kirylarol.spendsculptor;

import by.kirylarol.spendsculptor.Service.ReceiptService;
import by.kirylarol.spendsculptor.entities.*;
import by.kirylarol.spendsculptor.repos.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpendSculptorApplication {


    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext =  SpringApplication.run(SpendSculptorApplication.class,args);
        File image = new File("C:\\Users\\kirlarla\\Desktop\\STRWEBPR\\New folder\\SpendSculptor\\src\\main\\resources\\image.jpg");
       // var serv = applicationContext.getBean(ReceiptService.class);
      //  serv.addReceipt(image, new Receipt());
        /*  Receipt receipt = new Receipt();
        receipt.setDate(new Date(2023,10,27));
        receipt.setAccountId(1);
        receipt.setShopId(2);
        Position position = new Position();
        position.setName("Апчихба");
        position.setPrice(BigDecimal.valueOf(2.24));
        Position position1 = new Position();
        position.setName("Абуба");
        position.setPrice(BigDecimal.valueOf(3.6));
        List<Position> list = new ArrayList<>();
        list.add(position1);
        list.add(position);
        receipt.setPositionList(list);
        Category category = new Category();
        category.setCategoryName("Залупа");
        position.setCategory(category);
        var service = applicationContext.getBean(ReceiptService.class);
        service.addReceipt(receipt);
         */

        Identity identity = new Identity();
        identity.setName("Кирилл");
        identity.setSurname("Орел");

        User user = new User();
        user.setIdentity(identity);
        user.setLogin("Kiryxa");
        user.setPassword("Arol");

        UserRepository userRepository = applicationContext.getBean(UserRepository.class);

        userRepository.save(user);

        Account account = new Account();

        AccountUser accountUser = new AccountUser();
        accountUser.setUser(user);
        accountUser.setAccount(account);
        accountUser.setPermission(Account_enum.ACCOUNT_CREATOR);
        accountUser.setWeight(1);
        applicationContext.getBean(AccountRepository.class).save(account);
        applicationContext.getBean(AccountUserRepository.class).save(accountUser);

        Shop shop = new Shop();
        shop.setName("Хуета");

        applicationContext.getBean(ShopRepository.class).save(shop);

        Receipt receipt = new Receipt();
        receipt.setDate(new Date(2023,10,27));
        receipt.setAccount(account);
        receipt.setShop(shop);
        Position position = new Position();
        position.setName("Апчихба");
        position.setPrice(BigDecimal.valueOf(2.24));
        Position position1 = new Position();
        position1.setName("Абуба");
        position1.setPrice(BigDecimal.valueOf(3.6));
        List<Position> list = new ArrayList<>();
        list.add(position1);
        list.add(position);
        receipt.setPositionList(list);
        Category category = applicationContext.getBean(CategoryRepository.class).findDistinctFirstByCategoryName("Залупа");

        category.setCategoryName("Залупа");
        position.setCategory(category);

         applicationContext.getBean(ReceiptService.class).addReceipt(receipt);
        // applicationContext.getBean(ReceiptService.class).delete(receipt.receiptId());

    }



}
