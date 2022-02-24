package org.matusikl.dto.roledto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class RolePostDto {

    @NotNull
    @Pattern(message = "{role.role.pattern}", regexp = "^(ROLE_).*")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
