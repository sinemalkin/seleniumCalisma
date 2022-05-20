package calisma1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class BestBuyAssertions {
    static WebDriver driver;
    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @AfterClass
    public static void tearDown(){

    }
    @Test
    public void test(){
        //https://www.bestbuy.com/ Adresine gidin farkli test method’lari olusturarak asagidaki
        //    testleri yapin
        driver.get("https://www.bestbuy.com/");
    }
    @Test
    public void test1(){
        //○ Sayfa URL’inin https://www.bestbuy.com/ ‘a esit oldugunu test edin
        String expectedUrl="https://www.bestbuy.com/";
        String actualUrl= driver.getCurrentUrl();
        Assert.assertEquals(expectedUrl,actualUrl);
    }
    @Test
    public void test2(){
        //titleTest => Sayfa başlığının “Rest” içermediğini(contains) test edin
        Assert.assertFalse(driver.getTitle().contains("Rest"));
    }
    @Test
    public void test3(){
       //logoTest => BestBuy logosunun görüntülendigini test edin
        WebElement logoTest=driver.findElement(By.xpath("(//img[@class='logo'])[1]"));
        Assert.assertTrue(logoTest.isDisplayed());

    }
    @Test
    public void test4(){
        //logoTest => BestBuy logosunun görüntülendigini test edin
        WebElement logoTest=driver.findElement(By.xpath("(//img[@class='logo'])[1]"));
        Assert.assertTrue(logoTest.isDisplayed());

    }


}
