package custom.properties;

import org.aeonbits.owner.Config;

/**
 * Интерфейс для предоставление доступа к url используемого в тестировании сайта
 */
@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "system:properties",
        "system:env",
        "file:url.properties"
})
public interface PropsUrl extends Config {

    /**
     * Медот для предоставления доступа к сайту Яндекса
     * @return url сайта Яндекс
     */
    @Key("base.url.yandex")
    String baseURLYandex();
}
