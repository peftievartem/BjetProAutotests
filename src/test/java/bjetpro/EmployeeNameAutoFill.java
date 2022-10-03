package bjetpro;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

public class EmployeeNameAutoFill extends BaseMultiSessionTest {

    private static Stream<employeesFullName> getFromJSONFile() {
        ObjectMapper mapper = new ObjectMapper();
        employeesFullName[] userData = new employeesFullName[0];
        try {
            userData = mapper.readValue(new File("src/test/java/bjetpro/UserData.json"), employeesFullName[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Arrays.stream(userData);
    }


    private static Stream<Arguments> employeesNames() {
        return Stream.of(
                Arguments.of("Peftiiev Artem Yurievich", "Peftiiev", "Artem", "Yurievich"),
                Arguments.of("Gorodnychyi Oleksii", "Gorodnychyi", "Oleksii", "")
        );
    }

    //    @ParameterizedTest
//    @JsonFileSource(resources = "/UserData.json")
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

        clickBySelector(".app-sidebar-menu a[data-menu-xmlid='hr.menu_hr_root']");
        clickBySelector(".o-kanban-button-new");
        sendBySelector("input[name='name']", name);

        clickBySelector("input[name='surname']");
//        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("aaaaaa");

        SoftAssertions softAssertions = new SoftAssertions();

        String expectedText = getValueBySelector("input[name='surname']");
        softAssertions.assertThat(expectedText).isEqualTo(surname);

        expectedText = getValueBySelector("input[name='first_name']");
        softAssertions.assertThat(expectedText).isEqualTo(first_name);

        expectedText = getValueBySelector("input[name='patronymic']");
        softAssertions.assertThat(expectedText).isEqualTo(patronymic);

        softAssertions.assertAll();
    }
//    @ParameterizedTest
    @MethodSource("employeesNames")
    public void autofillNameSurnamePatronymic2(String name, String surname, String first_name, String patronymic) {

        clickBySelector(".app-sidebar-menu a[data-menu-xmlid='hr.menu_hr_root']");
        clickBySelector(".o-kanban-button-new");
        sendBySelector("input[name='name']", name);

        clickBySelector("input[name='surname']");
//        driver.findElement(By.cssSelector("input[name='name']")).sendKeys("aaaaaa");

        SoftAssertions softAssertions = new SoftAssertions();

        String expectedText = getValueBySelector("input[name='surname']");
        softAssertions.assertThat(expectedText).isEqualTo(surname);

        expectedText = getValueBySelector("input[name='first_name']");
        softAssertions.assertThat(expectedText).isEqualTo(first_name);

        expectedText = getValueBySelector("input[name='patronymic']");
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
