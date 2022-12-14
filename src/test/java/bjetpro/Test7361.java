package bjetpro;

import bjetpro.common.BaseMultiSessionTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Test7361 extends BaseMultiSessionTest {
    // BJET-7361

    private static Stream<Arguments> times() {
        return Stream.of(
                Arguments.of("18:00", "17:00", false),
                Arguments.of("18:01", "18:00", false),
                Arguments.of("17:00", "18:00", true),
                Arguments.of("17:00", "00:00", true)
        );
    }

    @ParameterizedTest()
    @MethodSource("times")
    public void annualTemplatesTest(String timeFrom, String timeTo, boolean result) {

        click(".app-sidebar-menu a[data-menu-xmlid='hr_payroll.menu_hr_payroll_root']");
        click(By.linkText("Configuration"));
        click(".o_sub_menu_content a[data-menu-xmlid='l10n_ua_hr_payroll.menu_hr_schedule_templates']");
        click(".o_data_row:nth-child(1)");
        click(".o_form_buttons_view .o_form_button_edit");
        click(".o_data_row:nth-child(1) > .o_data_cell:nth-child(3)");
        sendString(".o_data_row:nth-child(1) > .o_data_cell:nth-child(3) input", timeFrom);
        click(".o_data_row:nth-child(1) > .o_data_cell:nth-child(4)");
        sendString("table div[name='date_invoice'] input.o_input", "11/13/2022");
        sendString(".o_data_row:nth-child(1) > .o_data_cell:nth-child(4) input", timeTo);
        click(".o_form_buttons_edit .o_form_button_save");

        SoftAssertions softAssertions = new SoftAssertions();

        if (!result) {
            softAssertions.assertThat(element(".modal-dialog .o_dialog_warning").isDisplayed());
        } else {
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            softAssertions.assertThat(driver.findElements(By.cssSelector(".modal-dialog .o_dialog_warning")).size() == 0);
//            softAssertions.assertThat(element(".modal-dialog .o_dialog_warning").size() == 0);
        }
    }


    @ParameterizedTest()
    @MethodSource("times")
    public void workingTimesTest(String timeFrom, String timeTo, boolean result) {

        click(".app-sidebar-menu a[data-menu-xmlid='hr_payroll.menu_hr_payroll_root']");
        click(By.linkText("Configuration"));
        click(".o_sub_menu_content a[data-menu-xmlid='l10n_ua_hr_payroll.menu_hr_payslip_view_resource_calendar']");
        click(".o_data_row:nth-child(1)");
        click(".o_form_buttons_view .o_form_button_edit");
        click(".o_data_row:nth-child(1) > .o_data_cell:nth-child(4)");
        sendString(".o_data_row:nth-child(1) > .o_data_cell:nth-child(4) input", timeTo);
        click(".o_data_row:nth-child(1) > .o_data_cell:nth-child(5)");
        sendString(".o_data_row:nth-child(1) > .o_data_cell:nth-child(5) input", timeTo);
        click(".o_form_buttons_edit .o_form_button_save");

        SoftAssertions softAssertions = new SoftAssertions();

        if (!result) {
            softAssertions.assertThat(element(".modal-dialog .o_dialog_warning").isDisplayed());
        } else {
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            softAssertions.assertThat(driver.findElements(By.cssSelector(".modal-dialog .o_dialog_warning")).size() == 0);
        }
    }


}






