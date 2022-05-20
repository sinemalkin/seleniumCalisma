package windowHandle;

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
import java.util.Set;

public class C02 {

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
    public void test01(){
        //Tests package’inda yeni bir class olusturun: WindowHandle2
        //https://the-internet.herokuapp.com/windows adresine gidin.
        driver.get("https://the-internet.herokuapp.com/windows");
        String heroHandle=driver.getWindowHandle();
        //Sayfadaki textin “Opening a new window” olduğunu doğrulayın.
        WebElement textE=driver.findElement(By.xpath("//h3"));
        String text= textE.getText();
        Assert.assertEquals("Opening a new window",text);
        //Sayfa başlığının(title) “The Internet” olduğunu doğrulayın.
        Assert.assertEquals("The Internet",driver.getTitle());
        //Click Here butonuna basın.
        driver.findElement(By.xpath("//*[text()='Click Here']")).click();
        Set<String> handles= driver.getWindowHandles();
        String ikinciSayfa="";

        for (String w:handles
             ) {
            if (!handles.equals(heroHandle)){
                ikinciSayfa=w;
            }
        }
        driver.switchTo().window(ikinciSayfa);


        //Acilan yeni pencerenin sayfa başlığının (title) “New Window” oldugunu dogrulayin.
        Assert.assertEquals("New Window",driver.getTitle());

        //Sayfadaki textin “New Window” olduğunu doğrulayın.
        Assert.assertEquals("New Window", driver.findElement(By.xpath("//h3")).getText());

        //Bir önceki pencereye geri döndükten sonra sayfa başlığının
        //The Internet” olduğunu doğrulayın.

        driver.switchTo().window(heroHandle);
        String heroTitle=driver.getTitle();
        Assert.assertEquals("The Internet",heroTitle);

    }
}
