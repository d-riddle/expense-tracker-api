package in.ankitapps.expensetrackerapi.repository;

import in.ankitapps.expensetrackerapi.entity.Expense;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.Optional;


@Repository
public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    //Select * from tbl_expenses where category=?
    Page<Expense> findByUserIdAndCategory(Long userId,String category, Pageable Page);

    //Select * from tbl_expenses where name Like '%keyword%'
    Page<Expense> findByUserIdAndNameContaining(Long userId,String keyword,Pageable page);

    //Select * from tbl_expenses where Date Between startDate And endDate
    Page<Expense> findByDate(Date startDate, Date endDate, Pageable Page);

    Page<Expense> findByUserId(Long userId,Pageable page);

    Optional<Expense> findByUserIdAndId(Long userId,Long id);
}
