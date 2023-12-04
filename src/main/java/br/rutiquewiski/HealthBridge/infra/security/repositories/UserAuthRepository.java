package br.rutiquewiski.HealthBridge.infra.security.repositories;

import br.rutiquewiski.HealthBridge.infra.security.domain.UserAuth;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthRepository extends JpaRepository<UserAuth, Long> {

    UserDetails findByUsername(String username);
}
