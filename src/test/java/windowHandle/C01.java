package windowHandle;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class C01 {
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
        driver.quit();
    }
    @Test
    public void test01() throws InterruptedException {
        // 1- amazon anasayfaya gidelim
        driver.get("https://www.amazon.com");
        String amazonHandle=driver.getWindowHandle();


        // 2- url'in  amazon icerdigini test edelim
        String amazonUrl=driver.getCurrentUrl();
        String expectedUrl="amazon";
        Assert.assertTrue(amazonUrl.contains(expectedUrl));
        // 3- yeni bir pencere acip bestbuy ana sayfaya gidelim
        driver.switchTo().newWindow(WindowType.WINDOW);
        driver.get("https://www.bestbuy.com");
        String bestBuyHandle=driver.getWindowHandle();


        // 4- title'in Best Buy icerdigini test edelim
        String bestbuyTitle=driver.getTitle();
        String expectedTitle="Best Buy";
        Assert.assertTrue(bestbuyTitle.contains(expectedTitle));


        // 5- ilk sayfaya donup sayfada java aratalim
        driver.switchTo().window(amazonHandle);
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys("java"+Keys.ENTER);

        // 6- arama sonuclarinin Java icerdigini test edelim
        WebElement aramaSonuc=driver.findElement(By.xpath("//div[@class='a-section a-spacing-small a-spacing-top-small']"));
        String aramaSonucStr=aramaSonuc.getText();
        Assert.assertTrue(aramaSonucStr.contains("java"));

        // 7- yeniden bestbuy'in acik oldugu sayfaya gidelim
        driver.switchTo().window(bestBuyHandle);

        // 8- logonun gorundugunu test edelim
        WebElement logo=driver.findElement(By.xpath("(//img[@alt='Best Buy Logo'])[1]"));
        Assert.assertTrue(logo.isDisplayed());

    }
}
