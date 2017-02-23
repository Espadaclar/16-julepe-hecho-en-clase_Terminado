

import static org.junit.Assert.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * The test class CartaTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class CartaTest
{
    private Carta carta1_4oros;
    private Carta carta1_2espad;
    private Carta carta1_12bast;
    private Carta carta1_7copas;
    private Carta carta1_1oros;

    /**
     * Default constructor for test class CartaTest
     */
    public CartaTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
        carta1_4oros = new Carta(4, Palo.OROS);
        carta1_2espad = new Carta(2, Palo.ESPADAS);
        carta1_12bast = new Carta(12, Palo.BASTOS);
        carta1_7copas = new Carta(7, Palo.COPAS);
        carta1_1oros = new Carta(1, Palo.OROS);
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }
}
