package account.util;

import static account.util.RoleUtil.ACCOUNTANT;
import static account.util.RoleUtil.ADMIN;

public class RoleAssignment {
    public static String roleAssign(String operation){
        switch (operation) {
            case "ADMIN" -> {
                return ADMIN.getValue();
            }
            case "ACCOUNTANT" -> {
                return ACCOUNTANT.getValue();
            }
            default -> {
                return "";
            }
        }

    }
}
