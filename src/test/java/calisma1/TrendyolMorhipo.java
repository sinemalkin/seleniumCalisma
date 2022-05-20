package calisma1;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class TrendyolMorhipo {
    //- google'a git
    // - trendyolu ara
    // - trendyol linkini bulup siteye git
    // - avize arat
    // - toplam ürün sayısını al
    // - yeni sekmede morhipoya git
    // - avize arat
    // - toplam ürün sayısını al
    // - iki sitedeki topla avize sayısını karşılaştır
    // - ürün sayısı fazla olan siteyi kapat
    static WebDriver driver;
    static int sonucTrendyol;
    static int sonucorhipo;
    static String trendyolHandle;
    static String morhipoHandle;
    @BeforeClass
    public static void setUp(){
        WebDriverManager.chromedriver().setup();
        driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(15));
    }
    @AfterClass
    public static void tearDown(){
        driver.quit();
    }
    @Test
    public void  test1(){
        //- google'a git
        // - trendyolu ara
        // - trendyol linkini bulup siteye git
        // - avize arat
        // - toplam ürün sayısını al
        driver.get("https://www.google.com");
        driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("trendyol"+ Keys.ENTER);
        driver.findElement(By.xpath("(//*[@class='LC20lb MBeuO DKV0Md'])[1]")).click();
        driver.findElement(By.id("Combined-Shape")).click();
        driver.findElement(By.xpath("//input[@class='search-box']")).sendKeys("avize"+Keys.ENTER);
        trendyolHandle=driver.getWindowHandle();
        String trendolToplam=driver.findElement(By.xpath("//*[@class='dscrptn']")).getText();
        String trendyolUrun=Arrays.stream(trendolToplam.split(" ")).collect(Collectors.toList()).get(3).replaceAll("\\D","");
        sonucTrendyol= Integer.parseInt(trendyolUrun);
    }
    @Test
    public  void test2(){
        // - yeni sekmede morhipoya git
        // - avize arat
        // - toplam ürün sayısını al
        driver.switchTo().newWindow(WindowType.TAB).get("https://www.morhipo.com");
        driver.findElement(By.xpath("//input[@id='pw-search-input']")).sendKeys("avize"+Keys.ENTER);
        morhipoHandle=driver.getWindowHandle();
        WebElement morhipoToplam=driver.findElement(By.xpath("//span[@class='bigger']"));
        String morhipoUrun=Arrays.stream(morhipoToplam.getText().split(" ")).collect(Collectors.toList()).get(0).replaceAll("\\D","");
        sonucorhipo= Integer.parseInt(morhipoUrun);
    }

    @Test
    public void test3() throws InterruptedException {
        // - iki sitedeki topla avize sayısını karşılaştır
        // - ürün sayısı fazla olan siteyi kapat
        Assert.assertTrue(sonucTrendyol>sonucorhipo);
        driver.switchTo().window(trendyolHandle);
        driver.close();
        Thread.sleep(1500);
    }

}
