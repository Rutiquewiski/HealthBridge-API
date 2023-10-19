package br.rutiquewiski.HealthBridge.domain.professional.Dentist;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dental_specialty")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DentalSpecialty {

    @Id
    private String name;
}
