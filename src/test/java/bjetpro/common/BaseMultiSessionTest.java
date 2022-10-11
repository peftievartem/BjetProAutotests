package bjetpro.common;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public abstract class BaseMultiSessionTest {
    public static ChromeDriver driver;
    public static WebDriverWait waitDriver;

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
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        driver.get(creds.getProperty("url"));
        click(By.linkText(creds.getProperty("base")));
        sendString(By.id("login"), creds.getProperty("login"));
        sendString(By.id("password"), creds.getProperty("password"));

        click("button[type='submit']");
    }

    public WebElement element(By ele) {
        return driver.findElement(ele);
    }

    public WebElement element(String sel) {
        return driver.findElement(By.cssSelector(sel));
    }

    public void click(By ele) {
        element(ele).click();
    }

    public void click(String sel) {
        element(sel).click();
    }

    public void sendString(By ele, String value) {
        element(ele).sendKeys(value);
    }

    public void sendString(String sel, String value) {
        element(sel).sendKeys(value);
    }

    public String getStringValue(By ele) {
        return element(ele).getAttribute("value");
    }

    public String getStringValue(String sel) {
        return element(sel).getAttribute("value");
    }

    public String getText(By ele) {
        return element(ele).getText();
    }

    public String getText(String sel) {
        return element(sel).getText();
    }

    public void waitOLoading() {
        waitDriver.until(ExpectedConditions.attributeToBe(By.cssSelector(".o_loading"), "style", "display: none;"));
    }

    @BeforeEach
    public void connectBase() {
        driver = new ChromeDriver();
        waitDriver = new WebDriverWait(driver, 10);

        login();
    }

    @AfterEach
    public void quitBase() {
        driver.quit();
    }

}
