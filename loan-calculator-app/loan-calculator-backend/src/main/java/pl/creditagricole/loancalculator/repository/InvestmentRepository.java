package pl.creditagricole.loancalculator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.creditagricole.loancalculator.model.InvestmentParams;

public interface InvestmentRepository extends JpaRepository<InvestmentParams, Long> {
}
