package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.adress.AddressDTO;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO.DoctorRegistrationDTO;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO.DoctorUpdateDTO;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.Doctor;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.MedicalSpecialty;
import br.rutiquewiski.HealthBridge.repositories.DoctorRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
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
public class DoctorControllerTest {

    @InjectMocks
    private DoctorController doctorController;

    @Mock
    private DoctorRepository doctorRepository;

    @Mock
    private Page<Doctor> doctorPage;

    @Captor
    private ArgumentCaptor<Doctor> doctorArgumentCaptor;

    @Test
    public void testRegisterDoctor() {
        // Given
        DoctorRegistrationDTO registrationDTO = new DoctorRegistrationDTO("Dr. Smith", "smith@example.com", "123456789", "123456", new AddressDTO("Street", "Neighborhood", "12345678", "123", "Complement", "City", "State"), List.of(new MedicalSpecialty("Cardiology")));
        Doctor doctor = new Doctor(registrationDTO);

        // When
        when(doctorRepository.save(any(Doctor.class))).thenReturn(doctor);

        ResponseEntity<?> responseEntity = doctorController.registerDoctor(registrationDTO, UriComponentsBuilder.newInstance());

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isInstanceOf(URI.class);
    }

    @Test
    public void testGetAllDoctors() {
        // Given
        String name = "Dr. Smith";
        Pageable pageable = PageRequest.of(0, 10);
        List<Doctor> doctorsList = List.of(new Doctor(), new Doctor());

        // When
        when(doctorRepository.findAllByActiveTrueAndNameContaining(pageable, name)).thenReturn(doctorPage);
        when(doctorPage.isEmpty()).thenReturn(false, true);
        when(doctorPage.map(any(Function.class))).thenReturn(doctorPage);

        ResponseEntity<?> responseEntity = doctorController.getAllDoctors(name, pageable);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testGetDoctorDetails() {
        // Given
        int doctorId = 1;
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);

        // When
        when(doctorRepository.findByIdAndActiveTrue(doctorId)).thenReturn(doctor);

        ResponseEntity<?> responseEntity = doctorController.getDoctorDetails(doctorId);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testUpdateDoctor() {
        // Given
        int doctorId = 1;
        DoctorUpdateDTO updateDTO = new DoctorUpdateDTO("Dr. Smith", "smith@example.com", "123456789", "123456", new AddressDTO("Street", "Neighborhood", "12345678", "123", "Complement", "City", "State"), List.of(new MedicalSpecialty("Cardiology")));
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);

        // When
        when(doctorRepository.findByIdAndActiveTrue(doctorId)).thenReturn(doctor);

        ResponseEntity<?> responseEntity = doctorController.updateDoctor(doctorId, updateDTO);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Doctor information updated");
    }

    @Test
    public void testDeleteDoctor() {
        // Given
        int doctorId = 1;
        Doctor doctor = new Doctor();
        doctor.setId(doctorId);

        // When
        when(doctorRepository.findByIdAndActiveTrue(doctorId)).thenReturn(doctor);

        ResponseEntity<?> responseEntity = doctorController.deleteDoctor(doctorId);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Doctor deleted");
    }
}

