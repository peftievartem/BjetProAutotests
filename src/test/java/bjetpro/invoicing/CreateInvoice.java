package bjetpro.invoicing;

import bjetpro.BaseMultiSessionTest;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Wait;

import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


public class CreateInvoice extends BaseMultiSessionTest{

    @Test()
    public void simpleCreateInvoiceTest() throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver,10);

        driver.findElement(By.cssSelector(".app-sidebar-menu a[data-menu-xmlid='bjet_invoicing_menu.bjet_invoicing_menu']")).click();
        driver.findElement(By.cssSelector("a[data-menu-xmlid='bjet_invoicing_menu.invoicing_menu_action_invoice_tree1']")).click();


        wait.until(ExpectedConditions.attributeToBe(By.cssSelector(".o_loading"), "style", "display: none;"));
        driver.findElement(By.cssSelector(".o_cp_buttons .o_list_button_add")).click();
        
        driver.findElement(By.cssSelector("div[name='partner_id'] input")).click();

        String expectedText = "";

        wait.until(ExpectedConditions.attributeToBe(By.cssSelector(".o_loading"), "style", "display: none;"));

        expectedText = driver.findElement(By.cssSelector("ul[style^='display: block;'] li:nth-child(4) a")).getText();
        driver.findElement(By.cssSelector("ul[style^='display: block;'] li:nth-child(4) a")).click();


        driver.findElement(By.cssSelector("table div[name='date_invoice'] input.o_input")).sendKeys("11/13/2022");


        driver.findElement(By.cssSelector("div[name='invoice_line_ids'] .o_field_x2many_list_row_add a")).click();
        driver.findElement(By.cssSelector("div[name='invoice_line_ids'] .o_data_row:first-child div[name='product_id']:first-child")).click();

        wait.until(ExpectedConditions.attributeToBe(By.cssSelector(".o_loading"), "style", "display: none;"));
        driver.findElement(By.cssSelector("ul[style^='display: block;'] li:first-child a")).click();

        driver.findElement(By.cssSelector(".o_statusbar_buttons_container .o_invoice_validate")).click();

        driver.findElement(By.cssSelector(".o_cp_buttons .o_form_button_save")).click();
        driver.findElement(By.cssSelector("a[data-menu-xmlid='bjet_invoicing_menu.invoicing_menu_action_invoice_tree1']")).click();

        SoftAssertions softAssertions = new SoftAssertions();
        System.out.println(expectedText);
        softAssertions.assertThat(expectedText).isEqualTo(driver.findElement(By.cssSelector(".o_view_manager_content .o_data_row td:nth-child(2)")));

    }

}
