package org.matusikl.model;

import org.matusikl.encryptionaes256.EncryptionPassword;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "MS_Account")
public class Account implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAccount")
    private Integer idAccount;

    @NotNull(message = "{account.login.null}")
    @Pattern(message = "{account.login.pattern}",
            regexp = "[a-zA-Z0-9]{8,}$")
    private String login;

    @Size(min = 8, message = "{account.password.size}")
    private String password;

    public Account() {
    }

    //region Getters-Setters
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() throws Exception {
        EncryptionPassword encryptionPassword = new EncryptionPassword();
        String decryptedPassword = encryptionPassword.decrypt(password);
        return decryptedPassword;
    }

    public void setPassword(String password) throws Exception {
        EncryptionPassword encryptionPassword = new EncryptionPassword();
        String encryptedPassword = encryptionPassword.encrypt(password);
        this.password = encryptedPassword;
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    //endregion
}
