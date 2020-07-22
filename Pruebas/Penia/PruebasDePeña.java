package Penia;

import Emanuelle.Base;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;

import static org.junit.Assert.assertEquals;

public class PruebasDePeña {

    @Before
    public void setup(){

        WebDriver driver = null;
        Base base = new Base(driver);
        base.firefoxDriverConnection();
        base.visit("");

    }

    @Test
    public void prueba1() {

        assertEquals(0, 0);

    }

}
