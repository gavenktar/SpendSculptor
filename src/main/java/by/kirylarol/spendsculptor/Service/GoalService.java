package by.kirylarol.spendsculptor.Service;

import by.kirylarol.spendsculptor.entities.Account;
import by.kirylarol.spendsculptor.entities.Goal;
import by.kirylarol.spendsculptor.repos.GoalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
@Transactional (readOnly = true)
public class GoalService {
    private final GoalRepository goalRepository;
    private final ReceiptService receiptService;

    @Autowired
    public GoalService(GoalRepository goalRepository, ReceiptService receiptService) {
        this.goalRepository = goalRepository;
        this.receiptService = receiptService;
    }

    @Transactional
    public Goal createGoal(Account account, String name, LocalDate createDate, LocalDate validDate, BigDecimal target, BigDecimal currstate){
        Goal goal = new Goal();
        goal.setGoal(target);
        goal.setCreated(createDate);
        goal.setValid(validDate);
        goal.setState(currstate);
        goal.setAccount(account);
        return goalRepository.save(goal);
    }

    @Transactional
    public void UpdateGoal(Account account){
        List<Goal> goalList = takeActiveGoals(account);
        for (var elem : goalList){
            elem.setState(receiptService.getAllSpends(account, elem.created(), elem.valid()) );
            goalRepository.save(elem);
        }
    }

    @Transactional
    public List<Goal> takeActiveGoals (Account account){
        LocalDate now = LocalDate.now();
        return goalRepository.findGoalsByAccount_IdAndValidAfterAndCreatedBefore(account.getId(), now,now);
    }

    @Transactional
    public void deleteGoal (Goal goal){
        goalRepository.delete(goal);
    }

    @Transactional
    public List<Goal> takeAllGoals (Account account){
        return goalRepository.findGoalsByAccount_Id(account.getId());
    }

    public  List<Goal> takeGoalValidUntilDate (Account account, LocalDate date){
        System.out.println(date.toString());
        System.out.println(account.getId());
        return goalRepository.findGoalsByAccount_IdAndValidAfterAndCreatedBefore(account.getId(), date,date);
    }

}
