package hs.gsm.maywave.repository;

import hs.gsm.maywave.entity.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScenarioRepository extends JpaRepository<Scenario, Long> {
    Optional<Scenario> findByIdAndRoleId(Long id, Integer roleId);
}