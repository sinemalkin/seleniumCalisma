package calisma1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

public class Test5 {
    //2. Navigate to url 'http://automationexercise.com'
    //3. Verify that home page is visible successfully
    //4. Scroll down page to bottom
    //5. Verify 'SUBSCRIPTION' is visible
    //6. Scroll up page to top
    //7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
    WebDriver driver;
    ChromeOptions options;
    @Before
    public void setup() {
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addExtensions(new File("C:\\Program Files\\Google\\Chrome\\Application\\101.0.4951.64\\XPath-Plugin.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        //1. Launch browser
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @Test
    public void test() throws AWTException {
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//body"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Scroll down page to bottom
        Robot rbt=new Robot();
        rbt.keyPress(KeyEvent.VK_END);
        //5. Verify 'SUBSCRIPTION' is visible
        WebElement subs=driver.findElement(By.xpath("//h2[normalize-space()='Subscription']"));
        Assert.assertTrue(subs.isDisplayed());
        //6. Scroll up page to top
        driver.findElement(By.xpath("//i[@class='fa fa-angle-up']")).click();
        //7. Verify that page is scrolled up and 'Full-Fledged practice website for Automation Engineers' text is visible on screen
        WebElement full = driver.findElement(By.xpath("//div[@class='item active']//h2[contains(text(),'Full-Fledged practice website for Automation Engin')]"));
        Assert.assertTrue(full.isDisplayed());
    }
    @After
    public void close() {
        driver.close();
    }

}

