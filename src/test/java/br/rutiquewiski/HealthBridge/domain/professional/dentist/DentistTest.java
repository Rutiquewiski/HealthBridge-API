package br.rutiquewiski.HealthBridge.domain.professional.dentist;

import br.rutiquewiski.HealthBridge.domain.adress.Address;
import br.rutiquewiski.HealthBridge.domain.adress.AddressDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO.DentistUpdateDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DentistTest {

    @Test
    void createDentistWithAllFields() {
        // Arrange
        Address address = new Address("Street", "Neighborhood", "Postal Code", "Number", "Complement", "City", "State");
        Dentist dentist = new Dentist(1, "John Doe", "john@example.com", "1234567890", "12345", address, new ArrayList<>(), true);

        // Assert
        assertEquals(1, dentist.getId());
        assertEquals("John Doe", dentist.getName());
        assertEquals("john@example.com", dentist.getEmail());
        assertEquals("1234567890", dentist.getPhone());
        assertEquals("12345", dentist.getDocument());
        assertTrue(dentist.isActive());
        assertEquals("Street", dentist.getAddress().getStreet_address());
        assertEquals("Neighborhood", dentist.getAddress().getNeighborhood());
        assertEquals("Postal Code", dentist.getAddress().getPostal_code());
        assertEquals("Number", dentist.getAddress().getNumber());
        assertEquals("Complement", dentist.getAddress().getComplement());
        assertEquals("City", dentist.getAddress().getCity());
        assertEquals("State", dentist.getAddress().getState());
    }

    @Test
    void updateDentistInformation() {
        // Arrange
        Dentist dentist = new Dentist();
        AddressDTO addressDTO = new AddressDTO("Updated Street", "Updated Neighborhood", "Updated Postal Code", "Updated Number", "Updated Complement", "Updated City", "Updated State");
        List<DentalSpecialty> specialties = new ArrayList<>();
        specialties.add(new DentalSpecialty("Updated Specialty"));
        DentistUpdateDTO updateDTO = new DentistUpdateDTO("Updated Name","updated@example.com", "9876543210", "54321", addressDTO, specialties);

        // Act
        dentist.updateInformation(updateDTO);

        // Assert
        assertEquals("Updated Name", dentist.getName());
        assertEquals("updated@example.com", dentist.getEmail());
        assertEquals("9876543210", dentist.getPhone());
        assertEquals("54321", dentist.getDocument());
        assertEquals("Updated Street", dentist.getAddress().getStreet_address());
        assertEquals("Updated Neighborhood", dentist.getAddress().getNeighborhood());
        assertEquals("Updated Postal Code", dentist.getAddress().getPostal_code());
        assertEquals("Updated Number", dentist.getAddress().getNumber());
        assertEquals("Updated Complement", dentist.getAddress().getComplement());
        assertEquals("Updated City", dentist.getAddress().getCity());
        assertEquals("Updated State", dentist.getAddress().getState());
        assertEquals("Updated Specialty", dentist.getSpecialties().getFirst().getName());
    }

    @Test
    void safeDeleteDentist() {
        // Arrange
        Dentist dentist = new Dentist();

        // Act
        dentist.safeDelete();

        // Assert
        assertFalse(dentist.isActive());
    }
}
