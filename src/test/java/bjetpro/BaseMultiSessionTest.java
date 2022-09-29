package bjetpro;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.io.IOException;

public abstract class BaseMultiSessionTest {
    public static ConnectionDriver driver;

    @BeforeEach
    public void createDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ConnectionDriver();

        Shared a = new Shared(driver);
        a.login(a.getCreds().getProperty("login"), a.getCreds().getProperty("password"));
    }

    @AfterEach
    public void quit() {
        driver.quit();
    }

}
