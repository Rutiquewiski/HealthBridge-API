package br.rutiquewiski.HealthBridge.domain.professional.dentist;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DentalSpecialtyTest {

    @Test
    void createDentalSpecialtyWithName() {
        // Arrange
        String specialtyName = "Endodontics";
        DentalSpecialty dentalSpecialty = new DentalSpecialty(specialtyName);

        // Assert
        assertEquals(specialtyName, dentalSpecialty.getName());
    }

    @Test
    public void testNoArgsConstructor() {
        // Test no-args constructor
        DentalSpecialty specialty = new DentalSpecialty();

        // Assertions
        assertNotNull(specialty);
    }

    @Test
    public void testAllArgsConstructor() {
        // Test all-args constructor
        DentalSpecialty specialty = new DentalSpecialty("Orthodontics");

        // Assertions
        assertNotNull(specialty);
        assertEquals("Orthodontics", specialty.getName());
    }

    @Test
    public void testSetName() {
        // Set name
        DentalSpecialty specialty = new DentalSpecialty();
        specialty.setName("Endodontics");

        // Assertions
        assertEquals("Endodontics", specialty.getName());
    }

    @Test
    public void testNotEqualsAndHashCode() {
        // Create two specialties with different names
        DentalSpecialty specialty1 = new DentalSpecialty("Prosthodontics");
        DentalSpecialty specialty2 = new DentalSpecialty("Orthodontics");

        // Assertions
        assertNotEquals(specialty1, specialty2);
        assertNotEquals(specialty1.hashCode(), specialty2.hashCode());
    }

}
