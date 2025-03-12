package br.com.trajy.consultacreditoapi.service;

import br.com.trajy.consultacreditoapi.model.entity.Credito;
import br.com.trajy.consultacreditoapi.repository.CreditoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditoService {

    private final CreditoRepository repository;

    // TODO - implementar mensageria.
    public Credito findByNumeroCredito(String numeroCredito) {
        return repository.findByNumeroCredito(numeroCredito);
    }

    public List<Credito> findByNumeroNfse(String numeroNfse) {
        return repository.findByNumeroNfse(numeroNfse);
    }

}
