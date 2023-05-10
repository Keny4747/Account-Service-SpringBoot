package account.util;

import org.springframework.stereotype.Component;

@Component
public class SalaryFormatter {
    public String getFormattedSalary(Long salary) {
        return String.format("%d dollar(s) %02d cent(s)", salary / 100, salary % 100);
    }

}
