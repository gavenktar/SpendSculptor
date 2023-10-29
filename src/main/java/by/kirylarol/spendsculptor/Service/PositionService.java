package by.kirylarol.spendsculptor.Service;

import by.kirylarol.spendsculptor.entities.Category;
import by.kirylarol.spendsculptor.entities.Position;
import by.kirylarol.spendsculptor.entities.Receipt;
import by.kirylarol.spendsculptor.repos.PositionRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional (readOnly = true)
public class PositionService {
    PositionRepository positionRepository;


    @Transactional
    Position addPosition (Position position){
        return positionRepository.save(position);
    }

    @Transactional
    Position addPosition(Receipt receipt, String name, BigDecimal price){
        Position position = new Position();
        position.setName(name);
        position.setPrice(price);
        position.setReceipt(receipt);
        return addPosition(position);
    }

    @Transactional
    Position getById(int id){
        Optional<Position> positionOptional =  positionRepository.findById(id);
        return positionOptional.orElse(null);
    }

    @Transactional
    public List<Position> getByReceiptId(Receipt id){
        return positionRepository.getAllByReceipt(id);
    }

    @Transactional
    public List<Position> getAllByCategory(Category category){
        return positionRepository.getAllByCategory(category);
    }

}
