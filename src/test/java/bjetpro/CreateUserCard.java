package bjetpro;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import java.util.stream.Stream;

public class CreateUserCard extends BaseMultiSessionTest {

    private static Stream<Arguments> userNames() {
        return Stream.of(
                Arguments.of("Taras Chornyi", "test1@test.com"),
                Arguments.of("John Doe",      "test2@test.com"),
                Arguments.of("Adam Red",      "test3@test.com"),
                Arguments.of("Stiven Doda",   "test4@test.com"),
                Arguments.of("Russel Hug",    "test5@test.com")
        );
    }

    @ParameterizedTest()
    @MethodSource("userNames")
    public void createUserCard(String name, String login) {

        driver.findElement(By.xpath("//*[@id='sidebar']/li/a[@data-menu-xmlid='base.menu_administration']")).click();
        driver.findElement(By.linkText("Users & Companies")).click();
        driver.findElement(By.xpath("//*[@data-menu-xmlid='base.menu_action_res_users']")).click();

        driver.findElement(By.cssSelector(".o_list_button_add")).click();

        driver.findElement(By.name("name")).sendKeys(name);
        driver.findElement(By.name("login")).sendKeys(login);

        driver.findElement(By.cssSelector(".o_form_button_save")).click();

        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException ie) {
            throw new RuntimeException(ie);
        }
    }
}
