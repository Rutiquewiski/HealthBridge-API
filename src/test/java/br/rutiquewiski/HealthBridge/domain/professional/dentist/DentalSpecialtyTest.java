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
}
