package in.ankitapps.expensetrackerapi.controller;


import in.ankitapps.expensetrackerapi.entity.Expense;
import in.ankitapps.expensetrackerapi.service.ExpenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

@RestController
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    @RequestMapping(method = RequestMethod.GET,value = "/expenses")
    public List<Expense> getAllExpenses(Pageable page){
        return (expenseService.getAllExpenses(page).stream().toList());
    }

    @RequestMapping(method = RequestMethod.GET,value="/expenses/{id}")
    public Expense getExpenseById(@PathVariable Long id){
        return (expenseService.getExpenseById(id));
    }

    @ResponseStatus(value=HttpStatus.NO_CONTENT)
    @RequestMapping(method = RequestMethod.DELETE,value = "/expenses")
    public void deleteExpenseById(@RequestParam Long id){
        expenseService.deleteExpenseById(id);
    }

    @ResponseStatus(value = HttpStatus.CREATED)
    @RequestMapping(method=RequestMethod.POST,value="/expenses")
    public Expense saveExpenseDetails(@Valid @RequestBody Expense expense){
        return (expenseService.saveExpenseDetails(expense));
    }

    @RequestMapping(method =RequestMethod.PUT,value = "/expenses/{id}")
    public Expense updateExpenseDetails(@RequestBody Expense expense,@PathVariable Long id){
        return (expenseService.updateExpenseDetails(expense,id));
    }

    @RequestMapping(method = RequestMethod.GET,value="/expenses/category")
    public List<Expense> getExpensesByCategory(@RequestParam String category,Pageable page){
        return (expenseService.readByCategory(category,page));
    }

    @RequestMapping(method=RequestMethod.GET,value="/expenses/name")
    public List<Expense> readByName(@RequestParam String keyword,Pageable page){
        return (expenseService.readByName(keyword,page));
    }

    @RequestMapping(method=RequestMethod.GET,value="/expenses/date")
    public List<Expense> readByDate(@RequestParam(required=false) Date startDate, @RequestParam(required = false) Date endDate,Pageable page){
        return (expenseService.readByDate(startDate,endDate,page));
    }
}
