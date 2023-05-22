package account.util;

import lombok.Getter;

public enum RoleUtil {
    ADMIN("ROLE_ADMINISTRATOR"),
    ACCOUNTANT("ROLE_ACCOUNTANT"),
    USER("ROLE_USER");

    @Getter
    private final String value;

    RoleUtil(String value) {
        this.value = value;
    }

}
