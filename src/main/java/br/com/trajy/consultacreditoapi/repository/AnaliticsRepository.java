package br.com.trajy.consultacreditoapi.repository;

import br.com.trajy.consultacreditoapi.model.entity.AnalyticsModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnaliticsRepository extends JpaRepository<AnalyticsModel, Long> {

}
