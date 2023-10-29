package by.kirylarol.spendsculptor.Service;


import by.kirylarol.spendsculptor.entities.Account;
import by.kirylarol.spendsculptor.Api.ApiSender;
import by.kirylarol.spendsculptor.Api.JsonStringIntoInternalParser;
import by.kirylarol.spendsculptor.entities.Position;
import by.kirylarol.spendsculptor.entities.Receipt;
import by.kirylarol.spendsculptor.repos.ReceiptRepository;
import by.kirylarol.spendsculptor.utils.Hotkeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class ReceiptService {

    private final ApiSender apiSender;
    private final JsonStringIntoInternalParser jsonStringIntoInternalParser;
    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ApiSender apiSender, JsonStringIntoInternalParser jsonStringIntoInternalParser, ReceiptRepository receiptRepository) {
        this.apiSender = apiSender;
        this.jsonStringIntoInternalParser = jsonStringIntoInternalParser;
        this.receiptRepository = receiptRepository;
    }


    @Transactional
    public Receipt addReceipt (File receiptImage, Account User, Date date, BigDecimal price) throws Exception {
        Receipt receipt = new Receipt();
        receipt.setAccount(User);
        receipt.setDate(date);
        receipt.setTotal(price);
        return addReceipt(receiptImage, receipt);
    }

    @Transactional
    public Receipt addReceipt(File receiptImage, Receipt receipt) throws Exception {
        String result = apiSender.send(receiptImage);
        List<Position> positionList = new ArrayList<>(jsonStringIntoInternalParser.firstParseStageAfterHttp(result).parse());
        Optional<Position> position = positionList.stream().filter(elem -> {
            return Objects.equals(elem.name(), Hotkeys.TOTAL.getName());
        }).findFirst();
        if (position.isPresent()) {
            BigDecimal total = position.get().price();
            positionList.remove(position.get());
            receipt.setTotal(total);
        }
        receipt.setPositionList(positionList);

        return  this.save(receipt);
    }

    public Receipt save(Receipt receipt) {
        BigDecimal currentTotal = BigDecimal.valueOf(0);
        for (var elem : receipt.positionList()) {
            elem.setReceipt(receipt);
            currentTotal = currentTotal.add(elem.price());
        }
        if (receipt.total() == null) {
            receipt.setTotal(currentTotal);
        }
        return receiptRepository.save(receipt);
    }

    @Transactional
    public Receipt addReceipt(Receipt receipt) throws Exception {
        return this.save(receipt);
    }

    public Receipt getReceipt(int id){
        Optional<Receipt> result = receiptRepository.findById(id);
        return result.orElse(null);
    }

    public List<Receipt> getAllReceipts(int id){
        return receiptRepository.findAll();
    }

    public List<Receipt> getAllByAccount(int id){
        return  receiptRepository.findAllByAccountId(id);
    }

    public List<Receipt> getAllBetween (Date date1, Date date2){
        return receiptRepository.findAllByDateBetween(date1,date2);
    }

    @Transactional
    public Receipt update (int id, Receipt receipt) {
        receipt.setReceiptId(id);
        return receiptRepository.save(receipt);
    }

    @Transactional
    public void delete (int id){
        receiptRepository.deleteById(id);
    }


    public BigDecimal getAllSpends (Account account, Date start, Date end){
        return receiptRepository.getTotalByAccount(account,start,end);
    }

}
