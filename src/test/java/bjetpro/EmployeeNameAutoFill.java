package bjetpro;


import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.util.stream.Stream;

public class EmployeeNameAutoFill extends BaseMultiSessionTest {

    private static Stream<employeesFullName> getFromJSONFile() {
        return new ParseJson<employeesFullName>("src/test/java/bjetpro/UserData.json").ParseJson0("src/test/java/bjetpro/UserData.json");
//        return new ParseJson<employeesFullName>("src/test/java/bjetpro/UserData.json").getData();
    }


    private static Stream<Arguments> employeesNames() {
        return Stream.of(
                Arguments.of("Peftiiev Artem Yurievich", "Peftiiev", "Artem", "Yurievich"),
                Arguments.of("Gorodnychyi Oleksii", "Gorodnychyi", "Oleksii", "")
        );
    }

    //    @ParameterizedTest
//    @JsonFileSource(resources = "UserData.json")
//    public void autofillNameSurnamePatronymic(JsonObject object) {
//
//        String name = object.getString("name");
//        String surname = object.getString("surname");
//        String first_name = object.getString("first_name");
//        String patronymic = object.getString("patronymic");
    @ParameterizedTest
    @MethodSource("getFromJSONFile")
    public void autofillNameSurnamePatronymic(employeesFullName ele) {

        String name = ele.getName();
        String surname = ele.getSurname();
        String first_name = ele.getFirst_name();
        String patronymic = ele.getPatronymic();

        click(By.cssSelector(".app-sidebar-menu a[data-menu-xmlid='hr.menu_hr_root']"));
        click(By.cssSelector(".o-kanban-button-new"));
        sendString(By.cssSelector("input[name='name']"), name);

        click(By.cssSelector("input[name='surname']"));

        SoftAssertions softAssertions = new SoftAssertions();

        String expectedText = getStringValue(By.cssSelector("input[name='surname']"));
        softAssertions.assertThat(expectedText).isEqualTo(surname);

        expectedText = getStringValue(By.cssSelector("input[name='first_name']"));
        softAssertions.assertThat(expectedText).isEqualTo(first_name);

        expectedText = getStringValue(By.cssSelector("input[name='patronymic']"));
        softAssertions.assertThat(expectedText).isEqualTo(patronymic);

        softAssertions.assertAll();
    }
}

class employeesFullName {
    private String name;
    private String surname;
    private String first_name;
    private String patronymic;

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getFirst_name() {
        return first_name;
    }

    public String getPatronymic() {
        return patronymic;
    }

}
