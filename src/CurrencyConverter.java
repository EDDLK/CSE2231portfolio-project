/**
 * enhanced interface for CurrencyConverter component.
 */
public interface CurrencyConverter extends CurrencyConverterKernel {

    /**
     * automatically updates all currency exchange rates from an external
     * source.
     *
     * @ensures all rates are refreshed with new values
     */
    void updateRatesAutomatically();

    /**
     * converts some amount from any currency to CNY.
     *
     * @param amount
     *            the amount to convert
     * @param fromCurrency
     *            the original currency
     * @return the value in CNY
     * @requires amount >= 0 and valid currency code
     * @ensures returns correct value in CNY
     */
    double convertToCNY(double amount, String fromCurrency);

    /**
     * returns list of supported currency codes.
     *
     * @return list of currency codes
     * @ensures list contains only valid codes
     */
    java.util.List<String> getSupportedCurrencies();
}
