package calisma1;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.List;

public class Test2 {
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
    public void test2() throws InterruptedException {
        //1. “https://www.saucedemo.com” Adresine gidin
        driver.get("https://www.saucedemo.com");
        //2. Username kutusuna “standard_user” yazdirin
        driver.findElement(By.xpath("//input[@id='user-name']")).sendKeys("standard_user");
        //3. Password kutusuna “secret_sauce” yazdirin
        driver.findElement(By.xpath("//input[@id='password']")).sendKeys("secret_sauce");
        //4. Login tusuna basin
        driver.findElement(By.xpath("//input[@id='login-button']")).click();
        //5. Ilk urunun ismini kaydedin ve bu urunun sayfasina gidin
        List<WebElement> urunIsmi=driver.findElements(By.xpath("//div[@class='inventory_item_name']"));
        String ilkUrun=urunIsmi.get(0).getText();
        urunIsmi.get(0).click();
        //6. Add to Cart butonuna basin
        driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-backpack']")).click();
        Thread.sleep(2000);
        //7. Alisveris sepetine tiklayin
        driver.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
        Thread.sleep(2000);
        //8. Sectiginiz urunun basarili olarak sepete eklendigini control edin
        String sepet=driver.findElement(By.xpath("//div[@class='inventory_item_name']")).getText();
        if (ilkUrun.equals(sepet)){
            System.out.println("test passed");
        }else{
            System.out.println("test failed");
        }
        //9. Sayfayi kapatin
    }

}
