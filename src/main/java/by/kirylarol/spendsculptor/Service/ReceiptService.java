package by.kirylarol.spendsculptor.Service;


import by.kirylarol.spendsculptor.model.ApiSender;
import by.kirylarol.spendsculptor.model.JsonStringIntoInternalParser;
import by.kirylarol.spendsculptor.entities.Position;
import by.kirylarol.spendsculptor.entities.Receipt;
import by.kirylarol.spendsculptor.repos.ReceiptRepository;
import by.kirylarol.spendsculptor.utils.Hotkeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
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
    ReceiptRepository receiptRepository;

    @Autowired
    ReceiptService(ApiSender apiSender, JsonStringIntoInternalParser jsonStringIntoInternalParser, ReceiptRepository receiptRepository) {
        this.apiSender = apiSender;
        this.jsonStringIntoInternalParser = jsonStringIntoInternalParser;
        this.receiptRepository = receiptRepository;
    }

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
        this.save(receipt);
        return receipt;
    }

    ;


    @Transactional
    public void save(Receipt receipt) {
        BigDecimal currentTotal = BigDecimal.valueOf(0);
        for (var elem : receipt.positionList()) {
            elem.setReceipt(receipt);
            currentTotal = currentTotal.add(elem.price());
        }
        if (receipt.total().equals(BigDecimal.valueOf(0))) {
            receipt.setTotal(currentTotal);
        }
        receiptRepository.save(receipt);
    }

    public Receipt addReceipt(Receipt receipt) throws Exception {
        this.save(receipt);
        return receipt;
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
        receiptRepository.save(receipt);
        return receipt;
    }

    @Transactional
    public void delete (int id){
        receiptRepository.deleteById(id);
    }


}
