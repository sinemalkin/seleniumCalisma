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

import java.io.File;
import java.time.Duration;

public class Test4 {
    WebDriver driver;
    ChromeOptions options;
    /*
    1. Launch browser
    2. Navigate to url 'http://automationexercise.com'
    3. Verify that home page is visible successfully
    4. Click on 'Signup / Login' button
    5. Verify 'Login to your account' is visible
    6. Enter incorrect email address and password
    7. Click 'login' button
    8. Verify error 'Your email or password is incorrect!' is visibl
     */

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        options = new ChromeOptions();
        options.addExtensions(new File("C:\\Program Files\\Google\\Chrome\\Application\\101.0.4951.64\\XPath-Plugin.crx"));
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(ChromeOptions.CAPABILITY, options);
        driver = new ChromeDriver(capabilities);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));


    }
    @After public void tearDown(){
        driver.quit();
    }
    @Test
    public void test() {
        //2. Navigate to url 'http://automationexercise.com'
        driver.get("http://automationexercise.com");
        //3. Verify that home page is visible successfully
        WebElement homePage = driver.findElement(By.xpath("//body"));
        Assert.assertTrue(homePage.isDisplayed());
        //4. Click on 'Signup / Login' button
        driver.findElement(By.xpath("//a[normalize-space()='Signup / Login']")).click();
        //Verify 'Login to your account' is visible
        WebElement loginScreen = driver.findElement(By.xpath("//h2[normalize-space()='Login to your account']"));
        Assert.assertTrue(loginScreen.isDisplayed());
        // Enter incorrect email address and password
        driver.findElement(By.xpath("//input[@data-qa='login-email']")).sendKeys("abc@abc.com");
        driver.findElement(By.xpath("//input[@placeholder='Password']")).sendKeys("1232222");
        //7. Click 'login' button
        driver.findElement(By.xpath("//button[normalize-space()='Login']")).click();
        //8. Verify error 'Your email or password is incorrect!' is visible
        WebElement hata = driver.findElement(By.xpath("//p[normalize-space()='Your email or password is incorrect!']"));
        Assert.assertTrue(hata.isDisplayed());
    }

    }
