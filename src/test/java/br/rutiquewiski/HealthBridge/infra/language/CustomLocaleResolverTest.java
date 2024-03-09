package br.rutiquewiski.HealthBridge.infra.language;

import jakarta.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class CustomLocaleResolverTest {

    @Test
    void resolveLocaleReturnsEnglish() {
        // Arrange
        CustomLocaleResolver resolver = new CustomLocaleResolver();
        HttpServletRequest request = mock(HttpServletRequest.class);

        // Act
        Locale resolvedLocale = resolver.resolveLocale(request);

        // Assert
        assertEquals(Locale.ENGLISH, resolvedLocale);
    }

    @Test
    void setLocaleDoesNotModifyLocale() {
        // Arrange
        CustomLocaleResolver resolver = new CustomLocaleResolver();
        HttpServletRequest request = mock(HttpServletRequest.class);

        // Act
        resolver.setLocale(request, null, Locale.FRENCH);

        // Assert
        // No assertion since setLocale does not modify the locale
    }
}