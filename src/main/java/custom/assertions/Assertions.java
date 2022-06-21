package custom.assertions;

import io.qameta.allure.Step;

/**
 * Класс для переопределения Assertions
 */
public class Assertions {

    /**
     * Метод для подтверждения, что предоставленное условие истинно
     * @param condition условие, которое необходимо проверить
     * @param message сообщение в случае ошибки
     */
    @Step("Проверяем, что нет ошибки: {message}")
    public static void assertTrue(boolean condition, String message) {
        org.junit.jupiter.api.Assertions.assertTrue(condition, message);
    }

    /**
     * Метод для подтверждения ошибки
     * @param message сообщение об ошибке
     */
    @Step("Подтверждаем ошибку: {message}")
    public static void fail(String message) {
        org.junit.jupiter.api.Assertions.fail(message);
    }
}
