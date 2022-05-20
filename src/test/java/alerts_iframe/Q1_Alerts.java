package alerts_iframe;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Q1_Alerts {
    // https://the-internet.herokuapp.com/javascript_alerts adresine gidin.
    //● Bir metod olusturun: acceptAlert
    //      ○ 1. butona tıklayın, uyarıdaki OK butonuna tıklayın ve result mesajının
    //      “You successfully clicked an alert” oldugunu test edin.
    //● Bir metod olusturun: dismissAlert
    //      ○ 2. butona tıklayın, uyarıdaki Cancel butonuna tıklayın ve result mesajının
    //      “successfuly” icermedigini test edin.
    //● Bir metod olusturun: sendKeysAlert
    //      ○ 3. butona tıklayın, uyarıdaki metin kutusuna isminizi yazin,
    //      OK butonuna tıklayın ve result mesajında isminizin görüntülendiğini doğrulayın.

    static WebDriver driver;

    @BeforeClass
    public static void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");

    }

    @AfterClass
    public static void tearDown() {

    }

    @Test
    public void acceptAlert() {
        driver.findElement(By.xpath("//*[text()='Click for JS Alert']")).click();
        driver.switchTo().alert().accept();
        WebElement resultMesajiElementi= driver.findElement(By.xpath("//p[@id='result']"));
        String resultMesaji=resultMesajiElementi.getText();
        String expectedMesaj="You successfully clicked an alert";
        Assert.assertEquals(expectedMesaj,resultMesaji);

    }

    @Test
    public void dismissAlert(){
        driver.findElement(By.xpath("//*[text()='Click for JS Confirm']")).click();
        driver.switchTo().alert().dismiss();
        WebElement resultMesaji2Elementi= driver.findElement(By.xpath("//*[text()='You clicked: Cancel']"));
        String resultMesaji2= resultMesaji2Elementi.getText();
        String expectedMesaj2="successfuly";
        Assert.assertFalse(resultMesaji2.contains(expectedMesaj2));
    }

    @Test
    public void sendKeysAlert(){
        driver.findElement(By.xpath("//*[text()='Click for JS Prompt']")).click();
        driver.switchTo().alert().sendKeys("sinem");
        driver.switchTo().alert().accept();
        WebElement resultMesajE= driver.findElement(By.xpath("//*[text()='You entered: sinem']"));
        String resultMesaj=resultMesajE.getText();
        String expectedMesaj="sinem";
        Assert.assertTrue(resultMesaj.contains(expectedMesaj));
    }
}