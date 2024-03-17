package br.rutiquewiski.HealthBridge.domain.professional.doctor;

import br.rutiquewiski.HealthBridge.domain.professional.dentist.DentalSpecialty;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MedicalSpecialtyTest {

    @Test
    void createMedicalSpecialtyWithName() {
        // Arrange
        String specialtyName = "Cardiology";
        MedicalSpecialty medicalSpecialty = new MedicalSpecialty(specialtyName);

        // Assert
        assertEquals(specialtyName, medicalSpecialty.getName());
    }

    @Test
    public void testNoArgsConstructor() {
        // Test no-args constructor
        MedicalSpecialty specialty = new MedicalSpecialty();

        // Assertions
        assertNotNull(specialty);
    }

    @Test
    public void testAllArgsConstructor() {
        // Test all-args constructor
        MedicalSpecialty specialty = new MedicalSpecialty("Cardiology");

        // Assertions
        assertNotNull(specialty);
        assertEquals("Cardiology", specialty.getName());
    }

    @Test
    public void testSetName() {
        // Set name
        MedicalSpecialty specialty = new MedicalSpecialty();
        specialty.setName("Cardiology");

        // Assertions
        assertEquals("Cardiology", specialty.getName());
    }

    @Test
    public void testNotEqualsAndHashCode() {
        // Create two specialties with different names
        MedicalSpecialty specialty1 = new MedicalSpecialty("Cardiology");
        MedicalSpecialty specialty2 = new MedicalSpecialty("Cardiology");

        // Assertions
        assertNotEquals(specialty1, specialty2);
        assertNotEquals(specialty1.hashCode(), specialty2.hashCode());
    }
}
