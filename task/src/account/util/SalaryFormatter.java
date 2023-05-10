package account.util;

import org.springframework.stereotype.Component;

@Component
public class SalaryFormatter {
    public String getFormattedSalary(Long salary) {
        String dollars = String.valueOf(salary).substring(0,String.valueOf(salary).length()-2);
        String cents = String.valueOf(salary).substring(String.valueOf(salary).length()-2);
        return String.format("%s dollar(s) %s cent(s)",dollars,cents);
    }
}
