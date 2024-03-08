package br.rutiquewiski.HealthBridge.infra.language;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.web.servlet.LocaleResolver;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageConfigurationTest {

    @Test
    void localeResolverBeanIsCustomLocaleResolver() {
        // Arrange
        ApplicationContext context = new AnnotationConfigApplicationContext(LanguageConfiguration.class);

        // Act
        LocaleResolver localeResolver = context.getBean(LocaleResolver.class);

        // Assert
        assertNotNull(localeResolver);
        assertInstanceOf(CustomLocaleResolver.class, localeResolver);
    }
}
