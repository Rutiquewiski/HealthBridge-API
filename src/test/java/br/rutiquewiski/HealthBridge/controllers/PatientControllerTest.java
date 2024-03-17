package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.adress.AddressDTO;
import br.rutiquewiski.HealthBridge.domain.patient.DTO.PatientListingDTO;
import br.rutiquewiski.HealthBridge.domain.patient.DTO.PatientRegistrationDTO;
import br.rutiquewiski.HealthBridge.domain.patient.DTO.PatientUpdateDTO;
import br.rutiquewiski.HealthBridge.domain.patient.Patient;
import br.rutiquewiski.HealthBridge.repositories.PatientRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class PatientControllerTest {

    @InjectMocks
    private PatientController patientController;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private Page<Patient> patientPage;


    @Test
    public void testRegisterPatient() {
        // Given
        PatientRegistrationDTO registrationDTO = new PatientRegistrationDTO("John Doe", "john@example.com", "123456789", "123456", new AddressDTO("Street", "Neighborhood", "12345678", "123", "Complement", "City", "State"), "Medical History");
        Patient patient = new Patient(registrationDTO);

        // When
        when(patientRepository.save(any(Patient.class))).thenReturn(patient);

        ResponseEntity<?> responseEntity = patientController.registerPatient(registrationDTO, UriComponentsBuilder.newInstance());

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isInstanceOf(URI.class);
    }

    @Test
    public void testGetAllPatients() {
        // Given
        String name = "John";
        Pageable pageable = PageRequest.of(0, 10);
        List<PatientListingDTO> patientListingDTOS = List.of(new PatientListingDTO(new Patient()));

        // When
        when(patientRepository.findAllByActiveTrueAndNameContaining(pageable, name)).thenReturn(patientPage);
        when(patientPage.isEmpty()).thenReturn(false, true);
        when(patientPage.map(any(Function.class))).thenReturn(patientPage);

        ResponseEntity<?> responseEntity = patientController.getAllPatients(name, pageable);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testGetPatientDetails() {
        // Given
        int patientId = 1;
        Patient patient = new Patient();
        patient.setId(patientId);

        // When
        when(patientRepository.findByIdAndActiveTrue(patientId)).thenReturn(patient);

        ResponseEntity<?> responseEntity = patientController.getPatientDetails(patientId);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testUpdatePatient() {
        // Given
        int patientId = 1;
        PatientUpdateDTO updateDTO = new PatientUpdateDTO("John Doe", "john@example.com", "123456789", "123456", new AddressDTO("Street", "Neighborhood", "12345678", "123", "Complement", "City", "State"), "Updated Medical History");
        Patient patient = new Patient();
        patient.setId(patientId);

        // When
        when(patientRepository.findByIdAndActiveTrue(patientId)).thenReturn(patient);

        ResponseEntity<?> responseEntity = patientController.updatePatient(patientId, updateDTO);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Patient information updated");
    }

    @Test
    public void testDeletePatient() {
        // Given
        int patientId = 1;
        Patient patient = new Patient();
        patient.setId(patientId);

        // When
        when(patientRepository.findByIdAndActiveTrue(patientId)).thenReturn(patient);

        ResponseEntity<?> responseEntity = patientController.deletePatient(patientId);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Patient deleted");
    }
}
