package bjetpro;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Properties;

public class Shared {

    private final Properties creds;

    private ConnectionDriver driver;

    public Properties getCreds() {
        return creds;
    }

        public Shared(ConnectionDriver driver){
            this.driver = driver;
            this.creds = new Credentials("app.properties").getCreds();

        }
    public void login(String login, String password) {
            driver.get(creds.getProperty("url"));
            driver.findElement(By.linkText(creds.getProperty("base"))).click();
            driver.findElement(By.id("login")).sendKeys(login);
            driver.findElement(By.id("password")).sendKeys(password);
            driver.findElement(By.cssSelector("button[type='submit']")).click();
        }

    }
