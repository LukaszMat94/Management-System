package org.matusikl.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Data
@Table(name = "MS_Laptop")
public class Laptop {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "idLaptop")
    private Integer idLaptop;
    @Column(name = "nameLaptop")
    private String nameLaptop;
    @Column(name = "brandLaptop")
    private String brandLaptop;
    @Column(name = "loginLaptop")
    private String loginLaptop;
    @Column(name = "passwordLaptop")
    private String passwordLaptop;
//    @OneToOne
//    private Employee employee;
    // Zeby tutaj to laczyc to musisz Employee tez zrobic jako Entity w bazie :)


}
