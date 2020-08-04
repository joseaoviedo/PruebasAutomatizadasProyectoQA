package Pruebas.Alejandro;
import Pruebas.Base;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.*;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.ArrayList;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PruebasUnitariasDeAlejandro {

    Base base;
    JavascriptExecutor js;
    static String correoUser = randomEmail();

    @Before
    public void setup() throws InterruptedException {

        WebDriver driver = null;
        base = new Base(driver);
        base.firefoxDriverConnection();
        js = (JavascriptExecutor) base.getDriver();
        base.visit("http://localhost:8080/shop/");
        Thread.sleep(1000);
        base.clickClass("cc-compliance");
        Thread.sleep(1000);
    }
    /*
    -------------------------------
        Pruebas unitarias
    -------------------------------

     */

    //SHOPTC31
    //Cambiar idioma de la página a Francés
    //@Ignore
    @Test
    public void pruebaA() throws InterruptedException {

        base.clickXPath("/html/body/nav[1]/div/ul[2]/li[1]");
        base.clickXPath("/html/body/nav[1]/div/ul[2]/li[1]/ul/a");
        Thread.sleep(2000);
        assertEquals("Articles vedettes", base.getText(By.className("ilt")));
    }
    //SHOPTC32
    //Botón de volver a la parte superior de la página funcione correctamente
    //@Ignore
    @Test
    public void pruebaB() throws InterruptedException {

        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        Thread.sleep(2000);
        base.clickId("scrollUp");
        Thread.sleep(2000);
        assertTrue(base.isDisplayed(By.xpath("/html/body/div[2]/div/div/div[2]/form[1]/div/div[2]/input")));
    }

    //SHOPTC33
    //Registrarse en la página Shopizer como usuario
    //@Ignore
    @Test
    public void pruebaC() throws InterruptedException {

        base.clickXPath("/html/body/nav[1]/div/ul[2]/li[2]/a/span");
        base.clickXPath("//*[@id=\"registerLink\"]");
        base.setText("NombreEx",By.id("firstName"));
        base.setText("ApellidoEx",By.id("lastName"));
        base.setText("ZoneEx",By.id("hidden_zones"));
        base.setText(correoUser,By.id("emailAddress"));
        base.setText("password123",By.id("password"));
        base.setText("password123",By.id("passwordAgain"));
        base.clickXPath("/html/body/div[4]/div/div/div[1]/div[2]/form/button");
        new WebDriverWait(base.getDriver(), 20).until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("/html/body/div[3]/div/div/div[1]/div/span/p")));

        assertTrue(base.isDisplayed(By.xpath("/html/body/div[3]/div/div/div[1]/div/span/p")));
        System.out.print(correoUser);
    }

    //SHOPTC34
    //Registrarse en la página Shopizer como usuario sin rellenar ningún campo
    //@Ignore
    @Test
    public void pruebaD() throws InterruptedException {

        base.clickXPath("/html/body/nav[1]/div/ul[2]/li[2]/a/span");
        base.clickXPath("//*[@id=\"registerLink\"]");
        base.clickXPath("/html/body/div[4]/div/div/div[1]/div[2]/form/button");
        Thread.sleep(2000);

        assertTrue(base.isDisplayed(By.xpath("//*[@id=\"customer.errors\"]")));
    }

    //SHOPTC35
    //Botón “Facebook” envía a la página de Facebook de la página web
    //@Ignore
    @Test
    public void pruebaE() throws InterruptedException {
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");
        base.clickXPath("/html/body/div[5]/div/div/div[1]/div/ul[2]/li/a/i");
        Thread.sleep(2000);
        ArrayList<String> tabHandles = new ArrayList<String>(base.getDriver().getWindowHandles());
        String pag = tabHandles.get(1);
        base.getDriver().switchTo().window(pag);
        assertEquals("Shopizer - Inicio | Facebook",base.getDriver().getTitle());
    }

    //SHOPTC36
    //Llenar formulario de contactar con nosotros
    //@Ignore
    @Test
    public void pruebaF() throws InterruptedException {
        js.executeScript("window.scrollBy(0,350)", "");
        base.clickXPath("/html/body/div[5]/div/div/div[2]/div/div/ul/li[2]/a");
        Thread.sleep(1000);
        base.setText("NombreEx",By.id("name"));
        base.setText("ejemploEx@gmail.com",By.id("email"));
        base.setText("ConsultaEx",By.id("subject"));
        base.setText("ComentariosEx",By.id("comment"));
        //Scroll down to an element
        WebElement Element = base.findElement(By.id("comment"));
        js.executeScript("arguments[0].scrollIntoView();", Element);
        //Click reCaptcha
        new WebDriverWait(base.getDriver(), 10).until(ExpectedConditions.
                frameToBeAvailableAndSwitchToIt(By.xpath("/html/body/div[3]/div/div[1]/form/fieldset/div[5]/div/div/div/div/iframe")));
        new WebDriverWait(base.getDriver(), 20).until(ExpectedConditions.
                elementToBeClickable(By.xpath("//*[@id=\"recaptcha-anchor\"]"))).click();
        //Salir del iframe
        base.getDriver().switchTo().defaultContent();
        base.clickId("submitContact");
        Thread.sleep(2000);
        assertTrue(base.isDisplayed(By.id("store.success")));
    }

    //SHOPTC37
    //Intentar enviar un formulario de contacta con nosotros sin rellenar el formulario
    //@Ignore
    @Test
    public void pruebaG() throws InterruptedException {
        js.executeScript("window.scrollBy(0,350)", "");
        base.clickXPath("/html/body/div[5]/div/div/div[2]/div/div/ul/li[2]/a");
        Thread.sleep(1000);
        //Scroll down to an element
        WebElement Element = base.findElement(By.id("comment"));
        js.executeScript("arguments[0].scrollIntoView();", Element);
        //Click reCaptcha
        new WebDriverWait(base.getDriver(), 10).until(ExpectedConditions.
                frameToBeAvailableAndSwitchToIt(By.xpath("/html/body/div[3]/div/div[1]/form/fieldset/div[5]/div/div/div/div/iframe")));
        new WebDriverWait(base.getDriver(), 20).until(ExpectedConditions.
                elementToBeClickable(By.xpath("//*[@id=\"recaptcha-anchor\"]"))).click();
        //Salir del iframe
        base.getDriver().switchTo().defaultContent();
        assertFalse(base.findElement(By.id("submitContact")).isEnabled());

    }
    //SHOPTC38
    //Probar funcionalidad de botón “Casa” que debe devolver a la página principal
    //@Ignore
    @Test
    public void pruebaH() throws InterruptedException {
        base.clickXPath("/html/body/nav[2]/div/div[2]/ul/li[2]/a");
        base.clickXPath("/html/body/nav[2]/div/div[2]/ul/li[2]/ul/li[2]/a");
        Thread.sleep(2000);
        base.clickXPath("/html/body/nav[2]/div/div[2]/ul/li[1]/a");
        Thread.sleep(1000);
        assertEquals("Vintage Bags - Shopizer demo",base.getDriver().getTitle());
    }

    //SHOPTC39
    //Iniciar sesión como usuario
    //@Ignore
    @Test
    public void pruebaI() throws InterruptedException {
        System.out.print(correoUser);
        base.visit("http://localhost:8080/shop/customer/customLogon.html");
        Thread.sleep(1000);
        base.setText(correoUser,By.id("signin_userName"));
        base.setText("password123",By.id("signin_password"));
        base.clickId("genericLogin-button");
        new WebDriverWait(base.getDriver(), 20).until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("/html/body/div[3]/div/div/div[1]/div/span/p")));
        assertTrue(base.isDisplayed(By.xpath("/html/body/div[3]/div/div/div[1]/div/span/p")));
        System.out.print(correoUser);
    }
    //SHOPTC40
    //Iniciar sesión como usuario sin rellenar ningún campo
    //@Ignore
    @Test
    public void pruebaJ() throws InterruptedException {
        base.visit("http://localhost:8080/shop/customer/customLogon.html");
        Thread.sleep(1000);
        base.clickId("genericLogin-button");
        assertTrue(base.isDisplayed(By.id("loginError")));

    }
    //SHOPTC41
    //Iniciar sesión como usuario con un correo no registrado
    //@Ignore
    @Test
    public void pruebaK() throws InterruptedException {
        base.visit("http://localhost:8080/shop/customer/customLogon.html");
        Thread.sleep(1000);
        base.setText("noregistrado@gmail.com",By.id("signin_userName"));
        base.setText("password123",By.id("signin_password"));
        base.clickId("genericLogin-button");
        assertTrue(base.isDisplayed(By.id("loginError")));
    }
    //SHOPTC42
    //Iniciar sesión como usuario con un uno contraseña incorrecta
    //@Ignore
    @Test
    public void pruebaL() throws InterruptedException {
        base.visit("http://localhost:8080/shop/customer/customLogon.html");
        Thread.sleep(1000);
        base.setText(correoUser,By.id("signin_userName"));
        base.setText("negativo",By.id("signin_password"));
        base.clickId("genericLogin-button");
        assertTrue(base.isDisplayed(By.id("loginError")));
    }
    //SHOPTC47
    //Registrarse en la página Shopizer como usuario sin llenar el campo “Dirección de correo electrónico”
    //@Ignore
    @Test
    public void pruebaM() throws InterruptedException {
        base.clickXPath("/html/body/nav[1]/div/ul[2]/li[2]/a/span");
        base.clickXPath("//*[@id=\"registerLink\"]");
        base.setText("Nombre",By.id("firstName"));
        base.setText("Apellido",By.id("lastName"));
        base.setText("Zone",By.id("hidden_zones"));
        base.setText("password123",By.id("password"));
        base.setText("password123",By.id("passwordAgain"));
        base.clickXPath("/html/body/div[4]/div/div/div[1]/div[2]/form/button");
        Thread.sleep(1000);
        assertTrue(base.isDisplayed(By.xpath("//*[@id=\"customer.errors\"]")));
    }
    //SHOPTC48
    //Registrarse en la página Shopizer como usuario sin llenar el campo “Contraseña”
    //@Ignore
    @Test
    public void pruebaN() throws InterruptedException {
        base.clickXPath("/html/body/nav[1]/div/ul[2]/li[2]/a/span");
        base.clickXPath("//*[@id=\"registerLink\"]");
        base.setText("Nombre",By.id("firstName"));
        base.setText("Apellido",By.id("lastName"));
        base.setText("Zone",By.id("hidden_zones"));
        base.setText(correoUser,By.id("emailAddress"));
        base.setText("password123",By.id("passwordAgain"));
        base.clickXPath("/html/body/div[4]/div/div/div[1]/div[2]/form/button");
        Thread.sleep(1000);
        assertTrue(base.isDisplayed(By.xpath("//*[@id=\"customer.errors\"]")));
    }
    //SHOPTC49
    //Registrarse en la página Shopizer como usuario sin llenar el campo “Repetir contraseña”
    //@Ignore
    @Test
    public void pruebaO() throws InterruptedException {
        base.clickXPath("/html/body/nav[1]/div/ul[2]/li[2]/a/span");
        base.clickXPath("//*[@id=\"registerLink\"]");
        base.setText("Nombre",By.id("firstName"));
        base.setText("Apellido",By.id("lastName"));
        base.setText("Zone",By.id("hidden_zones"));
        base.setText(correoUser,By.id("emailAddress"));
        base.setText("password123",By.id("password"));
        base.clickXPath("/html/body/div[4]/div/div/div[1]/div[2]/form/button");
        Thread.sleep(1000);
        assertTrue(base.isDisplayed(By.xpath("//*[@id=\"customer.errors\"]")));

    }
    //SHOPTC50
    //Registrarse en la página Shopizer como usuario con un correo ya registrado
    //@Ignore
    @Test
    public void pruebaP() throws InterruptedException {
        base.clickXPath("/html/body/nav[1]/div/ul[2]/li[2]/a/span");
        base.clickXPath("//*[@id=\"registerLink\"]");
        base.setText("Nombre",By.id("firstName"));
        base.setText("Apellido",By.id("lastName"));
        base.setText("Zone",By.id("hidden_zones"));
        base.setText(correoUser,By.id("emailAddress"));
        base.setText("password123",By.id("password"));
        base.setText("password123",By.id("passwordAgain"));
        base.clickXPath("/html/body/div[4]/div/div/div[1]/div[2]/form/button");
        Thread.sleep(1000);
        assertTrue(base.isDisplayed(By.xpath("//*[@id=\"customer.errors\"]")));

    }
    //SHOPTC51
    //Cambiar contraseña en una cuenta usuario de la página
    //@Ignore
    @Test
    public void pruebaQ() throws InterruptedException {
        base.visit("http://localhost:8080/shop/customer/customLogon.html");
        Thread.sleep(1000);
        base.setText(correoUser,By.id("signin_userName"));
        base.setText("password123",By.id("signin_password"));
        base.clickId("genericLogin-button");
        new WebDriverWait(base.getDriver(), 20).until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("/html/body/div[3]/div/div/div[1]/div/span/p")));
        base.clickXPath("/html/body/div[3]/div/div/div[1]/div/ul/li[3]/a");
        base.setText("password123",By.id("currentPassword"));
        base.setText("newpassword",By.id("password"));
        base.setText("newpassword",By.id("checkPassword"));
        base.clickId("submitChangePassword");
        assertTrue(base.isDisplayed(By.xpath("//*[@id=\"store.success\"]")));
    }
    //SHOPTC52
    //Cambiar contraseña con una contraseña actual incorrecta en una cuenta usuario de la página
    //@Ignore
    @Test
    public void pruebaR() throws InterruptedException {
        base.visit("http://localhost:8080/shop/customer/customLogon.html");
        Thread.sleep(1000);
        base.setText(correoUser,By.id("signin_userName"));
        base.setText("newpassword",By.id("signin_password"));
        base.clickId("genericLogin-button");
        new WebDriverWait(base.getDriver(), 20).until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("/html/body/div[3]/div/div/div[1]/div/span/p")));
        base.clickXPath("/html/body/div[3]/div/div/div[1]/div/ul/li[3]/a");
        base.setText("newpassword",By.id("currentPassword"));
        base.setText("password123",By.id("password"));
        base.setText("password321",By.id("checkPassword"));
        base.clickId("submitChangePassword");
        assertFalse(base.findElement(By.id("submitChangePassword")).isEnabled());

    }
    //SHOPTC53
    //Cambiar contraseña con una nueva contraseña incorrecta(Tamaño menor a 6 caracteres)en una cuenta usuario de la página
    //@Ignore
    @Test
    public void pruebaS() throws InterruptedException {
        base.visit("http://localhost:8080/shop/customer/customLogon.html");
        Thread.sleep(1000);
        base.setText(correoUser,By.id("signin_userName"));
        base.setText("newpassword",By.id("signin_password"));
        base.clickId("genericLogin-button");
        new WebDriverWait(base.getDriver(), 20).until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("/html/body/div[3]/div/div/div[1]/div/span/p")));
        base.clickXPath("/html/body/div[3]/div/div/div[1]/div/ul/li[3]/a");
        base.setText("newpassword",By.id("currentPassword"));
        base.setText("pass",By.id("password"));
        base.setText("pass",By.id("checkPassword"));
        base.clickId("submitChangePassword");
        assertFalse(base.findElement(By.id("submitChangePassword")).isEnabled());

    }
    //SHOPTC54
    //Cerrar sesión como usuario
    //@Ignore
    @Test
    public void pruebaT() throws InterruptedException {
        base.visit("http://localhost:8080/shop/customer/customLogon.html");
        Thread.sleep(1000);
        base.setText(correoUser,By.id("signin_userName"));
        base.setText("newpassword",By.id("signin_password"));
        base.clickId("genericLogin-button");
        new WebDriverWait(base.getDriver(), 20).until(ExpectedConditions.presenceOfElementLocated
                (By.xpath("/html/body/div[3]/div/div/div[1]/div/span/p")));
        base.clickXPath("/html/body/div[3]/div/div/div[1]/div/ul/li[4]/a");
        assertTrue(base.isDisplayed(By.xpath("/html/body/nav[1]/div/ul[2]/li[2]/a/span")));

    }
    @After
    public void close(){
        base.Quit();
    }

    //Generar correo aleatorio
    private static String randomEmail() {
        return "random-" + UUID.randomUUID().toString() + "@example.com";
    }
}
