package com.qualityassurance.tutorial;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PruebasAutomatizadasPena {
	
	private WebDriver driver;
	By myAccountLinkLocator = By.xpath("//div[@class=\"dropdown dropdown-login dropdown-tab\"]");
	By loginLocator = By.xpath("//a[@class=\"dropdown-item active tr\"]");
	By loginimgLocator = By.xpath("//img[@src =\"https://www.phptravels.net/themes/default/assets/img/sign-bg.png\"]");
	By emailLocator = By.xpath("//input[@name=\"username\"]");
	By passwordLocator = By.xpath("//input[@name=\"password\"]");
	By loginBottomLocator = By.xpath("//*[@id=\"loginfrm\"]/button");
	@Before
	public void setUp() {
		System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://localhost:8080/admin/logon.html");
	}
	
	@Test
	public void adminLogonTest1() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Assert.assertEquals("Store information",driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div[2]/span/p")).getText());
		Thread.sleep(2000);
	}
	
	@Test
	public void adminLogonTest2() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("pablo@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Invalid username or password",driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div")).getText());
		Thread.sleep(2000);
	}
	
	@Test
	public void adminLogonTest3() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("123456");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Invalid username or password",driver.findElement(By.xpath("/html/body/div/div[1]/div[1]/div")).getText());
		Thread.sleep(2000);
	}

	@Test
	public void adminLogonTest4() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("");
		driver.findElement(By.id("password")).sendKeys("");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		Assert.assertEquals("*",driver.findElement(By.xpath("/html/body/div/div[1]/div[2]/div[2]/div/form/div[1]/div/span/font/strong")).getText());
		Thread.sleep(2000);
	}
	
	@Test
	public void createStoreTest1() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[1]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("storename")).sendKeys("Bitstore");
		driver.findElement(By.id("code")).sendKeys("499021");
		driver.findElement(By.id("storephone")).sendKeys("22654549");
		driver.findElement(By.id("storeEmailAddress")).sendKeys("bitStore@shopizer.com");
		driver.findElement(By.id("storeaddress")).sendKeys("main street 2132");
		driver.findElement(By.id("storecity")).sendKeys("Los Angeles");
		driver.findElement(By.id("storepostalcode")).sendKeys("90210");
		driver.findElement(By.id("domainName")).clear();
		driver.findElement(By.id("domainName")).sendKeys("www.bitstore.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/form/div[20]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Request completed with success",driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/form/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createStoreTest2() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[1]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("storename")).sendKeys("Bitstore");
		driver.findElement(By.id("code")).sendKeys("499021");
		driver.findElement(By.id("storephone")).sendKeys("22654549");
		driver.findElement(By.id("storeEmailAddress")).sendKeys("bitStore@shopizer.com");
		driver.findElement(By.id("storeaddress")).sendKeys("main street 2132");
		driver.findElement(By.id("storecity")).sendKeys("Los Angeles");
		driver.findElement(By.id("storepostalcode")).sendKeys("90210");
		driver.findElement(By.id("domainName")).clear();
		driver.findElement(By.id("domainName")).sendKeys("www.bitstore.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/form/div[20]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("An error occurred in this request",driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[2]/h3")).getText());
		Thread.sleep(2000);
		}
	@Test
	public void createStoreTest3() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[1]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("storename")).sendKeys("Bitstore");
		driver.findElement(By.id("code")).sendKeys("577854");
		driver.findElement(By.id("storephone")).sendKeys("22654549");
		driver.findElement(By.id("storeEmailAddress")).sendKeys("bitStore@shopizer.com");
		driver.findElement(By.id("storeaddress")).sendKeys("main street 2132");
		driver.findElement(By.id("storecity")).sendKeys("Los Angeles");
		driver.findElement(By.id("storepostalcode")).sendKeys("90210");
		driver.findElement(By.id("domainName")).clear();
		driver.findElement(By.id("domainName")).sendKeys("www.bitstore.com");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/form/div[20]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Request completed with success",driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/form/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createManufacturerTest1() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[5]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[5]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("code")).sendKeys("QuickSilver");
		driver.findElement(By.id("name0")).sendKeys("quicksilver");
		driver.findElement(By.id("title0")).sendKeys("quicksilver");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/form/div[13]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Request completed with success",driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/form/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createManufacturerTest2() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[5]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[5]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("code")).sendKeys("QuickSilver");
		driver.findElement(By.id("name0")).sendKeys("quicksilver");
		driver.findElement(By.id("title0")).sendKeys("quicksilver");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/form/div[13]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("An error occurred in this request",driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[2]/h3")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createManufacturerTest3() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[5]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[5]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("code")).sendKeys("QuickSilver2");
		driver.findElement(By.id("name0")).sendKeys("quicksilver");
		driver.findElement(By.id("title0")).sendKeys("quicksilver");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/form/div[13]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Request completed with success",driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/form/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createCatalogueTest1() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[2]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("category.code")).sendKeys("tshirt");
		driver.findElement(By.id("name0")).sendKeys("T-Shirt");
		driver.findElement(By.id("categoryHighlight0")).sendKeys("tshirt");
		driver.findElement(By.id("descriptions0.metatagTitle")).sendKeys("t-shirt");
		driver.findElement(By.id("descriptions0.metatagKeywords")).sendKeys("T-Shirts");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[20]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Request completed with success",driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createCatalogueTest2() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[2]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("category.code")).sendKeys("tshirt");
		driver.findElement(By.id("name0")).sendKeys("T-Shirt");
		driver.findElement(By.id("categoryHighlight0")).sendKeys("tshirt");
		driver.findElement(By.id("descriptions0.metatagTitle")).sendKeys("t-shirt");
		driver.findElement(By.id("descriptions0.metatagKeywords")).sendKeys("T-Shirts");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[20]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("An error occurred in this request",driver.findElement(By.xpath("/html/body/div/table/tbody/tr[1]/td[2]/h3")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createCatalogueTest3() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[2]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("category.code")).sendKeys("tshirtPremium");
		driver.findElement(By.id("name0")).sendKeys("T-Shirt");
		driver.findElement(By.id("categoryHighlight0")).sendKeys("tshirt");
		driver.findElement(By.id("descriptions0.metatagTitle")).sendKeys("t-shirt");
		driver.findElement(By.id("descriptions0.metatagKeywords")).sendKeys("T-Shirts");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[20]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Request completed with success",driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createProductGroupTest1() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[4]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("code")).sendKeys("Shoes");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/form/div[3]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Request completed with success",driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/form/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createProductGroupTest2() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[4]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("code")).sendKeys("Shoes");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/form/div[3]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("The group OPT01 already exists",driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/div/div/div/form/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createProductTest1() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("sku")).sendKeys("SK0023");
		driver.findElement(By.id("refSku")).sendKeys("SK0023");
		driver.findElement(By.id("name0")).sendKeys("Retro Shoes");
		driver.findElement(By.id("descriptions0.metatagTitle")).sendKeys("retro shoes");
		driver.findElement(By.id("productPriceAmount")).sendKeys("30");
		driver.findElement(By.id("quantity")).sendKeys("10");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[34]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Request completed with success",driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form[1]/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createProductTest2() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("sku")).sendKeys("SK0023");
		driver.findElement(By.id("refSku")).sendKeys("SK0023");
		driver.findElement(By.id("name0")).sendKeys("Retro Shoes");
		driver.findElement(By.id("descriptions0.metatagTitle")).sendKeys("retro shoes");
		driver.findElement(By.id("productPriceAmount")).sendKeys("30");
		driver.findElement(By.id("quantity")).sendKeys("10");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[34]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("sku already exists for another product",driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createProductTest3() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/ul/li[1]/ul/li[1]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("sku")).sendKeys("SK0024");
		driver.findElement(By.id("refSku")).sendKeys("SK0024");
		driver.findElement(By.id("name0")).sendKeys("Retro Shoes");
		driver.findElement(By.id("descriptions0.metatagTitle")).sendKeys("retro shoes");
		driver.findElement(By.id("productPriceAmount")).sendKeys("30");
		driver.findElement(By.id("quantity")).sendKeys("10");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[34]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Request completed with success",driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form[1]/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createProductOptionTest1() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[3]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[3]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("code")).sendKeys("OPT01");
		driver.findElement(By.id("name0")).sendKeys("Opcion1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[6]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("Request completed with success",driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@Test
	public void createProductOptionTest2() throws InterruptedException {
		driver.findElement(By.id("username")).sendKeys("admin@shopizer.com");
		driver.findElement(By.id("password")).sendKeys("password");
		Thread.sleep(2000);
		driver.findElement(By.id("formSubmitButton")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[1]/ul/li[4]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[3]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div[1]/div[3]/div/div[2]/div/ul/li[3]/ul/li[2]/a")).click();
		Thread.sleep(2000);
		driver.findElement(By.id("code")).sendKeys("OPT01");
		driver.findElement(By.id("name0")).sendKeys("Opcion1");
		Thread.sleep(2000);
		driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[6]/div/button")).click();
		Thread.sleep(2000);
		Assert.assertEquals("This code already exist",driver.findElement(By.xpath("/html/body/div/div[3]/div/div[2]/div/div/div/div/form/div[1]")).getText());
		Thread.sleep(2000);
	
	}
	
	@After
	public void tearDown() throws InterruptedException {
		
		driver.quit();
	}
	
	
}
