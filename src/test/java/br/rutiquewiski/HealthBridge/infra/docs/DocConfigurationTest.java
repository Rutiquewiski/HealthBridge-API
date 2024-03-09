package br.rutiquewiski.HealthBridge.infra.docs;

import io.swagger.v3.oas.models.OpenAPI;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class DocConfigurationTest {

    @Test
    void customOpenAPIConfigurationTest() {
        // Arrange
        DocConfiguration docConfiguration = new DocConfiguration();

        // Act
        OpenAPI openAPI = docConfiguration.customOpenAPI();

        // Assert
        assertNotNull(openAPI);
        assertNotNull(openAPI.getComponents());
        assertNotNull(openAPI.getInfo());
        assertNotNull(openAPI.getComponents().getSecuritySchemes());
        assertEquals(2, openAPI.getComponents().getSecuritySchemes().size());
        assertNotNull(openAPI.getInfo().getTitle());
        assertNotNull(openAPI.getInfo().getDescription());
        assertNotNull(openAPI.getInfo().getContact());
        assertNotNull(openAPI.getInfo().getContact().getName());
        assertNotNull(openAPI.getInfo().getContact().getEmail());
    }
}
