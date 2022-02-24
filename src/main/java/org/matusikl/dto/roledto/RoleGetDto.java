package org.matusikl.dto.roledto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RoleGetDto {

    private int idRole;

    @NotNull
    @Pattern(message = "{role.role.pattern}", regexp = "^(ROLE_).*")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }
}
