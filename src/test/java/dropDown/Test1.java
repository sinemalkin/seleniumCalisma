package dropDown;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;

public class Test1 {
    WebDriver driver;
    /*
    amazona gidip
    dropdown dan books secenegini secip
    java aratalim
    ve arama sonuclarinin java icerdigini test edelim
     */

    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));

    }

    @After
    public void tearDown(){
        //driver.quit();
    }
    @Test
    public void test1(){
        driver.get("https://www.amazon.com");
        WebElement ddm= driver.findElement(By.xpath("//select[@id='searchDropdownBox']"));
        Select select=new Select(ddm);
        select.selectByVisibleText("Books");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("java"+ Keys.ENTER);
        WebElement sonucYazisi= driver.findElement(By.xpath("(//div[@class='a-section a-spacing-small a-spacing-top-small'])[1]"));
        String actual= sonucYazisi.getText();
        String kelime="java";
       Assert.assertTrue(actual.contains(kelime));


    }
}
