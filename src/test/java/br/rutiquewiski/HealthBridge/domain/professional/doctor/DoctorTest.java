package br.rutiquewiski.HealthBridge.domain.professional.doctor;

import br.rutiquewiski.HealthBridge.domain.adress.Address;
import br.rutiquewiski.HealthBridge.domain.adress.AddressDTO;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO.DoctorUpdateDTO;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class DoctorTest {

    @Test
    void createDoctorWithAllFields() {
        // Arrange
        Address address = new Address("Street", "Neighborhood", "Postal Code", "Number", "Complement", "City", "State");
        Doctor doctor = new Doctor(1, "John Doe", "john@example.com", "1234567890", "12345", address, new ArrayList<>(), true);

        // Assert
        assertEquals(1, doctor.getId());
        assertEquals("John Doe", doctor.getName());
        assertEquals("john@example.com", doctor.getEmail());
        assertEquals("1234567890", doctor.getPhone());
        assertEquals("12345", doctor.getDocument());
        assertTrue(doctor.isActive());
        assertEquals("Street", doctor.getAddress().getStreet_address());
        assertEquals("Neighborhood", doctor.getAddress().getNeighborhood());
        assertEquals("Postal Code", doctor.getAddress().getPostal_code());
        assertEquals("Number", doctor.getAddress().getNumber());
        assertEquals("Complement", doctor.getAddress().getComplement());
        assertEquals("City", doctor.getAddress().getCity());
        assertEquals("State", doctor.getAddress().getState());
    }

    @Test
    void updateDoctorInformation() {
        // Arrange
        Doctor doctor = new Doctor();
        AddressDTO addressDTO = new AddressDTO("Updated Street", "Updated Neighborhood", "Updated Postal Code", "Updated Number", "Updated Complement", "Updated City", "Updated State");
        List<MedicalSpecialty> specialties = new ArrayList<>();
        specialties.add(new MedicalSpecialty("Updated Specialty"));
        DoctorUpdateDTO updateDTO = new DoctorUpdateDTO("Updated Name","updated@example.com", "9876543210", "54321", addressDTO, specialties);

        // Act
        doctor.updateInformation(updateDTO);

        // Assert
        assertEquals("Updated Name", doctor.getName());
        assertEquals("updated@example.com", doctor.getEmail());
        assertEquals("9876543210", doctor.getPhone());
        assertEquals("54321", doctor.getDocument());
        assertEquals("Updated Street", doctor.getAddress().getStreet_address());
        assertEquals("Updated Neighborhood", doctor.getAddress().getNeighborhood());
        assertEquals("Updated Postal Code", doctor.getAddress().getPostal_code());
        assertEquals("Updated Number", doctor.getAddress().getNumber());
        assertEquals("Updated Complement", doctor.getAddress().getComplement());
        assertEquals("Updated City", doctor.getAddress().getCity());
        assertEquals("Updated State", doctor.getAddress().getState());
        assertEquals("Updated Specialty", doctor.getSpecialties().get(0).getName());
    }

    @Test
    void safeDeleteDoctor() {
        // Arrange
        Doctor doctor = new Doctor();

        // Act
        doctor.safeDelete();

        // Assert
        assertFalse(doctor.isActive());
    }
}