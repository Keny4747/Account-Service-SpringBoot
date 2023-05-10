package account.util;

import org.springframework.stereotype.Component;

import java.time.YearMonth;
import java.time.format.TextStyle;
import java.util.Locale;

@Component
public class DatePaymentFormat {

    public String getFormattedPeriod(YearMonth period){
       return period.getMonth().getDisplayName(TextStyle.FULL, Locale.ENGLISH)
                .concat("-")
                .concat(String.valueOf(period.getYear()));
    }

}
