package br.rutiquewiski.HealthBridge.repositories;

import br.rutiquewiski.HealthBridge.domain.professional.dentist.Dentist;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DentistRepository extends JpaRepository<Dentist, Integer> {

    Page<Dentist> findAllByActiveTrue(Pageable pageable);

    Page<Dentist> findAllByActiveTrueAndNameContaining(Pageable pageable, String name);

    Dentist findByIdAndActiveTrue(Integer id);
}
