package org.matusikl.model;

public class Laptop {

    private Integer idLaptop;
    private String nameLaptop;
    private String brandLaptop;
    private String loginLaptop;
    private String passwordLaptop;
    private Employee employee;

    public Laptop(){
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

    public String getPasswordLaptop() {
        return passwordLaptop;
    }

    public void setPasswordLaptop(String passwordLaptop) {
        this.passwordLaptop = passwordLaptop;
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
