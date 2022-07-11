package in.ankitapps.expensetrackerapi.service;

import in.ankitapps.expensetrackerapi.entity.Expense;
import in.ankitapps.expensetrackerapi.exceptions.ResourceNotFoundException;
import in.ankitapps.expensetrackerapi.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ExpenseServiceImpl implements ExpenseService{
    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;
    public Page<Expense> getAllExpenses(Pageable page){

        return expenseRepository.findByUserId(userService.getLoggedInUser().getId(),page);
    }

    public Expense getExpenseById(Long id){
        Optional<Expense> expenseOptional=expenseRepository.findByUserIdAndId(userService.getLoggedInUser().getId(), id);
        if(expenseOptional.isPresent()){
            return (expenseOptional.get());
        }
        throw new ResourceNotFoundException("Expense is not found for the id "+id);
    }

    public void deleteExpenseById(Long id){

        Expense expense=getExpenseById(id);
        expenseRepository.delete(expense);
    }

    public Expense saveExpenseDetails(Expense expense){

        expense.setUser(userService.getLoggedInUser());
        Expense savedExpense=expenseRepository.save(expense);
        return savedExpense;
    }

    public Expense updateExpenseDetails(Expense expense,Long id){
        Expense existingExpense=getExpenseById(id);
        existingExpense.setName((expense.getName()!=null)?expense.getName():existingExpense.getName());
        existingExpense.setDescription((expense.getDescription()!=null)?expense.getDescription(): existingExpense.getDescription());
        existingExpense.setAmount((expense.getAmount()!=null)?expense.getAmount():existingExpense.getAmount());
        existingExpense.setCategory((expense.getCategory()!=null)?expense.getCategory():existingExpense.getCategory());
        existingExpense.setDate((expense.getDate()!=null)?expense.getDate():existingExpense.getDate());
        return (expenseRepository.save(existingExpense));
    }

    public List<Expense> readByCategory(String category, Pageable page){
        return (expenseRepository.findByUserIdAndCategory(userService.getLoggedInUser().getId(),category,page).toList());
    }

    public List<Expense> readByName(String keyword,Pageable page){
        return (expenseRepository.findByUserIdAndNameContaining(userService.getLoggedInUser().getId(), keyword,page).toList());
    }

    public List<Expense> readByDate(Date startDate,Date endDate,Pageable page){
        if(startDate==null){
            startDate=new Date(0);
        }
        if(endDate==null){
            endDate=new Date(System.currentTimeMillis());
        }
        Page<Expense> pages=expenseRepository.findByDate(startDate,endDate,page);
        return pages.toList();
    }


}
