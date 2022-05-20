package calisma1;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
public class YoutubeSorusu {
    /*
     * https://www.youtube.com adresine gidin
     * Aşağıdaki adları kullanarak 3 test metodu oluşturun ve gerekli testleri yapin
     * titleTest => Sayfa başlığının “YouTube” oldugunu test edin
     * imageTest => YouTube resminin görüntülendiğini (isDisplayed()) test edin
     * Search Box 'in erisilebilir oldugunu test edin (isEnabled())
     * wrongTitleTest => Sayfa basliginin “youtube” olmadigini dogrulayin
     */
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
        driver.close();
    }
    @Test
    public void test1(){
        driver.get("https://www.youtube.com");
    }
    @Test
    public void test2(){
        String expectedTitle="YouTube";
        String actualTitle=driver.getTitle();
        Assert.assertEquals(expectedTitle,actualTitle);
        //(yt-icon[@id='logo-icon'])[1]
    }
    @Test
    public void test3(){
        WebElement imageTest=driver.findElement(By.xpath("(//*[@class='style-scope ytd-logo'])[1]"));
        Assert.assertTrue(imageTest.isDisplayed());
    }
    @Test
    public void test4(){
        WebElement searchBoxTest=driver.findElement(By.xpath("//input[@id='search']"));
        Assert.assertTrue(searchBoxTest.isEnabled());
    }
    @Test
    public void test5(){
        Assert.assertNotEquals("youtube",driver.getTitle());
    }
}
