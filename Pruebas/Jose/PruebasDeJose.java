import static org.junit.Assert.*;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;

public class PruebasJose {
	private WebDriver driver;
	
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "/home/jose/eclipse-workspace/Automatizacion/src/main/resources/chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("localhost:8080");
	}

	@Test
	public void SHOPTC01() {
		WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"searchField\"]"));
		searchInput.sendKeys("Vintage");
		WebElement searchButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form[1]/div/div[2]/input"));
		searchButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement resultText = driver.findElement(By.xpath("//*[@id=\"products-qty\"]"));
		String results = resultText.getText();
		boolean flag = results.equals("0 artículo (s) encontrado (s)");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC02() {
		WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"searchField\"]"));
		searchInput.sendKeys("Vintage Laptop Bag");
		WebElement searchButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form[1]/div/div[2]/input"));
		searchButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement resultText = driver.findElement(By.xpath("//*[@id=\"products-qty\"]"));
		String results = resultText.getText();
		boolean flag = results.equals("0 artículo (s) encontrado (s)");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC03() {
		WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"searchField\"]"));
		searchInput.sendKeys("ARTICULO NO EXISTENTE");
		WebElement searchButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/div[2]/form[1]/div/div[2]/input"));
		searchButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement resultText = driver.findElement(By.xpath("//*[@id=\"products-qty\"]"));
		String results = resultText.getText();
		boolean flag = results.equals("0 artículo (s) encontrado (s)");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC04() throws NumberFormatException{
		WebElement itemsInCart = driver.findElement(By.xpath("//*[@id=\"miniCartSummary\"]"));
		int items = Integer.parseInt(itemsInCart.getText());
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		boolean flag = items + 1 == Integer.parseInt(itemsInCart.getText());
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC05(){
		WebElement photo = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[1]/a"));
		photo.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String url = driver.getCurrentUrl();
		boolean flag = url.contains("/shop/product/");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC06() {
		Actions act = new Actions(driver);
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement dropdown = driver.findElement(By.xpath("/html/body/nav[1]/div/ul[2]/li[3]/a"));
		act.moveToElement(dropdown);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement image = driver.findElement(By.xpath("/html/body/nav[1]/div/ul[2]/li[3]/ul/span/li[1]/div/ol/li/div[1]/a/img"));
		String scr = image.getAttribute("src");
		boolean flag = scr.isEmpty();
		driver.close();
		assertFalse(flag);
	}

	@Test
	public void SHOPTC07() {
		Actions act = new Actions(driver);
		WebElement dropdown = driver.findElement(By.xpath("/html/body/nav[1]/div/ul[2]/li[3]/a"));
		act.moveToElement(dropdown);
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement noItems = driver.findElement(By.xpath("/html/body/nav[1]/div/ul[2]/li[3]/ul/span/li/div/ol/h5"));
		driver.close();
		assertNotNull(noItems);
	}
	
	@Test
	public void SHOPTC09() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement quantity = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[1]/table/tbody/tr/td[2]/input"));
		quantity.sendKeys(Keys.UP);
		WebElement refreshCart = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[2]/div[1]/div[1]/a[1]"));
		refreshCart.click();
		String text = quantity.getAttribute("value");
		boolean flag = text.equals("2");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC10() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement quantity = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[1]/table/tbody/tr/td[2]/input"));
		quantity.sendKeys(Keys.DOWN);
		WebElement refreshCart = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[2]/div[1]/div[1]/a[1]"));
		refreshCart.click();
		String text = quantity.getAttribute("value");
		boolean flag = text.equals("1");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC11() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement quantity = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[1]/table/tbody/tr/td[2]/input"));
		quantity.clear();
		quantity.sendKeys("5");
		WebElement refreshCart = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[2]/div[1]/div[1]/a[1]"));
		refreshCart.click();
		String text = quantity.getAttribute("value");
		boolean flag = text.equals("5");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC12() {
		WebElement addToCartButton1 = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton1.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement addToCartButton2 = driver.findElement(By.xpath("/html/body/section/div/div/div[2]/div[2]/div[2]/div/a"));
		addToCartButton2.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement removeButton = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[1]/table/tbody/tr/td[5]/div/a/i"));
		removeButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String url = driver.getCurrentUrl();
		boolean flag = url.endsWith("shop/cart/shoppingCart.html");
		driver.close();
		assertTrue(flag);
	}
	
	@Test
	public void SHOPTC13() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement removeButton = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[1]/table/tbody/tr/td[5]/div/a/i"));
		removeButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String url = driver.getCurrentUrl();
		boolean flag = url.endsWith("/shop");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC14() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement checkoutButton = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[2]/div[2]/div[2]/a"));
		checkoutButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		String url = driver.getCurrentUrl();
		boolean flag = url.endsWith("/shop/order/checkout.html");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC15() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement checkoutButton = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[2]/div[2]/div[2]/a"));
		checkoutButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement alert = driver.findElement(By.xpath("/html/body/div[5]/div/div/form/div[2]/div/div[2]/div[2]/strong/font"));
		String message = alert.getText();
		boolean flag = message.equals("El nombre es obligatorio");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC16() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement checkoutButton = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[2]/div[2]/div[2]/a"));
		checkoutButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement name = driver.findElement(By.xpath("//*[@id=\"customer.firstName\"]"));
		name.sendKeys("John");
		WebElement alert = driver.findElement(By.xpath("/html/body/div[5]/div/div/form/div[2]/div/div[2]/div[2]/strong/font"));
		String message = alert.getText();
		boolean flag = message.equals("El apellido es obligatorio");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC17() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement checkoutButton = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[2]/div[2]/div[2]/a"));
		checkoutButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement name = driver.findElement(By.xpath("//*[@id=\"customer.firstName\"]"));
		name.sendKeys("John");
		WebElement lastName = driver.findElement(By.xpath("//*[@id=\"customer.lastName\"]"));
		lastName.sendKeys("Doe");
		WebElement alert = driver.findElement(By.xpath("/html/body/div[5]/div/div/form/div[2]/div/div[2]/div[2]/strong/font"));
		String message = alert.getText();
		boolean flag = message.equals("Se requiere la dirección de la calle");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC18() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement checkoutButton = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[2]/div[2]/div[2]/a"));
		checkoutButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement name = driver.findElement(By.xpath("//*[@id=\"customer.firstName\"]"));
		name.sendKeys("John");
		WebElement lastName = driver.findElement(By.xpath("//*[@id=\"customer.lastName\"]"));
		lastName.sendKeys("Doe");
		WebElement address = driver.findElement(By.xpath("//*[@id=\"customer.billing.address\"]"));
		address.sendKeys("Example St. 123");
		WebElement alert = driver.findElement(By.xpath("/html/body/div[5]/div/div/form/div[2]/div/div[2]/div[2]/strong/font"));
		String message = alert.getText();
		boolean flag = message.equals("Se requiere la ciudad");
		driver.close();
		assertTrue(flag);
	}

	@Test
	public void SHOPTC19() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement checkoutButton = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[2]/div[2]/div[2]/a"));
		checkoutButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement name = driver.findElement(By.xpath("//*[@id=\"customer.firstName\"]"));
		name.sendKeys("John");
		WebElement lastName = driver.findElement(By.xpath("//*[@id=\"customer.lastName\"]"));
		lastName.sendKeys("Doe");
		WebElement address = driver.findElement(By.xpath("//*[@id=\"customer.billing.address\"]"));
		address.sendKeys("Example St. 123");
		WebElement city = driver.findElement(By.xpath("//*[@id=\"customer.billing.city\"]"));
		city.sendKeys("TestCity");
		WebElement alert = driver.findElement(By.xpath("/html/body/div[5]/div/div/form/div[2]/div/div[2]/div[2]/strong/font"));
		String message = alert.getText();
		boolean flag = message.equals("Se requiere código postal");
		driver.close();
		assertTrue(flag);
	}
	
	@Test
	public void SHOPTC20() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement checkoutButton = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[2]/div[2]/div[2]/a"));
		checkoutButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement name = driver.findElement(By.xpath("//*[@id=\"customer.firstName\"]"));
		name.sendKeys("John");
		WebElement lastName = driver.findElement(By.xpath("//*[@id=\"customer.lastName\"]"));
		lastName.sendKeys("Doe");
		WebElement address = driver.findElement(By.xpath("//*[@id=\"customer.billing.address\"]"));
		address.sendKeys("Example St. 123");
		WebElement city = driver.findElement(By.xpath("//*[@id=\"customer.billing.city\"]"));
		city.sendKeys("TestCity");
		WebElement zipcode = driver.findElement(By.xpath("//*[@id=\"billingPostalCode\"]"));
		zipcode.sendKeys("123321");
		WebElement alert = driver.findElement(By.xpath("/html/body/div[5]/div/div/form/div[2]/div/div[2]/div[2]/strong/font"));
		String message = alert.getText();
		boolean flag = message.equals("Se requiere dirección de correo electrónico");
		driver.close();
		assertTrue(flag);
	}
	
	@Test
	public void SHOPTC21() {
		WebElement addToCartButton = driver.findElement(By.xpath("/html/body/section/div/div/div[1]/div[2]/div[2]/div/a"));
		addToCartButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		driver.navigate().to("localhost:8080/shop/cart/shoppingCart.html");
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement checkoutButton = driver.findElement(By.xpath("/html/body/div[5]/div/div/div/div/div/div[2]/div[2]/div[2]/a"));
		checkoutButton.click();
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		WebElement name = driver.findElement(By.xpath("//*[@id=\"customer.firstName\"]"));
		name.sendKeys("John");
		WebElement lastName = driver.findElement(By.xpath("//*[@id=\"customer.lastName\"]"));
		lastName.sendKeys("Doe");
		WebElement address = driver.findElement(By.xpath("//*[@id=\"customer.billing.address\"]"));
		address.sendKeys("Example St. 123");
		WebElement city = driver.findElement(By.xpath("//*[@id=\"customer.billing.city\"]"));
		city.sendKeys("TestCity");
		WebElement zipcode = driver.findElement(By.xpath("//*[@id=\"billingPostalCode\"]"));
		zipcode.sendKeys("123321");
		WebElement email = driver.findElement(By.xpath("//*[@id=\"customer.emailAddress\"]"));
		email.sendKeys("example@test.case");
		WebElement alert = driver.findElement(By.xpath("/html/body/div[5]/div/div/form/div[2]/div/div[2]/div[2]/strong/font"));
		String message = alert.getText();
		boolean flag = message.equals("Se requiere el número de teléfono");
		driver.close();
		assertTrue(flag);
	}
}
