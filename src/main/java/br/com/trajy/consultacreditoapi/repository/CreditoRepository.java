package br.com.trajy.consultacreditoapi.repository;

import br.com.trajy.consultacreditoapi.model.entity.Credito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface CreditoRepository extends JpaRepository<Credito, Long> {

    Optional<Credito> findByNumeroCredito(String numeroCredito);

    List<Credito> findByNumeroNfse(String numeroNfse);

}
