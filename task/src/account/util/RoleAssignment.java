package account.util;

import static account.util.RoleUtil.*;

public class RoleAssignment {
    public static String roleAssign(String operation){
        switch (operation) {
            case "ADMINISTRATOR" -> {
                return ADMIN.getValue();
            }
            case "ACCOUNTANT" -> {
                return ACCOUNTANT.getValue();
            }
            case "USER" -> {
                return USER.getValue();
            }
            default -> {
                return "";
            }
        }

    }
}
