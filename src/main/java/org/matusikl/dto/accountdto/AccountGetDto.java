package org.matusikl.dto.accountdto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class AccountGetDto {

    private Integer idAccount;

    @NotNull(message = "{account.login.null}")
    @Pattern(message = "{account.login.pattern}",
            regexp = "[a-zA-Z0-9]{8,}$")
    private String login;

    public AccountGetDto() {
    }

    public Integer getIdAccount() {
        return idAccount;
    }

    public void setIdAccount(Integer idAccount) {
        this.idAccount = idAccount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

}
