import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class Base {

    private WebDriver driver;

    public Base(WebDriver driver){
        this.driver = driver;
    }

    public WebDriver firefoxDriverConnection(){
        System.setProperty("webdriver.gecko.driver","src\\main\\resources\\geckodriver.exe");
        driver = new FirefoxDriver();
        return driver;
    }

    public WebElement findElement(By locator){
        return driver.findElement(locator);
    }

    public WebElement findElementByXpath(String locator){
        return driver.findElement(By.xpath(locator));
    }

    public List<WebElement> findElements(By locator){
        return driver.findElements(locator);
    }

    public String getText(WebElement element){
        return element.getText();
    }

    public String getText(By locator){
        return driver.findElement(locator).getText();
    }

    public void setText(String text, By locator){
        driver.findElement(locator).sendKeys(text);
    }

    public void click(String name){
        driver.findElement(By.className(name)).click();
    }

    public Boolean isDisplayed(By locator){
        try {
            return driver.findElement(locator).isDisplayed();
        }catch(org.openqa.selenium.NoSuchElementException e){
            return false;
        }
    }

    public void visit(String url){
        driver.manage().window().maximize();
        driver.get(url);
    }

    public void submit(By locator){
        driver.findElement(locator).submit();
    }

    public String getTitle(){
        return driver.getTitle();
    }

    public void Quit(){
        driver.quit();
    }

    public void clearText(By locator){
        driver.findElement(locator).clear();
    }
}
