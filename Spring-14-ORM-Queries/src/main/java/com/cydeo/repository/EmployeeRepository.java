package com.cydeo.repository;

import com.cydeo.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {

    //Displayu all employees with email adress
    List<Employee> findByEmail(String email);


    //Displayu all employees with firstName '' and last name '' also show all employees with an email address
    List<Employee> findByFirstNameAndLastNameOrEmail(String firstName,String LastName,String email);

    //Display all employees that first name is not ''
    List<Employee> findByFirstNameIsNot(String firstName);

    //Display all employees where last name starts with ''
    List<Employee> findByLastNameStartsWith(String pattern);

    //Display all employees with salaries higher than ''
    List<Employee> findBySalaryGreaterThan(Integer salary);

    //Display all employees with salaries less than  ''
    List<Employee>findBySalaryLessThanEqual(Integer salary);

    //Display all employees that has been hired between '' and ''
    List<Employee> findByHireDateBetween(LocalDate startDate,LocalDate endDate);

    //Display all employees where salaries greater and equal to '' in order.
    List<Employee> findBySalaryIsGreaterThanEqualOrderBySalaryDesc(Integer salary);

    //Display top unique 3 employees that is making less than ''
    List<Employee>findDistinctTop3BySalaryLessThan(Integer salary);

    //Display all employees that do not have email address
    List<Employee> findByEmailIsNull();

    @Query("select e FROM Employee e where e.email = 'lcasarolib@plala.or.jp'")
    Employee getEmployeeDetail();

    @Query("Select e.salary from Employee e where e.email = 'lcasarolib@plala.or.jp' ")
    Integer getEmployeeSalary();

    @Query("Select e from Employee e where e.email=?1")
    Optional<Employee> getEmployeeDetail(String email);

    @Query("Select e From Employee e where e.email=?1 AND e.salary=?2")
    Optional<Employee>  getEmployeeDetail(String email,int salary);

    //NOT equal with JPQL with className
    @Query("select e FROM Employee e where e.salary<> ?1") //positional parameter
    List<Employee> getEmployeeSalaryNotEqual(int salary);

    //like/contains/starswith/endwith
    @Query("select e from Employee e where e.firstName LIKE ?1")
    List<Employee>getEmployeeFirstNameLike(String pattern);

    //less than
    @Query("SELECT e FROM e Employee e where e.salary > ?1")
    List<Employee> getEmployeeSalaryLessThan(int salary);

    //Before
    @Query("Select e from Employee e where e.hireDate >?1")
    List<Employee> getEmployeeHireDateBefore(LocalDate date);

    //Between
    @Query("Select e From Employee e where e.salary BETWEEN ?1 AND ?2")
    List<Employee> getEmployeeSalaryBetween(int salary1,int salary2);

    //Null
    @Query("select e from Employee  e where e.email is not null")
    List<Employee> getEmployeeEmailIsNull();

    //Sorting in ascending order
    @Query("select e from Employee e ORDER BY e.salary desc")
    List<Employee> getEmployeeSalaryOrderAsc();

    @Query(value = "Select * From employees where salary ?1",nativeQuery = true) //native query with pure sql
    List<Employee> readEmployeeDetailBySalary(int salary);

    @Query("select e from Employee e where e.salary = :salary")
    List<Employee> getEmployeeSalary(@Param("salary")int salary);

    @Modifying
    @Transactional
    @Query("UPDATE Employee e SET e.email = 'admin@email.com' where e.id=:id")
    void updateEmployeeJPQL(@Param("id") int id);


}
