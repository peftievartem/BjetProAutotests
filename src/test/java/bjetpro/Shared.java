package bjetpro;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class Shared {

    private ChromeDriver driver;

    public Shared(ChromeDriver driver) {
        this.driver = driver;
        window();
    }

    private void window() {
        this.driver.manage().window().setSize(new Dimension(1850, 1053));
        this.driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void login(Properties creds) {
        driver.get(creds.getProperty("url"));
        driver.findElement(By.linkText(creds.getProperty("base"))).click();
        driver.findElement(By.id("login")).sendKeys(creds.getProperty("login"));
        driver.findElement(By.id("password")).sendKeys(creds.getProperty("password"));
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }

}
