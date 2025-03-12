package br.com.trajy.consultacreditoapi.controller;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;
import static org.springframework.http.ResponseEntity.ok;

import br.com.trajy.consultacreditoapi.assembly.CreditoAssembly;
import br.com.trajy.consultacreditoapi.model.dto.CreditoDTO;
import br.com.trajy.consultacreditoapi.service.CreditoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequestMapping("/creditos")
@RequiredArgsConstructor
public class CreditoController {

    private final CreditoService service;

    private final CreditoAssembly assembly;

    @GetMapping(value = "/credito/{numeroCredito}", produces = APPLICATION_JSON)
    public ResponseEntity<CreditoDTO> getByNumeroCredito(@PathVariable String numeroCredito) {
        return ok(assembly.toResource(service.findByNumeroCredito(numeroCredito)));
    }

    @GetMapping(value = "/{numeroNfse}", produces = APPLICATION_JSON)
    public ResponseEntity<List<CreditoDTO>> getByNumeroNfse(@PathVariable String numeroNfse) {
        return ok(assembly.toResource(service.findByNumeroNfse(numeroNfse)));
    }

}
