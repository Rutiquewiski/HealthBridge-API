package br.rutiquewiski.HealthBridge.domain.pacient;

import br.rutiquewiski.HealthBridge.domain.adress.Adress;
import br.rutiquewiski.HealthBridge.domain.pacient.DTO.PacientRegistrationDTO;
import br.rutiquewiski.HealthBridge.domain.pacient.DTO.PacientUpdateDTO;

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
public class Pacient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String document;

    @Embedded
    private Adress adress;

    private boolean active;

    public Pacient(PacientRegistrationDTO pacientRegistrationDTO) {
        this.name = pacientRegistrationDTO.name();
        this.email = pacientRegistrationDTO.email();
        this.phone = pacientRegistrationDTO.phone();
        this.document = pacientRegistrationDTO.document();
        this.adress = new Adress(pacientRegistrationDTO.adress());
        this.active = true;
    }

    public void updateInformation(PacientUpdateDTO pacientUpdateDTO) {

        if (pacientUpdateDTO.name() != null) {
            this.name = pacientUpdateDTO.name();
        }

        if (pacientUpdateDTO.email() != null) {
            this.email = pacientUpdateDTO.email();
        }

        if (pacientUpdateDTO.phone() != null) {
            this.phone = pacientUpdateDTO.phone();
        }

        if (pacientUpdateDTO.document() != null) {
            this.document = pacientUpdateDTO.document();
        }

        if (pacientUpdateDTO.adress() != null) {
            this.adress = new Adress(pacientUpdateDTO.adress());
        }
    }

    public void safeDelete() {
        this.active = false;
    }
}
