package org.matusikl.repository;

import org.matusikl.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByRole(String role);
    @Query(value = "select r.role from dbo.MS_Role r " +
            "inner join dbo.MS_Employee_Role er on r.id = er.id_role " +
            "inner join dbo.MS_Employee e on er.id_emp = e.idEmployee " +
            "inner join dbo.MS_Account a on e.idAccount = a.idAccount " +
            "where a.login = ?1", nativeQuery = true)
    List<String> findRolesByAccountUsername(String username);
}
