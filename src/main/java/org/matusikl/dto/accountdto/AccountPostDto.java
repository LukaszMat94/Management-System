package org.matusikl.dto.accountdto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class AccountPostDto {

    @NotNull(message = "{account.login.null}")
    @Pattern(message = "{account.login.pattern}",
            regexp = "[a-zA-Z0-9]{8,}$")
    private String login;

    @Size(min = 8, message = "{account.password.size}")
    private String password;

    public AccountPostDto() {
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
