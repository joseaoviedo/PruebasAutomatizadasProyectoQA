import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class PruebasDeEmanuelle {

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
