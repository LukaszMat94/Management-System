package org.matusikl.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import org.matusikl.encryptionaes256.EncryptionPassword;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "MS_Laptop")
public class Laptop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLaptop")
    private Integer idLaptop;
    @Size(min = 4, max = 20, message = "{laptop.namelaptop.size}")
    @Column(name = "nameLaptop")
    private String nameLaptop;
    @NotEmpty(message = "{laptop.brandLaptop.empty}")
    @Column(name = "brandLaptop")
    private String brandLaptop;
    @Column(name = "loginLaptop")
    @Size(min = 8, message = "{laptop.loginLaptop.size}")
    private String loginLaptop;
    @Column(name = "passwordLaptop")
    @Size(min = 8, message = "{laptop.passwordLaptop.size}")
    private String passwordLaptop;
    @OneToOne(mappedBy = "laptopEmployee")
    @JsonBackReference
    private Employee employee;

    public Laptop(){
    }

    public Laptop(Integer idLaptop, String nameLaptop, String brandLaptop, String loginLaptop, String passwordLaptop) {
        this.idLaptop = idLaptop;
        this.nameLaptop = nameLaptop;
        this.brandLaptop = brandLaptop;
        this.loginLaptop = loginLaptop;
        this.passwordLaptop = passwordLaptop;
    }

    //region Getters-Setters
    public Integer getIdLaptop() {
        return idLaptop;
    }

    public void setIdLaptop(Integer idLaptop) {
        this.idLaptop = idLaptop;
    }

    public String getNameLaptop() {
        return nameLaptop;
    }

    public void setNameLaptop(String nameLaptop) {
        this.nameLaptop = nameLaptop;
    }

    public String getBrandLaptop() {
        return brandLaptop;
    }

    public void setBrandLaptop(String brandLaptop) {
        this.brandLaptop = brandLaptop;
    }

    public String getLoginLaptop() {
        return loginLaptop;
    }

    public void setLoginLaptop(String loginLaptop) {
        this.loginLaptop = loginLaptop;
    }

    public String getPasswordLaptop() throws Exception {
        EncryptionPassword encryptionPassword = new EncryptionPassword();
        String decryptedPassword = encryptionPassword.decrypt(passwordLaptop);
        return decryptedPassword;

    }

    public void setPasswordLaptop(String passwordLaptop) throws Exception {
        EncryptionPassword encryptionPassword = new EncryptionPassword();
        String encryptedPassword = encryptionPassword.encrypt(passwordLaptop);
        this.passwordLaptop = encryptedPassword;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    //endregion

    //region toString
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Laptop{");
        sb.append("idLaptop=").append(idLaptop);
        sb.append(", nameLaptop='").append(nameLaptop).append('\'');
        sb.append(", brandLaptop='").append(brandLaptop).append('\'');
        sb.append(", loginLaptop='").append(loginLaptop).append('\'');
        sb.append(", passwordLaptop='").append(passwordLaptop).append('\'');
        sb.append('}');
        return sb.toString();
    }
    //endregion
}
