package org.matusikl.dto.laptopdto;

import org.matusikl.encryptionaes256.EncryptionPassword;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class LaptopPostDto {

    @Size(min = 4, max = 20, message = "{laptop.namelaptop.size}")
    private String nameLaptop;

    @NotEmpty(message = "{laptop.brandLaptop.empty}")
    private String brandLaptop;

    @Pattern(message = "{laptop.login.pattern}",
            regexp = "[a-zA-Z0-9]{8,}$")
    private String loginLaptop;

    @Size(min = 8, message = "{laptop.passwordLaptop.size}")
    private String passwordLaptop;

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

    public String getPasswordLaptop(){
        return passwordLaptop;
    }

    public void setPasswordLaptop(String passwordLaptop) throws Exception {
        EncryptionPassword encryptionPassword = new EncryptionPassword();
        String encryptedPassword = encryptionPassword.encrypt(passwordLaptop);
        this.passwordLaptop = encryptedPassword;
    }
}
