package ru.bellintegrator;

import custom.drivers.Manager;
import custom.properties.TestData;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.*;

public class Tests extends BaseTest {

    @Feature("Проверка результатов поиска")
    @DisplayName("Проверка результатов поиска с помощью PO")
    @ParameterizedTest(name = "{displayName}: {arguments}")
    @CsvSource({"гладиолус, Википедия"})
    public void gladiolus(String keyWord, String source) {
        Manager.getCurrentDriver().get(TestData.propsUrl.baseURLYandex());
        SearchOnGoogle gladiolus = new SearchOnGoogle(chromeDriver);
        gladiolus.find(keyWord);
        gladiolus.checkSource(keyWord, source);
    }
}