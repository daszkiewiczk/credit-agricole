package pl.creditagricole.loancalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.creditagricole.loancalculator.model.AgroParams;

public interface AgroRepository extends JpaRepository<AgroParams, Long> {
}
