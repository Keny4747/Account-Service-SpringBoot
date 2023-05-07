package account.util;

public enum DatePaymentFormat {

     DATEFORMAT("MM/YYYY");

    private final String formatValue;

    private DatePaymentFormat(String formatValue){
        this.formatValue = formatValue;
    }
    public String getDateFormat() {
        return formatValue;
    }
}
