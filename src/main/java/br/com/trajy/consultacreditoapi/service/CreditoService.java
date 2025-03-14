package br.com.trajy.consultacreditoapi.service;

import static br.com.trajy.architecture.restful.exception.utils.ExceptionHandlerUtils.generateEntityNotFoundMessage;
import static java.time.LocalDateTime.now;
import static java.util.Objects.nonNull;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import br.com.trajy.architecture.restful.exception.utils.ExceptionHandlerUtils;
import br.com.trajy.consultacreditoapi.model.entity.AnalyticsModel;
import br.com.trajy.consultacreditoapi.model.entity.Credito;
import br.com.trajy.consultacreditoapi.repository.CreditoRepository;
import br.com.trajy.consultacreditoapi.stream.MessageProducerWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditoService {

    private final CreditoRepository repository;

    private final MessageProducerWrapper messageProducer;

    public Credito findByNumeroCredito(String numeroCredito) {
        Credito entity = repository.findByNumeroCredito(numeroCredito)
                .orElseThrow(() -> new EntityNotFoundException(
                        generateEntityNotFoundMessage(Credito.class, "numeroCredito", numeroCredito)
                ));
        produceMessage(numeroCredito, nonNull(entity));
        return entity;
    }

    public List<Credito> findByNumeroNfse(String numeroNfse) {
        List<Credito> entities = repository.findByNumeroNfse(numeroNfse);
        if(isEmpty(entities)) {
            throw new EntityNotFoundException(generateEntityNotFoundMessage(Credito.class, "numeroNfse", numeroNfse));
        }
        produceMessage(numeroNfse, isEmpty(entities));
        return entities;
    }

    private void produceMessage(String searchValue, Boolean hasResult) {
        messageProducer.send(
                AnalyticsModel.builder()
                        .searchValue(searchValue)
                        .hasResult(hasResult)
                        .searchAt(now())
                        .build()
        );
    }

}
