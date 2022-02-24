package org.matusikl.repository;

import org.matusikl.model.Account;
import org.matusikl.model.Employee;
import org.matusikl.model.Laptop;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.EntityGraph.EntityGraphType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    boolean existsByPersonalIdentityNumberEmployee(String idPersonalNumber);

    boolean existsByEmailEmployee(String email);

    boolean existsByPersonalIdentityNumberEmployeeAndIdEmployeeNot(String idPersonalNumber, Integer idEmployee);

    boolean existsByEmailEmployeeAndIdEmployeeNot(String email, Integer idEmployee);

    boolean existsByAccountEmployee(Account account);

    boolean existsByLaptopEmployee(Laptop laptop);

    @Override
    @EntityGraph(value = "Employee.task", type = EntityGraphType.LOAD)
    List<Employee> findAll();

    @Override
    @EntityGraph(value = "Employee.task", type = EntityGraphType.LOAD)
    Optional<Employee> findById(Integer id);
}
