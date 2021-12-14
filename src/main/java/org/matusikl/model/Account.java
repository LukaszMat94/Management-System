package org.matusikl.model;

public class Account {
    private String login;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    //endregion
}
