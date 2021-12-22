package org.matusikl.repository;

import org.matusikl.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    Optional<Employee> findByPersonalIdentityNumberEmployee(String idPersonalNumber);

    @Query("select e from Employee e where e.personalIdentityNumberEmployee = ?1 and e.idEmployee <> ?2")
    Optional<Employee> findByPersonalIdentityNumberEmployeWithOtherID(String idPersonalNumber, Integer idEmployee);
}
