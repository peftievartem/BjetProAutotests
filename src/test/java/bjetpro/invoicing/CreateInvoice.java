package bjetpro.invoicing;

import bjetpro.common.BaseMultiSessionTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;


public class CreateInvoice extends BaseMultiSessionTest {

    @Test()
    public void simpleCreateInvoiceTest() throws InterruptedException {

        click(".app-sidebar-menu a[data-menu-xmlid='bjet_invoicing_menu.bjet_invoicing_menu']");
        click("a[data-menu-xmlid='bjet_invoicing_menu.invoicing_menu_action_invoice_tree1']");

        waitOLoading();
//        wait.until(ExpectedConditions.attributeToBe(By.cssSelector(".o_loading"), "style", "display: none;"));
        click(".o_cp_buttons .o_list_button_add");

        click("div[name='partner_id'] input");

        String expectedText = "";

        waitOLoading();
//        wait.until(ExpectedConditions.attributeToBe(By.cssSelector(".o_loading"), "style", "display: none;"));

        expectedText = getText(By.cssSelector("ul[style^='display: block;'] li:nth-child(4) a"));
        click("ul[style^='display: block;'] li:nth-child(4) a");

        sendString("table div[name='date_invoice'] input.o_input", "11/13/2022");

        click("div[name='invoice_line_ids'] .o_field_x2many_list_row_add a");
        click("div[name='invoice_line_ids'] .o_data_row:first-child div[name='product_id']:first-child");

        waitOLoading();
//        wait.until(ExpectedConditions.attributeToBe(By.cssSelector(".o_loading"), "style", "display: none;"));
        click("ul[style^='display: block;'] li:first-child a");

        click(".o_statusbar_buttons_container .o_invoice_validate");

        click(".o_cp_buttons .o_form_button_save");
        click("a[data-menu-xmlid='bjet_invoicing_menu.invoicing_menu_action_invoice_tree1']");

        SoftAssertions softAssertions = new SoftAssertions();
        System.out.println(expectedText);
        softAssertions.assertThat(expectedText).isEqualTo(driver.findElement(By.cssSelector(".o_view_manager_content .o_data_row td:nth-child(2)")));

    }

}
