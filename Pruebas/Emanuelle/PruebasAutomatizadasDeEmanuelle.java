package Pruebas.Emanuelle;

import Pruebas.Base;
import org.openqa.selenium.By;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import org.junit.*;

import static org.junit.Assert.*;

public class PruebasAutomatizadasDeEmanuelle {

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

        Assert.assertEquals("Request completed with success", base.getText(By.id("store.success")));

    }

    @Test
    public void prueba2() {

        base.redirect("http://localhost:8080/admin/content/pages/create.html");

        base.clickId("visible1");
        base.clickId("linkToMenu1");
        base.clearText(By.id("code"));
        base.clearText(By.id("order"));
        base.setText("003",By.id("order"));
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

        Assert.assertEquals("", base.getText(By.id("store.success")));

    }

    @Test
    public void prueba3() {

        base.redirect("http://localhost:8080/admin/content/pages/create.html");

        base.clickId("visible1");
        base.clickId("linkToMenu1");
        base.clearText(By.id("code"));
        base.setText("001",By.id("code"));
        base.clearText(By.id("order"));
        base.setText("0",By.id("order"));
        base.clearText(By.id("seUrl0"));
        base.setText("www.google.com",By.id("seUrl0"));
        base.clearText(By.id("descriptions0.metatagTitle"));
        base.setText("Titulo de prueba",By.id("descriptions0.metatagTitle"));
        base.clearText(By.id("descriptions0.metatagDescription"));
        base.setText("Keywords de prueba",By.id("descriptions0.metatagKeywords"));
        base.clearText(By.id("descriptions0.metatagDescription"));
        base.setText("Descripción de prueba",By.id("descriptions0.metatagDescription"));

        base.clickClass("btn-success");

        Assert.assertEquals("", base.getText(By.id("store.success")));

    }

    @Test
    public void prueba4() {

        base.redirect("http://localhost:8080/admin/content/pages/create.html");

        base.clickId("visible1");
        base.clickId("linkToMenu1");
        base.clearText(By.id("code"));
        base.setText("001",By.id("code"));
        base.clearText(By.id("order"));
        base.setText("0",By.id("order"));
        base.clearText(By.id("name0"));
        base.setText("Nombre de prueba extremadamente grandeNombre de prueba extremadamente grande",By.id("name0"));
        base.clearText(By.id("seUrl0"));
        base.setText("www.google.com",By.id("seUrl0"));
        base.clearText(By.id("descriptions0.metatagTitle"));
        base.setText("Titulo de prueba",By.id("descriptions0.metatagTitle"));
        base.clearText(By.id("descriptions0.metatagDescription"));
        base.setText("Keywords de prueba",By.id("descriptions0.metatagKeywords"));
        base.clearText(By.id("descriptions0.metatagDescription"));
        base.setText("Descripción de prueba",By.id("descriptions0.metatagDescription"));

        base.clickClass("btn-success");

        Assert.assertEquals("", base.getText(By.id("store.success")));

    }

    @Test
    public void prueba5() {

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
        base.clearText(By.id("descriptions0.metatagDescription"));
        base.setText("Descripción de prueba",By.id("descriptions0.metatagDescription"));

        base.clickClass("btn-success");

        Assert.assertEquals("Request completed with success", base.getText(By.id("store.success")));

    }

    @Test
    public void prueba6() {

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

        Assert.assertEquals("Request completed with success", base.getText(By.id("store.success")));

    }

    @Test
    public void prueba7() {

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
        base.clearText(By.id("descriptions0.metatagTitle"));
        base.setText("Titulo de prueba",By.id("descriptions0.metatagTitle"));
        base.clearText(By.id("descriptions0.metatagDescription"));
        base.setText("Keywords de prueba",By.id("descriptions0.metatagKeywords"));
        base.clearText(By.id("descriptions0.metatagDescription"));
        base.setText("Descripción de prueba",By.id("descriptions0.metatagDescription"));

        base.clickClass("btn-success");

        Assert.assertEquals("Request completed with success", base.getText(By.id("store.success")));

    }

    @Test
    public void prueba8() throws InterruptedException {

        // Crear una página

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

        Thread.sleep(1000);

        base.redirect("http://localhost:8080/admin/content/pages/list.html");

        Thread.sleep(1000);
        Actions act = new Actions(base.getDriver());
        WebElement shop = base.getDriver().findElement((By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div[3]/div/div[1]/table/tbody[2]/tr/td[5]/div/nobr/img")));
        act.moveToElement(shop).click().build().perform();

        Thread.sleep(1000);

        base.acceptAlert();

        Thread.sleep(1000);

        Assert.assertEquals("No items to show.", base.getText(By.className("emptyMessage")));

    }

    @Test
    public void prueba9() throws InterruptedException {

        base.redirect("http://localhost:8080/admin/content/boxes/create.html");
        base.clickId("visible1");
        base.clickId("linkToMenu1");
        base.clearText(By.id("code"));
        base.setText("002",By.id("code"));
        base.clearText(By.id("order"));
        base.setText("0",By.id("order"));
        base.clearText(By.id("name0"));
        base.setText("Test",By.id("name0"));
        base.clickClass("btn-success");

        Thread.sleep(1000);

        Assert.assertEquals("Request completed with success", base.getText(By.className("alert-success")));

    }

    @Test
    public void prueba10() throws InterruptedException {

        base.redirect("http://localhost:8080/admin/content/boxes/create.html");
        base.clickId("visible1");
        base.clickId("linkToMenu1");
        base.clearText(By.id("code"));
        base.clearText(By.id("order"));
        base.setText("0",By.id("order"));
        base.clearText(By.id("name0"));
        base.setText("Test",By.id("name0"));
        base.clickClass("btn-success");

        Thread.sleep(1000);

        Assert.assertNotEquals("Request completed with success", base.getText(By.className("alert-success")));

    }

    @Test
    public void prueba11() throws InterruptedException {

        base.redirect("http://localhost:8080/admin/content/boxes/create.html");
        base.clickId("visible1");
        base.clickId("linkToMenu1");
        base.clearText(By.id("code"));
        base.setText("002",By.id("code"));
        base.clearText(By.id("order"));
        base.setText("0",By.id("order"));
        base.clearText(By.id("name0"));
        base.clickClass("btn-success");

        Thread.sleep(1000);

        Assert.assertNotEquals("Request completed with success", base.getText(By.className("alert-success")));

    }

    @Test
    public void prueba12() throws InterruptedException {

        base.redirect("http://localhost:8080/admin/content/boxes/create.html");
        base.clickId("visible1");
        base.clickId("linkToMenu1");
        base.clearText(By.id("code"));
        base.setText("002",By.id("code"));
        base.clearText(By.id("order"));
        base.setText("0",By.id("order"));
        base.clearText(By.id("name0"));
        base.setText("Test",By.id("name0"));
        base.clickClass("btn-success");

        base.redirect("http://localhost:8080/admin/content/boxes/create.html");
        base.clickId("visible1");
        base.clickId("linkToMenu1");
        base.clearText(By.id("code"));
        base.setText("002",By.id("code"));
        base.clearText(By.id("order"));
        base.setText("0",By.id("order"));
        base.clearText(By.id("name0"));
        base.setText("Test",By.id("name0"));
        base.clickClass("btn-success");

        Thread.sleep(1000);

        Assert.assertEquals("Request completed with success", base.getText(By.className("alert-success")));

    }

    @Test
    public void prueba13() throws InterruptedException {

        base.redirect("http://localhost:8080/admin/content/contentImages.html");
        WebElement uploadElement = base.getDriver().findElement(By.className("input-file"));
        uploadElement.sendKeys("C:\\Users\\emanu\\Downloads\\foto.png");
        Thread.sleep(1000);
        base.clickClass("btn-success");

        Thread.sleep(1000);

        Assert.assertEquals("foto.png",base.getText(By.cssSelector("html body.body div.sm div.container div.row div.span9 div.tabbable div.tab-content div#catalogue-section.tab-pane.active div.sm-ui-component div#isc_B.normal div#isc_C div#isc_3.sectionStack div#isc_4 div#isc_0 div#isc_1 div#isc_J.simpleTile div#isc_K table.normal tbody tr td.name nobr")));

        Thread.sleep(1000);

        Actions act = new Actions(base.getDriver());
        WebElement shop = base.getDriver().findElement((By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div/img")));
        act.moveToElement(shop).click().build().perform();
        base.acceptAlert();

    }

    @Test
    public void prueba14() throws InterruptedException {

        base.redirect("http://localhost:8080/admin/content/contentImages.html");
        WebElement uploadElement = base.getDriver().findElement(By.className("input-file"));
        uploadElement.sendKeys("C:\\Users\\emanu\\Downloads\\fotovacia.png");
        Thread.sleep(1000);
        base.clickClass("btn-success");

        Thread.sleep(1000);
        try{
            Assert.assertEquals("fotovacia.png",base.getText(By.cssSelector("html body.body div.sm div.container div.row div.span9 div.tabbable div.tab-content div#catalogue-section.tab-pane.active div.sm-ui-component div#isc_B.normal div#isc_C div#isc_3.sectionStack div#isc_4 div#isc_0 div#isc_1 div#isc_J.simpleTile div#isc_K table.normal tbody tr td.name nobr")));
        }catch(Exception e){
            assertTrue(false);
        }

    }

    @Test
    public void prueba15() throws InterruptedException {

        base.redirect("http://localhost:8080/admin/content/contentImages.html");
        WebElement uploadElement = base.getDriver().findElement(By.className("input-file"));
        uploadElement.sendKeys("C:\\Users\\emanu\\Downloads\\fotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofo.png");
        Thread.sleep(1000);
        base.clickClass("btn-success");

        Thread.sleep(1000);

        Assert.assertNotEquals("fotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofotoofo.png",base.getText(By.cssSelector("html body.body div.sm div.container div.row div.span9 div.tabbable div.tab-content div#catalogue-section.tab-pane.active div.sm-ui-component div#isc_B.normal div#isc_C div#isc_3.sectionStack div#isc_4 div#isc_0 div#isc_1 div#isc_J.simpleTile div#isc_K table.normal tbody tr td.name nobr")));

        Thread.sleep(1000);

        try{
            Actions act = new Actions(base.getDriver());
            WebElement shop = base.getDriver().findElement((By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div/img")));
            act.moveToElement(shop).click().build().perform();
            base.acceptAlert();
        }catch (Exception e){

        }


    }

    @Test
    public void prueba16() throws InterruptedException {

        base.redirect("http://localhost:8080/admin/content/contentImages.html");
        WebElement uploadElement = base.getDriver().findElement(By.className("input-file"));
        uploadElement.sendKeys("C:\\Users\\emanu\\Downloads\\prueba_imagen_grande.png");
        Thread.sleep(1000);
        base.clickClass("btn-success");

        Thread.sleep(1000);

        Assert.assertEquals("prueba_imagen_grande.png",base.getText(By.cssSelector("html body.body div.sm div.container div.row div.span9 div.tabbable div.tab-content div#catalogue-section.tab-pane.active div.sm-ui-component div#isc_B.normal div#isc_C div#isc_3.sectionStack div#isc_4 div#isc_0 div#isc_1 div#isc_J.simpleTile div#isc_K table.normal tbody tr td.name nobr")));

        Thread.sleep(1000);

        try{
            Actions act = new Actions(base.getDriver());
            WebElement shop = base.getDriver().findElement((By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div/img")));
            act.moveToElement(shop).click().build().perform();
            base.acceptAlert();
        }catch (Exception e){

        }


    }

    @Test
    public void prueba17() {

        base.redirect("http://localhost:8080/admin/content/contentImages.html");
        WebElement uploadElement = base.getDriver().findElement(By.className("input-file"));


        try{
            uploadElement.sendKeys("C:\\Users\\emanu\\Desktop\\prueba.txt");
            Thread.sleep(1000);
            base.clickClass("btn-success");

            Thread.sleep(1000);
            Assert.assertNotEquals("prueba.txt",base.getText(By.cssSelector("html body.body div.sm div.container div.row div.span9 div.tabbable div.tab-content div#catalogue-section.tab-pane.active div.sm-ui-component div#isc_B.normal div#isc_C div#isc_3.sectionStack div#isc_4 div#isc_0 div#isc_1 div#isc_J.simpleTile div#isc_K table.normal tbody tr td.name nobr")));

            Thread.sleep(1000);
            Actions act = new Actions(base.getDriver());
            WebElement shop = base.getDriver().findElement((By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div/img")));
            act.moveToElement(shop).click().build().perform();
            base.acceptAlert();
        }catch (InvalidArgumentException | InterruptedException e){
            assertTrue(false);
        }

    }

    @Test
    public void prueba19() throws InterruptedException {

        base.redirect("http://localhost:8080/admin/content/contentImages.html");
        WebElement uploadElement = base.getDriver().findElement(By.className("input-file"));
        uploadElement.sendKeys("C:\\Users\\emanu\\Downloads\\foto.png");
        Thread.sleep(1000);
        base.clickClass("btn-success");

        Thread.sleep(1000);

        Assert.assertEquals("foto.png",base.getText(By.cssSelector("html body.body div.sm div.container div.row div.span9 div.tabbable div.tab-content div#catalogue-section.tab-pane.active div.sm-ui-component div#isc_B.normal div#isc_C div#isc_3.sectionStack div#isc_4 div#isc_0 div#isc_1 div#isc_J.simpleTile div#isc_K table.normal tbody tr td.name nobr")));

        Thread.sleep(1000);

        Actions act = new Actions(base.getDriver());
        WebElement shop = base.getDriver().findElement((By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div/img")));
        act.moveToElement(shop).click().build().perform();
        base.acceptAlert();

    }
    
    @Test
    public void prueba18() {

        base.redirect("http://localhost:8080/admin/content/contentImages.html");
        WebElement uploadElement = base.getDriver().findElement(By.className("input-file"));


        try{
            uploadElement.sendKeys("C:\\Users\\emanu\\Downloads\\!#$%&()=.png");
            Thread.sleep(1000);
            base.clickClass("btn-success");

            Thread.sleep(1000);
            Assert.assertEquals("!#$%&()=.png",base.getText(By.cssSelector("html body.body div.sm div.container div.row div.span9 div.tabbable div.tab-content div#catalogue-section.tab-pane.active div.sm-ui-component div#isc_B.normal div#isc_C div#isc_3.sectionStack div#isc_4 div#isc_0 div#isc_1 div#isc_J.simpleTile div#isc_K table.normal tbody tr td.name nobr")));

            Thread.sleep(1000);
            Actions act = new Actions(base.getDriver());
            WebElement shop = base.getDriver().findElement((By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div[2]/div/div[1]/div/img")));
            act.moveToElement(shop).click().build().perform();
            base.acceptAlert();
        }catch (InvalidArgumentException | InterruptedException e){
            assertTrue(false);
        }


    }

    @Test
    public void prueba20() throws InterruptedException {

        base.redirect("http://localhost:8080/admin/content/static/contentFiles.html");
        WebElement uploadElement = base.getDriver().findElement(By.className("input-file"));
        uploadElement.sendKeys("C:\\Users\\emanu\\Downloads\\foto.png");
        Thread.sleep(1000);
        base.clickClass("btn-success");

        Thread.sleep(1000);

        Assert.assertEquals("foto.png",base.getText(By.cssSelector("#isc_Ptable > tbody:nth-child(3) > tr:nth-child(1) > td:nth-child(1) > div:nth-child(1) > nobr:nth-child(1)")));

        Thread.sleep(1000);

        Actions act = new Actions(base.getDriver());
        WebElement shop = base.getDriver().findElement((By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/div/div/div/div/div[2]/div/div[3]/div/div/table/tbody[2]/tr/td[4]/div/nobr/img")));
        act.moveToElement(shop).click().build().perform();
        Thread.sleep(1000);
        base.acceptAlert();

    }

    @After
    public void close(){
        base.Quit();
    }


}
