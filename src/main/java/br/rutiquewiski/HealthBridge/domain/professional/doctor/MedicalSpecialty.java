package br.rutiquewiski.HealthBridge.domain.professional.doctor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "medical_specialty")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalSpecialty {

    @Id
    private String name;
}
