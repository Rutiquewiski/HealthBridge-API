package br.rutiquewiski.HealthBridge.domain.professional.dentist;

import br.rutiquewiski.HealthBridge.domain.adress.Adress;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO.DentistRegistrationDTO;
import br.rutiquewiski.HealthBridge.domain.professional.dentist.DTO.DentistUpdateDTO;
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
public class Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String document;

    @Embedded
    private Adress adress;

    @ManyToMany
    @JoinTable(
            name = "dentist_specialties",
            joinColumns = @JoinColumn(name = "dentist_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_name")
    )
    private List<DentalSpecialty> specialties;

    private boolean active;

    public Dentist(DentistRegistrationDTO dentistRegistrationDTO) {
        this.name = dentistRegistrationDTO.name();
        this.email = dentistRegistrationDTO.email();
        this.phone = dentistRegistrationDTO.phone();
        this.document = dentistRegistrationDTO.document();
        this.adress = new Adress(dentistRegistrationDTO.adress());
        this.specialties = dentistRegistrationDTO.specialties();
        this.active = true;
    }

    public void updateInformation(DentistUpdateDTO dentistUpdateDTO) {

        if (dentistUpdateDTO.name() != null) {
            this.name = dentistUpdateDTO.name();
        }

        if (dentistUpdateDTO.email() != null) {
            this.email = dentistUpdateDTO.email();
        }

        if (dentistUpdateDTO.phone() != null) {
            this.phone = dentistUpdateDTO.phone();
        }

        if (dentistUpdateDTO.document() != null) {
            this.document = dentistUpdateDTO.document();
        }

        if (dentistUpdateDTO.adress() != null) {
            this.adress = new Adress(dentistUpdateDTO.adress());
        }

        if (dentistUpdateDTO.specialties() != null) {
            this.specialties = dentistUpdateDTO.specialties();
        }
    }

    public void safeDelete() {
        this.active = false;
    }
}
