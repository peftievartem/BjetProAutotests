package bjetpro;


import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeNameAutoFill extends BaseMultiDriver {

    @Test
    public void autofillNameSurnamePatronymic2() {
//        driver.get(prop.getProperty("url"));

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
    public void autofillNameSurnamePatronymic() {
//        driver.get(prop.getProperty("url"));

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