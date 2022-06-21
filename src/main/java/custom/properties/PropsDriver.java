package custom.properties;

import org.aeonbits.owner.Config;

/**
 * Интерфейс для предоставления доступа к констанам файла driver.properties
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:driver.properties"
})
public interface PropsDriver extends Config {

    /**
     * Метод для предоставления доступа к переменной default.timeout, задающей время неявного ожидания
     * @return время неявного ожидания в секундах
     */
    @Key("default.timeout")
    int defaultTimeout();

    /**
     * Метод для предоставления доступа к переменной address.chrome.driver, хранящей наименование драйвера используемого браузера
     * @return наименование драйвера браузера
     */
    @Key("address.chrome.driver")
    String addressChromeDriver();
}
