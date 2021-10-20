package org.example;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * Unit test for simple App.
 */
public class AppTest 
{
    WebDriver driver;
    WebDriverWait wait;
    @Before
    public void setUp()
    {
        System.setProperty("webdriver.chrome.driver","C:\\Users\\testinium\\IdeaProjects\\trendyolDeneme\\resources\\chromedriver.exe");
        ChromeOptions options=new ChromeOptions();
        options.addArguments("start-maximized", "disable-popup-blocking");//tam ekran olarak yapmak
        driver=new ChromeDriver(options);
        driver.get("https://www.trendyol.com/");
    }

    @Test
    public void actionsTest() throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("div[class='account-nav-item']")))
                .click()
                .build()
                .perform();
        //findElement(By.cssSelector("div[class='login-button']")).click();
        TimeUnit.SECONDS.sleep(5);

    }
    @Test
    public void login() throws InterruptedException {
        //actionsTest();
        WebElement element = driver.findElement(By.xpath("//img[@alt=\"Male\"]"));
        element.click();
        driver.findElement(By.className("account-user")).click();
        WebElement email= driver.findElement(By.id("login-email"));
        email.sendKeys("mahmutbalci.3323@gmail.com");
        WebElement password= driver.findElement(By.id("login-password-input"));
        password.sendKeys("Mahmut.123");
        driver.findElement(By.className("submit")).click();
    }

    @Test
    public void search() throws InterruptedException {
        login();
        WebElement searchBox = driver.findElement(By.className("search-box"));
        searchBox.sendKeys("Kazak" + Keys.ENTER);
        driver.findElement(By.cssSelector("div[class='overlay']")).click();
        WebElement chooseGender = driver.findElement(By.xpath("(//div[@class='fltr-item-wrppr'])[1]"));
        chooseGender.click();
        List<WebElement> imageList = driver.findElements(By.className("fvrt-btn-wrppr"));
        System.out.println(imageList.size());
        String price = driver.findElement(By.cssSelector("div[class='prc-box-sllng']")).getText();
        System.out.println("Price :"+ price);
        WebElement item = imageList.get(9);

        item.click();

    }
    @Test
    public void tenthElement() throws InterruptedException {
        login();
        List<WebElement> imageList = driver.findElements(By.className("p-card-wrppr"));
        System.out.println(imageList.size());
        WebElement item = imageList.get(9);
        TimeUnit.SECONDS.sleep(3);
        item.click();
        TimeUnit.SECONDS.sleep(3);

    }

    @Test
    public void addToBasket() throws InterruptedException {
        tenthElement();
        String currentWindow = driver.getWindowHandle();
        //findElement(By.cssSelector("button[class='add-to-basket']")).click();
        wait.until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> windowHandles = driver.getWindowHandles();

        for (String window :
                windowHandles) {
            if (!currentWindow.equals(window)) {
                driver.switchTo().window(window);

            }
        }
        TimeUnit.SECONDS.sleep(5);
        driver.findElement(By.cssSelector("button[class='add-to-basket'")).click();
        TimeUnit.SECONDS.sleep(5);
        driver.switchTo().window(currentWindow);

    }





    @After
    public void tearDown(){
       driver.quit();
    }
}
