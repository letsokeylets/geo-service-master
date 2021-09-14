package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

public class GeoServiceTest {

    @Test
    void byIpTest() {
        final String ip = "172.13.10.12";
        final String message = "Добро пожаловать";

        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        // Делаем заглушку, на метод локал, что при стране RUSSIA вернёт "Добро пожаловать"
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn(message);

        GeoServiceImpl geoService = new GeoServiceImpl();
        //Тестируем метод ip, подсталвяя в него русский айпи (должна вернуться Country.RUSSIA)
        Country country = geoService.byIp(ip).getCountry();
        //Если метод ip работает корректно, сообщения совпадут
        Assertions.assertEquals(localizationService.locale(country), message);
    }
}
