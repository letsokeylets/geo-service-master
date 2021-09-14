package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoServiceImpl;
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
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn("Добро пожаловать");

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String preferences = messageSender.send(headers);
        String expected = "Добро пожаловать";
        Assertions.assertEquals(expected, preferences);
    }

    @Test
    void testUSATextSend() {
        final String ip = "96.";

        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        LocalizationServiceImpl localizationService = Mockito.mock(LocalizationServiceImpl.class);
        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn("Welcome");

        GeoServiceImpl geoService = Mockito.mock(GeoServiceImpl.class);
        Mockito.when(geoService.byIp(ip))
                .thenReturn(new Location("New York", Country.USA, null,  0));

        MessageSenderImpl messageSender = new MessageSenderImpl(geoService, localizationService);
        String preferences = messageSender.send(headers);
        String expected = "Welcome";
        Assertions.assertEquals(expected, preferences);
    }
}
