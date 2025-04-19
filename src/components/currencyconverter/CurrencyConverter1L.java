package components.currencyconverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * currency converter that manages exchange rates between different currencies
 *
 * stores rates as a nested map where: - first level key is source currency -
 * second level key is target currency - value is the exchange rate - example:
 * rates.get("usd").get("eur") = 0.92 means 1 usd = 0.92 eur
 *
 * rules: - currency codes can't be null - rates must be greater than 0 - direct
 * conversion needs an entry in the map
 *
 * @author Haopeng Liu
 * @version 2025.04.18
 */
public class CurrencyConverter1L extends CurrencyConverterSecondary {

    // Stores our conversion rates as a nested map
    /**
     * A nested map that stores currency conversion rates. The outer map's key
     * is the source currency code (e.g., "USD"), and its value is another map.
     * The inner map's key is the target currency code (e.g., "EUR"), and its
     * value is the conversion rate from the source currency to the target
     * currency.
     */
    private Map<String, Map<String, Double>> rates;

    /**
     * creates an empty converter with no rates.
     */
    public CurrencyConverter1L() {
        this.rates = new HashMap<>();
    }

    @Override
    public final void setExchangeRate(String from, String to, double rate) {
        // check inputs
        assert from != null && to != null : "currency codes can't be null";
        assert rate > 0 : "exchange rate must be positive";

        // create inner map if needed and store the rate
        if (!this.rates.containsKey(from)) {
            this.rates.put(from, new HashMap<>());
        }
        this.rates.get(from).put(to, rate);
    }

    @Override
    public final double convert(double amount, String from, String to) {
        // basic checks
        assert amount >= 0 : "can't convert negative amounts";
        assert this.isValidCurrency(from) && this.isValidCurrency(
                to) : "both currencies must be in our system";

        // multiply amount by the exchange rate
        return this.rates.get(from).get(to) * amount;
    }

    @Override
    public final boolean isValidCurrency(String code) {
        // check if currency exists and has conversion rates
        return this.rates.containsKey(code) && this.rates.get(code) != null;
    }

    @Override
    public final List<String> getSupportedCurrencies() {
        // return list of all currencies we have
        return new ArrayList<>(this.rates.keySet());
    }

    @Override
    public final CurrencyConverter newInstance() {
        // create a new empty converter
        return new CurrencyConverter1L();
    }

    @Override
    public final void clear() {
        // remove all rates
        this.rates.clear();
    }

    @Override
    public final void transferFrom(CurrencyConverter source) {
        // check source is valid
        assert source != null : "source converter can't be null";
        assert source instanceof CurrencyConverter1L : "can only transfer from another CurrencyConverter1L";

        // swap the rates map
        CurrencyConverter1L src = (CurrencyConverter1L) source;
        this.rates = src.rates;
        src.rates = new HashMap<>(); // Leave source empty
    }

    /**
     * not implemented yet - will add external api fetch later.
     */
    @Override
    public void updateRatesAutomatically() {
        throw new UnsupportedOperationException("not implemented yet");
    }
}