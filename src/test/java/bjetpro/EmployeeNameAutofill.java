package bjetpro;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeNameAutofill {

    static public Properties prop;
    private static WebDriver driver;

    static {
        try {
            prop = readPropertiesFile("app.properties");
        } catch (IOException ex) {
            throw new RuntimeException();
        }
    }

    public static Properties readPropertiesFile(String fileName) throws IOException {
        try (FileInputStream fis = new FileInputStream(fileName)) {
            Properties prop = new Properties();
            prop.load(fis);
            return prop;
        }
    }

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();

        driver.get(prop.getProperty("url"));
        driver.manage().window().setSize(new Dimension(1850, 1053));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        login(prop.getProperty("login"), prop.getProperty("password"));
    }


    @AfterAll
    public static void quit() {
       driver.quit();
    }

    private static void login(String login, String pass) {
        driver.findElement(By.linkText(prop.getProperty("base"))).click();
        driver.findElement(By.id("login")).sendKeys(login);
        driver.findElement(By.id("password")).sendKeys(pass);
        driver.findElement(By.cssSelector("button[type='submit']")).click();
    }


    @Test
    public void autofillNameSurnamePatronymic2() {
        driver.get(prop.getProperty("url"));

        driver.findElement(By.cssSelector(".app-sidebar-menu a[data-menu-xmlid='hr.menu_hr_root']")).click();
        driver.findElement(By.cssSelector(".o-kanban-button-new")).click();

        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("Peftiiev Artem Yurievich");

        driver.findElement(By.cssSelector("input[name='surname']")).click();
        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("aaaaaa");


        String expectedText = driver.findElement(By.cssSelector("input[name='surname']")).getAttribute("value");
        assertEquals(expectedText, "Peftiiev");
        expectedText = driver.findElement(By.cssSelector("input[name='first_name']")).getAttribute("value");
        assertEquals(expectedText, "Artem");
        expectedText = driver.findElement(By.cssSelector("input[name='patronymic']")).getAttribute("value");
        assertEquals(expectedText, "Yurievich");
    }

    @Test
    public void autofillNameSurnamePatronymic(){
        driver.get(prop.getProperty("url"));

        driver.findElement(By.cssSelector(".app-sidebar-menu a[data-menu-xmlid='hr.menu_hr_root']")).click();
        driver.findElement(By.cssSelector(".o-kanban-button-new")).click();

        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("Peftiiev Artem Yurievich");

        driver.findElement(By.cssSelector("input[name='surname']")).click();
        String expectedText = driver.findElement(By.cssSelector("input[name='surname']")).getAttribute("value");
        assertEquals(expectedText, "Peftiiev");
        expectedText = driver.findElement(By.cssSelector("input[name='first_name']")).getAttribute("value");
        assertEquals(expectedText, "Artem");
        expectedText = driver.findElement(By.cssSelector("input[name='patronymic']")).getAttribute("value");
        assertEquals(expectedText, "Yurievich");
    }


}