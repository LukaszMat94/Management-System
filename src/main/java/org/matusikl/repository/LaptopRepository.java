package org.matusikl.repository;

import org.matusikl.model.Laptop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface LaptopRepository extends JpaRepository<Laptop, Integer> {

    @Query("select l from Laptop l where l.nameLaptop = ?1")
    Optional<Laptop> findLaptopByNameLaptop(String nameLaptop);

    @Query("select l from Laptop l where l.nameLaptop = ?1 and l.idLaptop <> ?2")
    Optional<Laptop> findLaptopByNameLaptopAndOtherId(String nameLaptop, Integer idLaptop);

}
