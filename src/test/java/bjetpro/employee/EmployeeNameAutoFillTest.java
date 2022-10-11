package bjetpro.employee;


import bjetpro.common.BaseMultiSessionTest;
import bjetpro.common.ParseJson;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class EmployeeNameAutoFillTest extends BaseMultiSessionTest {

    private static Stream<Arguments> employeesNames() {
        return Stream.of(
                Arguments.of("Peftiiev Artem Yurievich", "Peftiiev", "Artem", "Yurievich"),
                Arguments.of("Gorodnychyi Oleksii", "Gorodnychyi", "Oleksii", "")
        );
    }

    private static Stream<Arguments> getFromJSONFile() {
        return ParseJson.getStream("UserData.json", EmployeeFullName.class);
    }

    @ParameterizedTest
    @MethodSource("getFromJSONFile")
    public void autofillNameSurnamePatronymic(EmployeeFullName ele) {

        String name = ele.getName();
        String surname = ele.getSurname();
        String first_name = ele.getFirst_name();
        String patronymic = ele.getPatronymic();

        click(".app-sidebar-menu a[data-menu-xmlid='hr.menu_hr_root']");
        click(".o-kanban-button-new");
        sendString("input[name='name']", name);

        click("input[name='surname']");

        SoftAssertions softAssertions = new SoftAssertions();

        String expectedText = getStringValue("input[name='surname']");
        softAssertions.assertThat(expectedText).isEqualTo(surname);

        expectedText = getStringValue("input[name='first_name']");
        softAssertions.assertThat(expectedText).isEqualTo(first_name);

        expectedText = getStringValue("input[name='patronymic']");
        softAssertions.assertThat(expectedText).isEqualTo(patronymic);

        softAssertions.assertAll();
    }
}

class EmployeeFullName {
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
