package custom.drivers;

import custom.properties.TestData;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static custom.drivers.Manager.getCurrentDriver;

public class Waits {

    public static WebDriverWait wait = new WebDriverWait(getCurrentDriver(),
            Duration.ofSeconds(TestData.propsDriver.defaultTimeout()));

    /**
     * Метод для ожидания без привязки к элементу
     * @param seconds количество секунд ожидания
     */
    public static void sleep(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод для ожидания проверки того, что элемент отсутствует
     * @param xpath путь к элементу
     */
    public static void waitUntilElementNotExist(String xpath) {
        int timer = 0;
        for (; timer <= TestData.propsDriver.defaultTimeout(); ++timer) {
            if (getCurrentDriver().findElements(By.xpath(xpath)).size() == 0)
                break;
            sleep(1);
        }
        if (timer == TestData.propsDriver.defaultTimeout()) {
            throw new TimeoutException("Элемент с селектором " + xpath +
                    " не изчезнет за " + TestData.propsDriver.defaultTimeout() + " секунд");
        }
    }

    /**
     * Метод для ожидания проверки того, что элемент кликабелен
     * @param xpath путь к элементу
     */
    public static void waitUntilElementRealyClicable(String xpath) {
        Waits.wait.until((ExpectedCondition<Boolean>) driver -> {
            try {
                driver.findElement(By.xpath(xpath)).click();
            } catch (ElementClickInterceptedException e) {
                return false;
            }
            return true;
        });
    }

    /**
     * Метод для ожидания проверки того, что элемент присутствует и виден
     * @param xpath путь к элементу
     */
    public static void waitUntilVisibilityOfElementLocated(String xpath) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
    }

    /**
     * Метод для ожидания проверки того, что элемент виден и кликабелен
     * @param xpath путь к элементу
     */
    public static void waitUntilElementClickable(String xpath) {
        Waits.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
    }

    /**
     * Метод для ожидания проверки того, что элемент присутствует
     * @param xpath путь к элементу
     */
    public static void waitUntilElementPresents(String xpath) {
        Waits.wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(xpath)));
    }

    /**
     * Метод для ожидания проверки того, что аттриут элемента примет требуемое значение
     * @param element проверяемый элемент
     * @param attribute проверяемые аттрибут элемента
     * @param value ожидаемое значение аттрибута
     */
    public static void waitUntilAttributeWillBe(WebElement element, String attribute, String value) {
        Waits.wait.until((ExpectedCondition<Boolean>) driver -> element.getAttribute(attribute).contains(value));
    }

    /**
     * Метод для ожидания проверки того, что заданный текст присутствует в элементе, заданным локатором
     * @param locator локатор проверяемого элемента
     * @param text заданный текст
     */
    public static void waitUntilElementTextContainsByLocator(By locator, String text) {
        Waits.wait.until(ExpectedConditions.textToBePresentInElementLocated(locator, text));
    }

    /**
     * Метод для ожидания проверки того, что заданный текст присутствует в элементе
     * @param text заданный текст
     */
    public static void waitUntilElementTextContains(WebElement element, String text) {
        Waits.wait.until(ExpectedConditions.textToBePresentInElement(element, text));
    }
}
