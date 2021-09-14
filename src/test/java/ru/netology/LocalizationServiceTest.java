package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationServiceImpl;

public class LocalizationServiceTest {

    @Test
    void localeTest() {
        final String ip = "96.124.432.21";
        final String message = "Welcome";

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        //Делаем заглушку на гео сервис, что при получение IP, вернётся локация США
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        //Получаем страну из заглушки
        Country country = geoService.byIp(ip).getCountry();
        //Проверяем, что по стране USA получаем Welcome
        Assertions.assertEquals(localizationService.locale(country), message);
    }
}
