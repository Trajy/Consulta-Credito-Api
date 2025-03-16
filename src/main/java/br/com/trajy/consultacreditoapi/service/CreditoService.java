package br.com.trajy.consultacreditoapi.service;

import static br.com.trajy.architecture.restful.exception.utils.ExceptionHandlerUtils.generateEntityNotFoundMessage;
import static java.time.LocalDateTime.now;
import static org.apache.commons.collections4.CollectionUtils.isEmpty;

import br.com.trajy.consultacreditoapi.model.entity.AnalyticsModel;
import br.com.trajy.consultacreditoapi.model.entity.Credito;
import br.com.trajy.consultacreditoapi.repository.CreditoRepository;
import br.com.trajy.consultacreditoapi.stream.MessageProducerWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CreditoService {

    private final CreditoRepository repository;

    private final MessageProducerWrapper messageProducer;

    public Credito findByNumeroCredito(String numeroCredito) {
        Optional<Credito> optionalEntity = repository.findByNumeroCredito(numeroCredito);
        produceMessage(numeroCredito, optionalEntity.isPresent());
        return optionalEntity.orElseThrow(() -> new EntityNotFoundException(
                generateEntityNotFoundMessage(Credito.class, "numeroCredito", numeroCredito)
        ));
    }

    public List<Credito> findByNumeroNfse(String numeroNfse) {
        List<Credito> entities = repository.findByNumeroNfse(numeroNfse);
        produceMessage(numeroNfse, isEmpty(entities));
        if(isEmpty(entities)) {
            throw new EntityNotFoundException(generateEntityNotFoundMessage(Credito.class, "numeroNfse", numeroNfse));
        }
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
