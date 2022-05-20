package practice;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.locators.RelativeLocator;

import java.time.Duration;

public class Q02 {
    /*
    ...Exercise2...
    http://www.bestbuy.com 'a gidin,
    Sayfa basliginin "Best" icerdigini(contains) dogrulayin
    Ayrica Relative Locator kullanarak;
        logoTest => BestBuy logosunun gorunutulenip goruntulenmedigini dogrulayin
        mexicoLinkTest => Linkin gorunutulenip goruntulenmedigini dogrulayin
 */

    WebDriver driver;
    @Before
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get(" http://www.bestbuy.com");

    }
    @Test
    public void test01(){

        Assert.assertTrue(driver.getTitle().contains("Best"));
    }
    @Test
    public void test02(){

        WebElement helloTextElement= driver.findElement(By.xpath("//div[@class ='heading']"));
        WebElement logoBestBuy=driver.findElement(RelativeLocator.with(By.tagName("img")).above(helloTextElement));
        Assert.assertTrue(logoBestBuy.isDisplayed());
    }
    @Test
    public void test03(){
        WebElement mecsicoLink= driver.findElement(By.xpath("(//img[@alt='Mexico'])[1]"));
        Assert.assertTrue(mecsicoLink.isDisplayed());
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
