package helpers;

import custom.drivers.Manager;
import io.qameta.allure.Attachment;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Класс для создания скриншотов к отчету о выполнении теста
 */
public class ScreenShoter {

    /**
     * Метод делает скриншот страницы
     * @return скриншот страницы в случае успешного выполнения и 0 байтов с случае ошибки
     */
    @Attachment
    public static byte[] getScreen() {
        File screenshot = ((TakesScreenshot)Manager.getCurrentDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/main/resources/screen.png"));
            return Files.readAllBytes(Paths.get("src/main/resources", "screen.png"));
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Не удалось сохранить файл на диск");
        }
        return new byte[0];
    }

    /**
     * Метод выполняет предварительный переход к element на странице и  только после делает скриншот
     * @param target элемент страницы, к которому необходимо перейти для выполнения скриншота
     * @return скриншот страницы в случае успешного выполнения и 0 байтов с случае ошибки
     */
    @Attachment
    public static byte[] getScreen(Object target) {
        Manager.action.moveToElement((WebElement) target).build().perform();

        File screenshot = ((TakesScreenshot)Manager.getCurrentDriver()).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File("src/main/resources/screen.png"));
            return Files.readAllBytes(Paths.get("src/main/resources", "screen.png"));
        } catch (Exception e) {
            e.printStackTrace();
            Assertions.fail("Не удалось сохранить файл на диск");
        }
        return new byte[0];
    }
}
