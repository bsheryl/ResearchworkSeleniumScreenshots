package ru.bellintegrator;

import custom.drivers.Manager;
import custom.listener.WebDriverEventHandler;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;

import static custom.drivers.Manager.killCurrentDriver;

/**
 * Класс для работы с браузером
 */
public class BaseTest {

    protected WebDriver chromeDriver;

    /**
     * Данный метод открывает страницу в браузере.
     * Метод выполняется перед каждым тестом.
     */
    @BeforeEach
    @Step("Открываем браузер")
    public void openWindow() {
        Manager.initChrome();
        chromeDriver = Manager.getCurrentDriver();
        WebDriverListener listener = new WebDriverEventHandler();
        chromeDriver = new EventFiringDecorator(listener).decorate(chromeDriver);
    }

    /**
     * Данный метод закрывает браузер полностью.
     * Метод выполняется после каждого теста.
     */
    @AfterEach
    @Step("Закрываем браузер")
    public void closeWindow() {
        killCurrentDriver();
    }
}