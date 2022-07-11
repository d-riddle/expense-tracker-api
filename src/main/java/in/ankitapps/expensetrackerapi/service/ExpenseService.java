package in.ankitapps.expensetrackerapi.service;

import in.ankitapps.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface ExpenseService {

    Page<Expense> getAllExpenses(Pageable page);

    Expense getExpenseById(Long id);

    void deleteExpenseById(Long id);

    Expense saveExpenseDetails(Expense expense);

    Expense updateExpenseDetails(Expense expense,Long id);

    List<Expense> readByCategory(String category,Pageable page);

    List<Expense> readByName(String keyword,Pageable page);

    List<Expense> readByDate(Date startDate, Date endDate, Pageable page);
}
