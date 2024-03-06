package br.rutiquewiski.HealthBridge.domain.professional.doctor;

import br.rutiquewiski.HealthBridge.domain.adress.Address;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO.DoctorRegistrationDTO;
import br.rutiquewiski.HealthBridge.domain.professional.doctor.DTO.DoctorUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Doctor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String document;

    @Embedded
    private Address address;

    @ManyToMany
    @JoinTable(
            name = "doctor_specialties",
            joinColumns = @JoinColumn(name = "doctor_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_name")
    )
    private List<MedicalSpecialty> specialties;

    private boolean active;

    public Doctor(DoctorRegistrationDTO doctorRegistrationDTO) {
        this.name = doctorRegistrationDTO.name();
        this.email = doctorRegistrationDTO.email();
        this.phone = doctorRegistrationDTO.phone();
        this.document = doctorRegistrationDTO.document();
        this.address = new Address(doctorRegistrationDTO.address());
        this.specialties = doctorRegistrationDTO.specialties();
        this.active = true;
    }

    public void updateInformation(DoctorUpdateDTO doctorUpdateDTO) {

        if (doctorUpdateDTO.name() != null) {
            this.name = doctorUpdateDTO.name();
        }

        if (doctorUpdateDTO.email() != null) {
            this.email = doctorUpdateDTO.email();
        }

        if (doctorUpdateDTO.phone() != null) {
            this.phone = doctorUpdateDTO.phone();
        }

        if (doctorUpdateDTO.document() != null) {
            this.document = doctorUpdateDTO.document();
        }

        if (doctorUpdateDTO.address() != null) {
            this.address = new Address(doctorUpdateDTO.address());
        }

        if (doctorUpdateDTO.specialties() != null) {
            this.specialties = doctorUpdateDTO.specialties();
        }
    }

    public void safeDelete() {
        this.active = false;
    }
}
