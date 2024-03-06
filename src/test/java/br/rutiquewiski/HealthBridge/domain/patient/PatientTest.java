package br.rutiquewiski.HealthBridge.domain.patient;

import br.rutiquewiski.HealthBridge.domain.adress.Address;
import br.rutiquewiski.HealthBridge.domain.adress.AddressDTO;
import br.rutiquewiski.HealthBridge.domain.patient.DTO.PatientUpdateDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PatientTest {

    @Test
    void createPatientWithAllFields() {
        // Arrange
        Address address = new Address("Street", "Neighborhood", "Postal Code", "Number", "Complement", "City", "State");
        Patient patient = new Patient(1, "John Doe", "john@example.com", "1234567890", "12345", "Medical history", address, true);

        // Assert
        assertEquals(1, patient.getId());
        assertEquals("John Doe", patient.getName());
        assertEquals("john@example.com", patient.getEmail());
        assertEquals("1234567890", patient.getPhone());
        assertEquals("12345", patient.getDocument());
        assertEquals("Medical history", patient.getMedicalHistory());
        assertTrue(patient.isActive());
        assertEquals("Street", patient.getAddress().getStreet_address());
        assertEquals("Neighborhood", patient.getAddress().getNeighborhood());
        assertEquals("Postal Code", patient.getAddress().getPostal_code());
        assertEquals("Number", patient.getAddress().getNumber());
        assertEquals("Complement", patient.getAddress().getComplement());
        assertEquals("City", patient.getAddress().getCity());
        assertEquals("State", patient.getAddress().getState());
    }


    @Test
    void updatePatientInformation() {
        // Arrange
        Patient patient = new Patient();
        AddressDTO addressDTO = new AddressDTO("Updated Street", "Updated Neighborhood", "Updated Postal Code", "Updated Number", "Updated Complement", "Updated City", "Updated State");
        PatientUpdateDTO updateDTO = new PatientUpdateDTO("Updated Name","updated@example.com", "9876543210", "54321", addressDTO, "Updated Medical History");

        // Act
        patient.updateInformation(updateDTO);

        // Assert
        assertEquals("Updated Name", patient.getName());
        assertEquals("updated@example.com", patient.getEmail());
        assertEquals("9876543210", patient.getPhone());
        assertEquals("54321", patient.getDocument());
        assertEquals("Updated Street", patient.getAddress().getStreet_address());
        assertEquals("Updated Neighborhood", patient.getAddress().getNeighborhood());
        assertEquals("Updated Postal Code", patient.getAddress().getPostal_code());
        assertEquals("Updated Number", patient.getAddress().getNumber());
        assertEquals("Updated Complement", patient.getAddress().getComplement());
        assertEquals("Updated City", patient.getAddress().getCity());
        assertEquals("Updated State", patient.getAddress().getState());
        assertEquals("Updated Medical History", patient.getMedicalHistory());
    }

    @Test
    void safeDeletePatient() {
        // Arrange
        Patient patient = new Patient();

        // Act
        patient.safeDelete();

        // Assert
        assertFalse(patient.isActive());
    }
}
