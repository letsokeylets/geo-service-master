package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;

public class GeoServiceTest {

    @Test
    void byIpTest() {
        GeoServiceImpl geoService = new GeoServiceImpl();
        Location locationRussian = geoService.byIp("172.323.43.12");
        Location locationUSA = geoService.byIp("96.323.43.12");

        Assertions.assertEquals(Country.RUSSIA, locationRussian.getCountry());
        Assertions.assertEquals(Country.USA, locationUSA.getCountry());
    }
}
