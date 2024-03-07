package br.rutiquewiski.HealthBridge.domain.professional.doctor;

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
}
