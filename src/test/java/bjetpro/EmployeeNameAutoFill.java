package bjetpro;


import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeNameAutoFill extends BaseMultiDriver {

    private static Stream<Arguments> employeesNames() {
        return Stream.of(
                Arguments.of("Peftiiev Artem Yurievich", "Peftiiev", "Artem", "Yurievich"),
                Arguments.of("Gorodnychyi Oleksii", "Gorodnychyi", "Oleksii", "")
        );
    }

    @ParameterizedTest()
    @MethodSource("employeesNames")
    public void autofillNameSurnamePatronymic2(String name, String surname, String first_name, String patronymic) {
//        driver.get(prop.getProperty("url"));

        driver.findElement(By.cssSelector(".app-sidebar-menu a[data-menu-xmlid='hr.menu_hr_root']")).click();
        driver.findElement(By.cssSelector(".o-kanban-button-new")).click();

        driver.findElement(By.cssSelector("input[name='name']")).sendKeys(name);

        driver.findElement(By.cssSelector("input[name='surname']")).click();
        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("aaaaaa");

        String expectedText = driver.findElement(By.cssSelector("input[name='surname']")).getAttribute("value");
        assertEquals(expectedText, surname);
        expectedText = driver.findElement(By.cssSelector("input[name='first_name']")).getAttribute("value");
        assertEquals(expectedText, first_name);
        expectedText = driver.findElement(By.cssSelector("input[name='patronymic']")).getAttribute("value");
        assertEquals(expectedText, patronymic);
    }
}