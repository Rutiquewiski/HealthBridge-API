package br.rutiquewiski.HealthBridge.domain.appointment.Dentist_Appointment;

import br.rutiquewiski.HealthBridge.domain.pacient.Pacient;
import br.rutiquewiski.HealthBridge.domain.professional.Dentist.Dentist;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "appointment_dentist")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Appointment_Dentist {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dentist_id")
    private Dentist dentist;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pacient_id")
    private Pacient pacient;

    private LocalDateTime date;

    private boolean canceled;

    public void cancel(){
        this.canceled = true;
    }
}
