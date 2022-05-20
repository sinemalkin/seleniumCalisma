package exercise;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Q1 {
    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @After
    public void tearDown(){
       // driver.quit();
    }
    @Test
    public void test01(){
        //Launch browser
        //2. Navigate to url 'http://automationexercise.com/'
        driver.get("http://automationexercise.com/");
        //3. Verify that home page is visible successfully
        WebElement anaSayfaE=driver.findElement(By.xpath("//html[@lang='en']"));
        Assert.assertTrue(anaSayfaE.isDisplayed());
        //4. Click on 'Signup / Login' button
        driver.findElement(By.xpath("//a[@href='/login']")).click();
        //5. Verify 'Login to your account' is visible
        Assert.assertTrue(driver.findElement(By.xpath("//*[text()='Login to your account']")).isDisplayed());
        //6. Enter incorrect email address and password
       driver.findElement(By.xpath("(//input[@placeholder='Email Address'])[1]")).sendKeys("sinem@gmail.com");

        driver.findElement(By.xpath("(//input[@placeholder='Password'])[1]")).sendKeys("1234");

        //7. Click 'login' button
        driver.findElement(By.xpath("(//button[@class='btn btn-default'])[1]")).click();
        //8. Verify error 'Your email or password is incorrect!' is visible
        String text=driver.findElement(By.xpath("//*[text()='Your email or password is incorrect!']")).getText();
        String expected="Your email or password is incorrect!";
        Assert.assertEquals(expected,text);
    }
}
