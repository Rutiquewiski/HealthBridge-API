package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.pacient.Pacient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacientRepository extends JpaRepository<Pacient, Integer> {

    Page<Pacient> findAllByActiveTrue(Pageable pageable);

    Pacient findByIdAndActiveTrue(Integer id);
}
