package custom.listener;

import helpers.ScreenShoter;
import org.openqa.selenium.support.events.WebDriverListener;

import java.lang.reflect.Method;

/**
 * Класс для кастомизации листнеров
 */
public class WebDriverEventHandler implements WebDriverListener {

    /**
     * Метод делает скриншот после каждого действия в браузере
     */
    @Override
    public void afterAnyCall(Object target, Method method, Object[] args, Object result) {
        ScreenShoter.getScreen(target);
    }
}
