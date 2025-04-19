package components.currencyconverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

/**
 * test class for CurrencyConverter1L implementation
 *
 * tests basic methods of the kernel interface like set exchange rates, convert,
 * and checking valid currencies
 */
public class CurrencyConverter1LTest {

    /**
     * tests setting a rate and converting a value.
     *
     * sets USD to CNY rate and checks conversion result
     */
    @Test
    public void testSetAndConvert() {
        CurrencyConverter1L converter = new CurrencyConverter1L();
        converter.setExchangeRate("USD", "CNY", 7.2);
        double result = converter.convert(10, "USD", "CNY");
        assertEquals(72.0, result, 0.001);
    }

    /**
     * tests isValidCurrency with a valid currency.
     *
     * checks that a currency with defined rates is reported as valid
     */
    @Test
    public void testIsValidCurrencyTrue() {
        CurrencyConverter1L converter = new CurrencyConverter1L();
        converter.setExchangeRate("USD", "CNY", 7.0);
        assertTrue(converter.isValidCurrency("USD"));
    }

    /**
     * tests isValidCurrency with an undefined currency.
     *
     * ensures undefined currencies are reported as invalid
     */
    @Test
    public void testIsValidCurrencyFalse() {
        CurrencyConverter1L converter = new CurrencyConverter1L();
        assertFalse(converter.isValidCurrency("EUR"));
    }

    /**
     * tests the clear method.
     *
     * ensures that currencies are no longer valid after clearing
     */
    @Test
    public void testClear() {
        CurrencyConverter1L converter = new CurrencyConverter1L();
        converter.setExchangeRate("USD", "CNY", 7.0);
        converter.clear();
        assertFalse(converter.isValidCurrency("USD"));
    }

    /**
     * tests getting the list of supported currencies.
     *
     * ensures the list contains added currencies
     */
    @Test
    public void testGetSupportedCurrencies() {
        CurrencyConverter1L converter = new CurrencyConverter1L();
        converter.setExchangeRate("USD", "CNY", 7.0);
        List<String> supported = converter.getSupportedCurrencies();
        assertTrue(supported.contains("USD"));
    }
}