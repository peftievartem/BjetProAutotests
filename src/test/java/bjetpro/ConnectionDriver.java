package bjetpro;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.IOException;
import java.util.concurrent.TimeUnit;


public class ConnectionDriver extends ChromeDriver {
    static public Credentials creds;

    static {
        try {
            creds = new Credentials("app.properties");
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    public ConnectionDriver(){
        this.get(creds.getProperty("url"));
        this.manage().window().setSize(new Dimension(1850, 1053));
        this.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        login();

    }
    private void login() {
        this.findElement(By.linkText(creds.getProperty("base"))).click();
        this.findElement(By.id("login")).sendKeys(creds.getProperty("login"));
        this.findElement(By.id("password")).sendKeys(creds.getProperty("password"));
        this.findElement(By.cssSelector("button[type='submit']")).click();
    }
}
