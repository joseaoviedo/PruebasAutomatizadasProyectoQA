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

    Base base;

    @Before
    public void setup() throws InterruptedException {

        WebDriver driver = null;
        base = new Base(driver);
        base.firefoxDriverConnection();
        base.visit("http://localhost:8080/admin/logon.html");

        base.setText("admin@shopizer.com", By.id("username"));
        base.setText("password", By.id("password"));
        base.clickId("formSubmitButton");
        Thread.sleep(2000);

    }

    /*
    -------------------------------
        Pruebas automatizadas
    -------------------------------

     */

    @Test
    public void prueba1() {

        base.redirect("http://localhost:8080/admin/content/pages/create.html");

        base.clickId("visible1");
        base.clickId("linkToMenu1");
        base.clearText(By.id("code"));
        base.setText("001",By.id("code"));
        base.clearText(By.id("order"));
        base.setText("0",By.id("order"));
        base.clearText(By.id("name0"));
        base.setText("Test",By.id("name0"));
        base.clearText(By.id("seUrl0"));
        base.setText("www.google.com",By.id("seUrl0"));
        base.clearText(By.id("descriptions0.metatagTitle"));
        base.setText("Titulo de prueba",By.id("descriptions0.metatagTitle"));
        base.clearText(By.id("descriptions0.metatagDescription"));
        base.setText("Keywords de prueba",By.id("descriptions0.metatagKeywords"));
        base.clearText(By.id("descriptions0.metatagDescription"));
        base.setText("Descripción de prueba",By.id("descriptions0.metatagDescription"));

        base.clickClass("btn-success");

        assertEquals("Request completed with success", base.getText(By.id("store.success")));

    }

    @Test
    public void prueba2() {


        base.redirect("http://localhost:8080/admin/content/pages/create.html");

        base.clickId("visible1");
        base.clickId("linkToMenu1");
        base.clearText(By.id("code"));
        base.clearText(By.id("order"));
        base.setText("0",By.id("order"));
        base.clearText(By.id("name0"));
        base.setText("Test",By.id("name0"));
        base.clearText(By.id("seUrl0"));
        base.setText("www.google.com",By.id("seUrl0"));
        base.clearText(By.id("descriptions0.metatagTitle"));
        base.setText("Titulo de prueba",By.id("descriptions0.metatagTitle"));
        base.clearText(By.id("descriptions0.metatagDescription"));
        base.setText("Keywords de prueba",By.id("descriptions0.metatagKeywords"));
        base.clearText(By.id("descriptions0.metatagDescription"));
        base.setText("Descripción de prueba",By.id("descriptions0.metatagDescription"));

        base.clickClass("btn-success");

        assertEquals("", base.getText(By.id("store.success")));


    }

    @Test
    public void prueba3() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba4() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba5() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba6() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba7() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba8() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba9() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba10() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba11() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba12() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba13() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba14() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba15() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba16() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba17() {

        assertEquals(0, 0);

    }
    @Test
    public void prueba18() {

        assertEquals(0, 0);

    }
    @Test
    public void prueba19() {

        assertEquals(0, 0);

    }

    @Test
    public void prueba20() {

        assertEquals(0, 0);

    }



}
