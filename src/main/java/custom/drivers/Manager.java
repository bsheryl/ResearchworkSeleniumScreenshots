package custom.drivers;

import custom.assertions.Assertions;
import custom.properties.TestData;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.List;

/**
 * Класс для инициализирования браузера и настройки тестов
 */
public class Manager {

    private static WebDriver currentDriver;

    public static org.openqa.selenium.interactions.Actions action;

    /**
     * Геттер драйвера используемого браузера
     * @return драйвер браузера
     */
    public static WebDriver getCurrentDriver() {
        return currentDriver;
    }

    /**
     * Инициализация браузера
     */
    public static void initChrome() {
        System.setProperty("webdriver.chrome.driver", System.getenv(TestData.propsDriver.addressChromeDriver()));
        ChromeOptions options = new ChromeOptions();
        options.addArguments(List.of("start-maximized"));
        try {
            currentDriver = new ChromeDriver(options);
        } catch (SessionNotCreatedException e) {
            Assertions.fail("Данный драйвер не совместим с текущим браузером. Используйте другой драйвер");
        }
        setDriverDefaultSetting();
        initStaticObject();
    }

    /**
     * Установка настроек драйвера по умолчанию
     */
    public static void setDriverDefaultSetting() {
        currentDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(TestData.propsDriver.defaultTimeout()));
        currentDriver.manage().deleteAllCookies();
    }

    /**
     * Инициализация объекта класса Action для имитации действий пользователя
     */
    private static void initStaticObject() {
        action = new org.openqa.selenium.interactions.Actions(Manager.getCurrentDriver());
    }

    /**
     * Закрытие окна браузера и обнуление настроек
     */
    public static void killCurrentDriver() {
        if (currentDriver != null) {
            currentDriver.quit();
            currentDriver = null;
        }
    }
}
