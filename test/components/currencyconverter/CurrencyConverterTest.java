package components.currencyconverter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * test class for the extended CurrencyConverter interface.
 *
 * tests enhanced methods provided by the secondary implementation including
 * conversion to CNY and object contract methods
 */
public class CurrencyConverterTest {

    /**
     * tests the convertToCNY convenience method.
     *
     * verifies it correctly converts to Chinese Yuan
     */
    @Test
    public void testConvertToCNY() {
        CurrencyConverter1L converter = new CurrencyConverter1L();
        converter.setExchangeRate("USD", "CNY", 7.3);
        double result = converter.convertToCNY(100, "USD");
        assertEquals(730.0, result, 0.001);
    }

    /**
     * tests the toString method.
     *
     * ensures it contains the supported currency codes
     */
    @Test
    public void testToStringContainsCurrency() {
        CurrencyConverter1L converter = new CurrencyConverter1L();
        converter.setExchangeRate("USD", "CNY", 7.2);
        String output = converter.toString();
        assertTrue(output.contains("USD"));
    }

    /**
     * tests equality of converters with same rates.
     *
     * verifies that converters with identical rates are equal
     */
    @Test
    public void testEqualsTrue() {
        CurrencyConverter1L c1 = new CurrencyConverter1L();
        c1.setExchangeRate("USD", "CNY", 7.2);

        CurrencyConverter1L c2 = new CurrencyConverter1L();
        c2.setExchangeRate("USD", "CNY", 7.2);

        assertTrue(c1.equals(c2));
    }

    /**
     * tests inequality of converters with different rates.
     *
     * ensures converters with different rates are not equal
     */
    @Test
    public void testEqualsFalse() {
        CurrencyConverter1L c1 = new CurrencyConverter1L();
        c1.setExchangeRate("USD", "CNY", 7.2);

        CurrencyConverter1L c2 = new CurrencyConverter1L();
        c2.setExchangeRate("USD", "CNY", 6.9);

        assertFalse(c1.equals(c2));
    }

    /**
     * tests hashCode consistency with equals.
     *
     * verifies equal objects have same hash codes
     */
    @Test
    public void testHashCodeSame() {
        CurrencyConverter1L c1 = new CurrencyConverter1L();
        c1.setExchangeRate("USD", "CNY", 7.0);

        CurrencyConverter1L c2 = new CurrencyConverter1L();
        c2.setExchangeRate("USD", "CNY", 7.0);

        assertEquals(c1.hashCode(), c2.hashCode());
    }
}