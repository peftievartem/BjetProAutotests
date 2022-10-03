package bjetpro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class BaseMultiSessionTest {
    public static ChromeDriver driver;

    private static Properties creds;

    @BeforeAll
    private static void setupDriver() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeAll
    private static void loadCreds() {
        setCreds("app.properties");
    }

    public static void setCreds(String fileName) {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            creds = new Properties();
            creds.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void login() {
        driver.manage().window().setSize(new Dimension(1850, 1053));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get(creds.getProperty("url"));
        clickByText(creds.getProperty("base"));
        sendById("login", creds.getProperty("login"));
        sendById("password", creds.getProperty("password"));
        clickBySelector("button[type='submit']");
    }

    public void clickByText(String linkText) {
        driver.findElement(By.linkText(linkText)).click();
    }

    public void clickBySelector(String linkSelector) {
        driver.findElement(By.cssSelector(linkSelector)).click();
    }

    public void sendById(String nameId, String linkText) {
        driver.findElement(By.id(nameId)).sendKeys(linkText);
    }

    public void sendBySelector(String nameSelector, String linkText) {
        driver.findElement(By.cssSelector(nameSelector)).sendKeys(linkText);
    }

    public String getValueBySelector(String nameSelector) {
        return driver.findElement(By.cssSelector(nameSelector)).getAttribute("value");

    }

    @BeforeEach
    public void connectBase() {
        driver = new ChromeDriver();

        login();

//        Shared a = new Shared(driver);
//        a.login(getCreds());
    }

    @AfterEach
    public void quitBase() {
        driver.quit();
    }

}
