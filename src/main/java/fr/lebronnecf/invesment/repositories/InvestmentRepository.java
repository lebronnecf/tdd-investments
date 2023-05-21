package fr.lebronnecf.invesment.repositories;

import fr.lebronnecf.invesment.models.InvestmentApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvestmentRepository extends JpaRepository<InvestmentApplication, Integer> {

}
