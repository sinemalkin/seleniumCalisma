package calisma1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class Test1 {
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
       driver.close();
    }
    @Test
    public void test1(){
        //1-C01_TekrarTesti isimli bir class olusturun
        //2- https://www.google.com/ adresine gidin
        driver.get("https://www.google.com/");
        //3- cookies uyarisini kabul ederek kapatin
        //4-Sayfa basliginin “Google” ifadesi icerdigini test edin

        String actualTitle= driver.getTitle();
        String arananKelime="Google";
        if (actualTitle.contains(arananKelime)){
            System.out.println("test passed");
        }else{
            System.out.println("test failed");
        }
        //5- Arama cubuguna “Nutella” yazip aratin
        driver.findElement(By.xpath("//input[@name='q']")).sendKeys("Nutella"+ Keys.ENTER);
        //6-Bulunan sonuc sayisini yazdirin
        String[] sonucText = driver.findElement(By.xpath("//div[@id='result-stats']")).getText().split(" ");
        String sonucSayisi = sonucText[1];
        System.out.println( sonucSayisi);

        //7- sonuc sayisinin 10 milyon’dan fazla oldugunu test edin


        //8-Sayfayi kapatin
    }

}
