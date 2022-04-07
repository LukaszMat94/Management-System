package org.matusikl.model;

import javax.validation.constraints.NotNull;

public class UserCredential {

    @NotNull(message = "{usercredential.login.null}")
    private String username;
    @NotNull(message = "{usercredential.password.null}")
    private String password;

    public UserCredential(String username, String password){
        this.username = username;
        this.password = password;
    }

    public UserCredential() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
