package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

public class MessageSenderTest {
    Map<String, String> headers;

    @BeforeEach
    public void init() {
        headers = new HashMap<String, String>();
    }

    @Test
    void testRussianTextSend() {
        final String ip = "172.";

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String preferences = messageSender.send(headers);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected, preferences);
    }

    @Test
    void testByIpUsed() {
        final String ip = "96.";

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        LocalizationService localizationService = Mockito.mock(LocalizationService.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        GeoService geoService = Mockito.mock(GeoService.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location(null, Country.USA, null, 0));

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        messageSender.send(headers);

        //Проверяем, что метод byIp не вызывается, если айпи пуст
        Mockito.verify(geoService, Mockito.times(0)).byIp("");
        //В случае стандартного айпи - вызывается 1 раз
        Mockito.verify(geoService, Mockito.times(1)).byIp(ip);
    }

}
