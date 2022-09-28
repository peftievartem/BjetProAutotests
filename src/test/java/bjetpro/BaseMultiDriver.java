package bjetpro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public class BaseMultiDriver {
    static public Credentials prop;
    public static ConnectionDriver driver;

    static {
        try {
            prop = new Credentials("app.properties");
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    @BeforeEach
    public void createDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ConnectionDriver();
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

}
