

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;

/**
 * The CurrencyConverterDemo class provides functionality to convert amounts
 * between different currencies using predefined exchange rates. It also
 * supports auto-updating of exchange rates to simulate real-world changes.
 *
 * <p>This class maintains a list of valid currency codes and a map of exchange
 * rates between these currencies. It provides methods to set exchange rates,
 * convert amounts between currencies, validate currency codes, and auto-update
 * exchange rates.</p>
 *
 * <p>Example usage:</p>
 * <pre>
 * {@code
 * CurrencyConverterDemo converter = new CurrencyConverterDemo();
 * converter.setRate("USD", "CNY", 6.90);
 * double usdToCny = converter.convert(100, "USD", "CNY");
 * System.out.println("100 usd in cny: " + usdToCny);
 * }
 * </pre>
 *
 * <p>Note: This class is for demonstration purposes and does not connect to
 * any real-world currency exchange rate service.</p>
 *
 * @author Haopeng Liu
 * @version 1.0
 */
public class CurrencyConverterDemo {

    // stores exchange rates: exchangeRates.get("USD").get("EUR") -> rate
    /**
     * A map that stores exchange rates between different currencies.
     * The outer map's key is the source currency code (e.g., "USD").
     * The value of the outer map is another map.
     * the value is the exchange rate from the source currency to the target currency.
     */
    private Map<String, Map<String, Double>> exchangeRates;

    // stores all valid currency codes, like "USD" or "CNY"
    /**
     * A set containing the list of available currencies.
     */
    private Set<String> currencyList;

    // constructor
    /**
     * Constructor for the CurrencyConverterDemo class.
     * Initializes the exchangeRates map and the currencyList set.
     */
    public CurrencyConverterDemo() {
        exchangeRates = new HashMap<>();
        currencyList = new HashSet<>();
    }

    // adds or updates the rate from one currency to another
    /**
     * Sets the exchange rate between two currencies.
     * If the currencies are not already tracked, they will be added to the currency list.
     *
     * @param fromCur the currency code to convert from
     * @param toCur the currency code to convert to
     * @param rate the exchange rate from the source currency to the target currency
     */
    public final void setRate(String fromCur, String toCur, double rate) {
        // add these currencies if they aren't tracked yet
        currencyList.add(fromCur);
        currencyList.add(toCur);

        if (!exchangeRates.containsKey(fromCur)) {
            exchangeRates.put(fromCur, new HashMap<>());
        }
        exchangeRates.get(fromCur).put(toCur, rate);
    }

    // converts amount from one currency to another
    /**
     * Converts an amount from one currency to another using predefined exchange rates.
     *
     * @param amount the amount of money to convert
     * @param fromCur the currency code of the currency to convert from
     * @param toCur the currency code of the currency to convert to
     * @return the converted amount in the target currency, or -1.
     */
    public final double convert(double amount, String fromCur, String toCur) {
        if (!isValid(fromCur) || !isValid(toCur)) {
            System.out.println("error: invalid currency code");
            return -1;
        }
        if (!exchangeRates.containsKey(fromCur)
                || !exchangeRates.get(fromCur).containsKey(toCur)) {
            System.out.println(
                    "error: no exchange rate from " + fromCur + " to " + toCur);
            return -1;
        }
        double rate = exchangeRates.get(fromCur).get(toCur);
        return amount * rate;
    }

    // checks if the currency code is in our list
    /**
     * Checks if the given currency code is valid.
     *
     * @param code the currency code to check
     * @return true if the currency code is in the currency list, false otherwise
     */
    public final boolean isValid(String code) {
        return currencyList.contains(code);
    }

    // randomly adjusts existing exchange rates to mimic an auto-update
    /**
     * Automatically updates the exchange rates by applying a random factor
     * of +/- 5% to each rate. This simulates the fluctuation in currency
     * exchange rates.
     *
     * The method iterates through all the currency pairs in the exchangeRates
     * map and adjusts each rate by a random factor between 0.95 and 1.05.
     *
     * After updating the rates, it prints a message indicating that the rates
     * have been updated.
     */
    public final void autoUpdateRates() {
        for (String fromCur : exchangeRates.keySet()) {
            Map<String, Double> innerMap = exchangeRates.get(fromCur);
            for (String toCur : innerMap.keySet()) {
                double oldRate = innerMap.get(toCur);
                // shift rates by a random factor of +/- 5%
                double newRate = oldRate * (0.95 + (Math.random() * 0.1));
                innerMap.put(toCur, newRate);
            }
        }
        System.out.println("rates updated (simulated)");
    }

    // shortcut to convert any currency to CNY
    public final double convertToCNY(double amount, String fromCur) {
        return convert(amount, fromCur, "CNY");
    }

    // returns all valid currencies we know about
    public final Set<String> getCurrencies() {
        return new HashSet<>(currencyList);
    }

    // simple demo in main
    public static void main(String[] args) {
        CurrencyConverterDemo converter = new CurrencyConverterDemo();

        // set some rates
        converter.setRate("USD", "CNY", 6.90);
        converter.setRate("USD", "EUR", 0.93);
        converter.setRate("CNY", "USD", 0.145);
        converter.setRate("CNY", "EUR", 0.135);

        // print supported currencies
        System.out.println("currencies: " + converter.getCurrencies());

        // convert 100 usd to cny
        double usdToCny = converter.convert(100, "USD", "CNY");
        System.out.println("100 usd in cny: " + usdToCny);

        // convert 100 cny to eur
        double cnyToEur = converter.convert(100, "CNY", "EUR");
        System.out.println("100 cny in eur: " + cnyToEur);

        // convert 50 eur to cny via shortcut
        double eurToCny = converter.convertToCNY(50, "EUR");
        System.out.println("50 eur in cny: " + eurToCny);

        // simulate auto-updating rates
        converter.autoUpdateRates();

        // convert again after update
        System.out.println("after update, 100 usd in cny: " + converter.convert(100, "USD", "CNY"));
    }
}
