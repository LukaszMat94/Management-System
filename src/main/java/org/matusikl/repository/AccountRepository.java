package org.matusikl.repository;

import org.matusikl.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    Optional<Account> findByLogin(String login);
    Optional<Account> findByLoginAndIdAccountNot(String login, Integer id);
}
