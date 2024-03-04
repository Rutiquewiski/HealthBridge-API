package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.professional.doctor.MedicalSpecialty;
import br.rutiquewiski.HealthBridge.repositories.MedicalSpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class MedicalSpecialtyControllerTest {

    @InjectMocks
    private MedicalSpecialtyController medicalSpecialtyController;

    @Mock
    private MedicalSpecialtyRepository medicalSpecialtyRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetMedicalSpecialties() {
        // Mock data
        List<MedicalSpecialty> specialties = Arrays.asList(new MedicalSpecialty("Specialty 1"), new MedicalSpecialty("Specialty 2"));
        Page<MedicalSpecialty> page = new PageImpl<>(specialties);

        // Mock repository behavior
        when(medicalSpecialtyRepository.findAll(any(Pageable.class))).thenReturn(page);

        // Call the controller method
        ResponseEntity<Page<MedicalSpecialty>> responseEntity = medicalSpecialtyController.getMedicalSpecialties(PageRequest.of(0, 10));

        // Assert the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(page, responseEntity.getBody());
    }

    @Test
    public void testRegisterMedicalSpecialty() {
        MedicalSpecialty specialty = new MedicalSpecialty("Specialty");

        // Mock repository behavior
        when(medicalSpecialtyRepository.findByName(specialty.getName())).thenReturn(null);

        // Call the controller method
        ResponseEntity<?> responseEntity = medicalSpecialtyController.registerMedicalSpecialty(specialty);

        // Assert the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Medical specialty registered successfully", responseEntity.getBody());
        verify(medicalSpecialtyRepository).save(specialty);
    }

    @Test
    public void testRegisterMedicalSpecialtyAlreadyExists() {
        MedicalSpecialty specialty = new MedicalSpecialty("Specialty");

        // Mock repository behavior
        when(medicalSpecialtyRepository.findByName(specialty.getName())).thenReturn(specialty);

        // Call the controller method
        ResponseEntity<?> responseEntity = medicalSpecialtyController.registerMedicalSpecialty(specialty);

        // Assert the response
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Medical specialty already exists", responseEntity.getBody());
        verify(medicalSpecialtyRepository, never()).save(specialty);
    }

    @Test
    public void testDeleteMedicalSpecialty() {
        String specialtyName = "Specialty";

        // Mock repository behavior
        when(medicalSpecialtyRepository.findByName(specialtyName)).thenReturn(new MedicalSpecialty(specialtyName));

        // Call the controller method
        ResponseEntity<?> responseEntity = medicalSpecialtyController.deleteMedicalSpecialty(specialtyName);

        // Assert the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Medical specialty deleted successfully", responseEntity.getBody());
        verify(medicalSpecialtyRepository).delete(any(MedicalSpecialty.class));
    }

    @Test
    public void testDeleteNonExistingMedicalSpecialty() {
        String specialtyName = "Specialty";

        // Mock repository behavior
        when(medicalSpecialtyRepository.findByName(specialtyName)).thenReturn(null);

        // Call the controller method
        ResponseEntity<?> responseEntity = medicalSpecialtyController.deleteMedicalSpecialty(specialtyName);

        // Assert the response
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Medical specialty not found", responseEntity.getBody());
        verify(medicalSpecialtyRepository, never()).delete(any(MedicalSpecialty.class));
    }
}

