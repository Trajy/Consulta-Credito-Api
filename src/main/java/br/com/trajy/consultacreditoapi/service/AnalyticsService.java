package br.com.trajy.consultacreditoapi.service;

import br.com.trajy.architecture.layer.service.ServiceAbstract;
import br.com.trajy.consultacreditoapi.model.entity.AnalyticsModel;
import br.com.trajy.consultacreditoapi.repository.AnaliticsRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
public class AnalyticsService extends ServiceAbstract<Long, AnalyticsModel> {

    private final AnaliticsRepository repository;

}
