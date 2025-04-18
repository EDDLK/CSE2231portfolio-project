package components.currencyconverter;

import components.standard.Standard;

/**
 * kernel interface for CurrencyConverter component.
 */
public interface CurrencyConverterKernel extends Standard<CurrencyConverter> {

    /**
     * sets the exchange rate between two currencies.
     *
     * @param fromCurrency
     *            the original currency code
     * @param toCurrency
     *            the target currency code
     * @param rate
     *            the exchange rate
     * @requires rate > 0
     * @ensures exchange rate is stored for future conversion
     */
    void setExchangeRate(String fromCurrency, String toCurrency, double rate);

    /**
     * converts a value from one currency to another.
     *
     * @param amount
     *            the amount of money to convert
     * @param fromCurrency
     *            the currency code to convert from
     * @param toCurrency
     *            the currency code to convert to
     * @return the converted value
     * @requires amount >= 0 and valid currency codes
     * @ensures returns correct conversion based on stored rate
     */
    double convert(double amount, String fromCurrency, String toCurrency);

    /**
     * checks if a currency code is valid.
     *
     * @param currencyCode
     *            the currency code
     * @return true if valid, false otherwise
     * @ensures returns true for valid codes only
     */
    boolean isValidCurrency(String currencyCode);
}
