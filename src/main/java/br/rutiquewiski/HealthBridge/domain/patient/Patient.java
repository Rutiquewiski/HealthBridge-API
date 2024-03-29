package br.rutiquewiski.HealthBridge.domain.patient;

import br.rutiquewiski.HealthBridge.domain.adress.Address;
import br.rutiquewiski.HealthBridge.domain.patient.DTO.PatientRegistrationDTO;
import br.rutiquewiski.HealthBridge.domain.patient.DTO.PatientUpdateDTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String document;
    private String medicalHistory;

    @Embedded
    private Address address;

    private boolean active;

    public Patient(PatientRegistrationDTO patientRegistrationDTO) {
        this.name = patientRegistrationDTO.name();
        this.email = patientRegistrationDTO.email();
        this.phone = patientRegistrationDTO.phone();
        this.document = patientRegistrationDTO.document();
        this.address = new Address(patientRegistrationDTO.address());
        this.medicalHistory = patientRegistrationDTO.medicalHistory();
        this.active = true;
    }

    public void updateInformation(PatientUpdateDTO patientUpdateDTO) {

        if (patientUpdateDTO.name() != null) {
            this.name = patientUpdateDTO.name();
        }

        if (patientUpdateDTO.email() != null) {
            this.email = patientUpdateDTO.email();
        }

        if (patientUpdateDTO.phone() != null) {
            this.phone = patientUpdateDTO.phone();
        }

        if (patientUpdateDTO.document() != null) {
            this.document = patientUpdateDTO.document();
        }

        if (patientUpdateDTO.address() != null) {
            this.address = new Address(patientUpdateDTO.address());
        }

        if (patientUpdateDTO.medicalHistory() != null) {
            this.medicalHistory = patientUpdateDTO.medicalHistory();
        }
    }

    public void safeDelete() {
        this.active = false;
    }
}
