package br.rutiquewiski.HealthBridge.infra.language;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.LocaleResolver;

@Configuration
public class LanguageConfiguration {

    @Bean
    public LocaleResolver localeResolver() {
        return new CustomLocaleResolver();
    }
}
