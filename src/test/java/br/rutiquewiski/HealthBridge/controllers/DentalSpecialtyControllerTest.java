package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.professional.dentist.DentalSpecialty;
import br.rutiquewiski.HealthBridge.repositories.DentalSpecialtyRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
public class DentalSpecialtyControllerTest {

    @Mock
    private DentalSpecialtyRepository dentalSpecialtyRepository;

    @InjectMocks
    private DentalSpecialtyController dentalSpecialtyController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetDentalSpecialties() {
        // Mocking pageable
        Pageable pageable = mock(Pageable.class);

        // Mocking data
        List<DentalSpecialty> specialties = new ArrayList<>();
        specialties.add(new DentalSpecialty("Orthodontics"));
        specialties.add(new DentalSpecialty("Endodontics"));
        Page<DentalSpecialty> page = new PageImpl<>(specialties);

        // Mocking repository behavior
        when(dentalSpecialtyRepository.findAll(pageable)).thenReturn(page);

        // Performing the test
        ResponseEntity<Page<DentalSpecialty>> response = dentalSpecialtyController.getDentalSpecialties(pageable);

        // Assertions
        assertNotNull(response.getBody());
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(specialties, response.getBody().getContent());
    }

    @Test
    void testRegisterDentalSpecialty_Success() {
        // Mocking data
        DentalSpecialty specialty = new DentalSpecialty("Orthodontics");

        // Mocking repository behavior
        when(dentalSpecialtyRepository.findByName(any(String.class))).thenReturn(null);

        // Performing the test
        ResponseEntity<?> response = dentalSpecialtyController.registerDentalSpecialty(specialty);

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Dental specialty registered successfully", response.getBody());
        verify(dentalSpecialtyRepository, times(1)).save(specialty);
    }

    @Test
    void testRegisterDentalSpecialty_AlreadyExists() {
        // Mocking data
        DentalSpecialty specialty = new DentalSpecialty("Orthodontics");

        // Mocking repository behavior
        when(dentalSpecialtyRepository.findByName(any(String.class))).thenReturn(specialty);

        // Performing the test
        ResponseEntity<?> response = dentalSpecialtyController.registerDentalSpecialty(specialty);

        // Assertions
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Dental specialty already exists", response.getBody());
        verify(dentalSpecialtyRepository, never()).save(any());
    }

    @Test
    void testDeleteDentalSpecialty_Success() {
        // Mocking data
        DentalSpecialty specialty = new DentalSpecialty("Orthodontics");

        // Mocking repository behavior
        when(dentalSpecialtyRepository.findByName(any(String.class))).thenReturn(specialty);

        // Performing the test
        ResponseEntity<?> response = dentalSpecialtyController.deleteDentalSpecialty("Orthodontics");

        // Assertions
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Dental specialty deleted successfully", response.getBody());
        verify(dentalSpecialtyRepository, times(1)).delete(specialty);
    }

    @Test
    void testDeleteDentalSpecialty_NotFound() {
        // Mocking repository behavior
        when(dentalSpecialtyRepository.findByName(any(String.class))).thenReturn(null);

        // Performing the test
        ResponseEntity<?> response = dentalSpecialtyController.deleteDentalSpecialty("Orthodontics");

        // Assertions
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Dental specialty not found", response.getBody());
        verify(dentalSpecialtyRepository, never()).delete(any());
    }
}
