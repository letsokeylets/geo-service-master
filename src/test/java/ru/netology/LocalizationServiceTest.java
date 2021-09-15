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
        String expectedRUS = "Добро пожаловать";
        String expectedUSA = "Welcome";

        LocalizationServiceImpl localizationService = new LocalizationServiceImpl();
        String actualRUS = localizationService.locale(Country.RUSSIA);
        String actualUSA = localizationService.locale(Country.USA);

        Assertions.assertEquals(expectedRUS, actualRUS);
        Assertions.assertEquals(expectedUSA, actualUSA);
        Assertions.assertNotEquals(expectedUSA, actualRUS);
    }
}
