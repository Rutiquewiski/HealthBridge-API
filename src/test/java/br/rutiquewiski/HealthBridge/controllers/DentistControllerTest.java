package br.rutiquewiski.HealthBridge.controllers;

import br.rutiquewiski.HealthBridge.domain.adress.AddressDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO.DentistRegistrationDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO.DentistUpdateDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DentalSpecialty;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;
import br.rutiquewiski.HealthBridge.repositories.DentistRepository;
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
public class DentistControllerTest {

    @InjectMocks
    private DentistController dentistController;

    @Mock
    private DentistRepository dentistRepository;

    @Mock
    private Page<Dentist> dentistPage;

    @Captor
    private ArgumentCaptor<Dentist> dentistArgumentCaptor;

    @Test
    public void testRegisterDentist() {
        // Given
        DentistRegistrationDTO registrationDTO = new DentistRegistrationDTO("Dr. Johnson", "johnson@example.com", "123456789", "123456", new AddressDTO("Street", "Neighborhood", "12345678", "123", "Complement", "City", "State"), List.of(new DentalSpecialty("Orthodontics")));
        Dentist dentist = new Dentist(registrationDTO);

        // When
        when(dentistRepository.save(any(Dentist.class))).thenReturn(dentist);

        ResponseEntity<?> responseEntity = dentistController.registerDentist(registrationDTO, UriComponentsBuilder.newInstance());

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
        assertThat(responseEntity.getBody()).isInstanceOf(URI.class);
    }

    @Test
    public void testGetAllDentists() {
        // Given
        String name = "Dr. Johnson";
        Pageable pageable = PageRequest.of(0, 10);
        List<Dentist> dentistsList = List.of(new Dentist(), new Dentist());

        // When
        when(dentistRepository.findAllByActiveTrueAndNameContaining(pageable, name)).thenReturn(dentistPage);
        when(dentistPage.isEmpty()).thenReturn(false, true);
        when(dentistPage.map(any(Function.class))).thenReturn(dentistPage);

        ResponseEntity<?> responseEntity = dentistController.getAllDentists(name, pageable);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testGetDentistDetails() {
        // Given
        int dentistId = 1;
        Dentist dentist = new Dentist();
        dentist.setId(dentistId);

        // When
        when(dentistRepository.findByIdAndActiveTrue(dentistId)).thenReturn(dentist);

        ResponseEntity<?> responseEntity = dentistController.getDentistDetails(dentistId);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isNotNull();
    }

    @Test
    public void testUpdateDentist() {
        // Given
        int dentistId = 1;
        DentistUpdateDTO updateDTO = new DentistUpdateDTO("Dr. Johnson", "johnson@example.com", "123456789", "123456", new AddressDTO("Street", "Neighborhood", "12345678", "123", "Complement", "City", "State"), List.of(new DentalSpecialty("Orthodontics")));
        Dentist dentist = new Dentist();
        dentist.setId(dentistId);

        // When
        when(dentistRepository.findByIdAndActiveTrue(dentistId)).thenReturn(dentist);

        ResponseEntity<?> responseEntity = dentistController.updateDentist(dentistId, updateDTO);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Dentist information updated");
    }

    @Test
    public void testDeleteDentist() {
        // Given
        int dentistId = 1;
        Dentist dentist = new Dentist();
        dentist.setId(dentistId);

        // When
        when(dentistRepository.findByIdAndActiveTrue(dentistId)).thenReturn(dentist);

        ResponseEntity<?> responseEntity = dentistController.deleteDentist(dentistId);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isEqualTo("Dentist deleted");
    }
}
