package bjetpro;

import org.assertj.core.api.SoftAssertions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Test7361 extends BaseMultiSessionTest{
    // https://bjet.atlassian.net/browse/BJET-7361

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

        driver.findElement(By.cssSelector(".app-sidebar-menu a[data-menu-xmlid='hr_payroll.menu_hr_payroll_root']")).click();
        driver.findElement(By.linkText("Configuration")).click();
        driver.findElement(By.cssSelector(".o_sub_menu_content a[data-menu-xmlid='l10n_ua_hr_payroll.menu_hr_schedule_templates']")).click();
        driver.findElement(By.cssSelector(".o_data_row:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".o_form_buttons_view .o_form_button_edit")).click();

        driver.findElement(By.cssSelector(".o_data_row:nth-child(1) > .o_data_cell:nth-child(3)")).click();
        driver.findElement(By.cssSelector(".o_data_row:nth-child(1) > .o_data_cell:nth-child(3) input")).sendKeys(timeFrom);

        driver.findElement(By.cssSelector(".o_data_row:nth-child(1) > .o_data_cell:nth-child(4)")).click();
        driver.findElement(By.cssSelector(".o_data_row:nth-child(1) > .o_data_cell:nth-child(4) input")).sendKeys(timeTo);

        driver.findElement(By.cssSelector(".o_form_buttons_edit .o_form_button_save")).click();

        SoftAssertions softAssertions = new SoftAssertions();

        if (!result) {
            softAssertions.assertThat(driver.findElement(By.cssSelector(".modal-dialog .o_dialog_warning")).isDisplayed());
        }else{
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            softAssertions.assertThat(driver.findElements(By.cssSelector(".modal-dialog .o_dialog_warning")).size() == 0);
        }
    }


    @ParameterizedTest()
    @MethodSource("times")
    public void workingTimesTest(String timeFrom, String timeTo, boolean result) {

        driver.findElement(By.cssSelector(".app-sidebar-menu a[data-menu-xmlid='hr_payroll.menu_hr_payroll_root']")).click();
        driver.findElement(By.linkText("Configuration")).click();
        driver.findElement(By.cssSelector(".o_sub_menu_content a[data-menu-xmlid='l10n_ua_hr_payroll.menu_hr_payslip_view_resource_calendar']")).click();
        driver.findElement(By.cssSelector(".o_data_row:nth-child(1)")).click();
        driver.findElement(By.cssSelector(".o_form_buttons_view .o_form_button_edit")).click();

        driver.findElement(By.cssSelector(".o_data_row:nth-child(1) > .o_data_cell:nth-child(4)")).click();
        driver.findElement(By.cssSelector(".o_data_row:nth-child(1) > .o_data_cell:nth-child(4) input")).sendKeys(timeFrom);

        driver.findElement(By.cssSelector(".o_data_row:nth-child(1) > .o_data_cell:nth-child(5)")).click();
        driver.findElement(By.cssSelector(".o_data_row:nth-child(1) > .o_data_cell:nth-child(5) input")).sendKeys(timeTo);

        driver.findElement(By.cssSelector(".o_form_buttons_edit .o_form_button_save")).click();

        SoftAssertions softAssertions = new SoftAssertions();

        if (!result) {
            softAssertions.assertThat(driver.findElement(By.cssSelector(".modal-dialog .o_dialog_warning")).isDisplayed());
        }else{
            driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
            softAssertions.assertThat(driver.findElements(By.cssSelector(".modal-dialog .o_dialog_warning")).size() == 0);
        }
    }


}






