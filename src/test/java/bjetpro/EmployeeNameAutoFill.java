package bjetpro;


import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeNameAutoFill extends BaseMultiSessionTest {

    static String fullName;

    private EmployeeNameAutoFill(){
        fullName = new Credentials("UserData.properties").getCreds().getProperty("employeeFullName");
    }

    private static Stream<Arguments> employeesNames() {
        return Stream.of(
                Arguments.of(fullName, "Peftiiev", "Artem", "Yurievich"),
                Arguments.of("Gorodnychyi Oleksii", "Gorodnychyi", "Oleksii", "Ol")
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

        SoftAssertions softAssertions = new SoftAssertions();

        String expectedText = driver.findElement(By.cssSelector("input[name='surname']")).getAttribute("value");
        softAssertions.assertThat(expectedText).isEqualTo(surname);

        expectedText = driver.findElement(By.cssSelector("input[name='first_name']")).getAttribute("value");
        softAssertions.assertThat(expectedText).isEqualTo(first_name);

        expectedText = driver.findElement(By.cssSelector("input[name='patronymic']")).getAttribute("value");
        softAssertions.assertThat(expectedText).isEqualTo(patronymic);

        softAssertions.assertAll();
    }
}