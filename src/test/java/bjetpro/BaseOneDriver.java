package bjetpro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;

public class BaseOneDriver {
    static public Credentials prop;
    public static ConnectionDriver driver;

    static {
        try {
            prop = new Credentials("app.properties");
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    @BeforeAll
    public static void createDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ConnectionDriver();
    }

    @AfterAll
    public static void quit() {
        driver.quit();
    }

}
