package alerts_iframe;

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

import java.time.Duration;

public class Q2_iframe {
    //● Bir class olusturun: IframeTest
    //  ● https://the-internet.herokuapp.com/iframe adresine gidin.
    //  ● Bir metod olusturun: iframeTest
    //     ○ “An IFrame containing….” textinin erisilebilir oldugunu test edin
    //     ve  konsolda    yazdirin.
    //     ○ Text Box’a “Merhaba Dunya!” yazin.
    //textbox'i dogru olarak locate etmemize ragmen driver bulamadi
    //bunun uzerine HTML kodlari inceleyince
    //textbox'in aslinda bir IFrame icerisinde oldugunu gorduk
    //bu durumda once iframe'i locate edip
    //switchTo() ile o iFrame'e gecmeliyiz
    //     ○ TextBox’in altinda bulunan “Elemental Selenium”
    //     linkinin textinin gorunur oldugunu  dogrulayin ve  konsolda yazdirin.
    //link yazi elementini dogru locate etmemize ragmen yazdirmadi
    //cunku yukarida iFrame'e gecis yapmistik
    //once oradan cikmamiz lazim
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
    public void iframeTest() {
        driver.get("https://the-internet.herokuapp.com/iframe");
        WebElement mesajE=driver.findElement(By.xpath("//*[text()='An iFrame containing the TinyMCE WYSIWYG Editor']"));
        Assert.assertTrue(mesajE.isEnabled());
        System.out.println(mesajE.getText());
        WebElement iFrameElementi=driver.findElement(By.xpath("//iframe[@id='mce_0_ifr']"));
        driver.switchTo().frame(iFrameElementi);

        WebElement mesajKutusu=driver.findElement(By.xpath("//body[@id='tinymce']"));
        mesajKutusu.clear();
        mesajKutusu.sendKeys("Merhaba Dunya!");

        driver.switchTo().defaultContent();
        WebElement linkTextE= driver.findElement(By.xpath("//*[text()='Elemental Selenium']"));
        Assert.assertTrue(linkTextE.isDisplayed());
        System.out.println(linkTextE.getText());



    }
}
