package components.currencyconverter;

import java.util.List;

/**
 * abstract class for the CurrencyConverter component. Implements enhanced
 * methods using only kernel methods.
 *
 * @author Haopeng Liu
 * @version 2025.04.5
 */
public abstract class CurrencyConverterSecondary implements CurrencyConverter {

    /**
     * constant used for initial hash value.
     */
    private static final int INITIAL_HASH = 17;

    /**
     * constant used for multiplying hash values.
     */
    private static final int PRIME = 31;

    /**
     * {@inheritDoc}
     */
    @Override
    public final double convertToCNY(double amount, String fromCurrency) {
        assert this.isValidCurrency(fromCurrency) : "Invalid currency code";
        return this.convert(amount, fromCurrency, "CNY");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final String toString() {
        List<String> codes = this.getSupportedCurrencies();
        StringBuilder sb = new StringBuilder("Supported currencies: ");
        for (String code : codes) {
            sb.append(code).append(" ");
        }
        return sb.toString().trim();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof CurrencyConverter)) {
            return false;
        }
        CurrencyConverter other = (CurrencyConverter) obj;
        List<String> codes = this.getSupportedCurrencies();
        for (String from : codes) {
            for (String to : codes) {
                if (this.convert(1.0, from, to) != other.convert(1.0, from,
                        to)) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final int hashCode() {
        int result = INITIAL_HASH;
        for (String from : this.getSupportedCurrencies()) {
            for (String to : this.getSupportedCurrencies()) {
                result = PRIME * result
                        + Double.hashCode(this.convert(1.0, from, to));
            }
        }
        return result;
    }
}
