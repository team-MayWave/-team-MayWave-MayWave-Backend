package hs.gsm.maywave.repository;

import hs.gsm.maywave.entity.ChoiceHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChoiceHistoryRepository extends JpaRepository<ChoiceHistory, Long> {
}