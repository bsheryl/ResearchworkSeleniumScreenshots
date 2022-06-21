package pages;

import custom.assertions.Assertions;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

/**
 * Класс для поиска на Google
 */
public class SearchOnGoogle {
    protected WebDriver chromeDriver;
    protected WebElement searchField;
    private List<WebElement> result;

    WebDriverWait wait;

    /**
     * Конструктор для создания страницы поиска
     * @param chromeDriver ссылка на драйвер
     */
    public SearchOnGoogle(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(30));
    }

    /**
     * Метод для ввода слова с строку поисковика
     * @param word искомое слово
     */
    @Step("Выполняем поиск по слову {word}")
    public void find(String word) {
        searchField = chromeDriver.findElement(By.xpath("//input[@title='Поиск']"));
        searchField.click();
        searchField.sendKeys(word);
        searchField.submit();
    }

    /**
     * Метод проверяет, есть ли ссылка на sourse в результатах поиска
     * @param keyWord ключевое слово, по которому производился поиск
     * @param source искомый ресурс
     */
    @Step("Проверяем, есть ли ссылка на {source} в результатах поиска")
    public void checkSource(String keyWord, String source) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("main")));
        result = chromeDriver.findElements(By.xpath("//div[@data-sokoban-container or contains(@class, 'kp-wholepage kp-wholepage-osrp')]"));
        Assertions.assertTrue(result.stream().anyMatch(x->x.getText().contains(source)),
                "Статья про " + keyWord + " на сайте " + source + " на странице не найдена");
    }
}
