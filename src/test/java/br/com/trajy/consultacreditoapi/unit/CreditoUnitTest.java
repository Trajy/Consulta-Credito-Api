package br.com.trajy.consultacreditoapi.unit;

import static br.com.trajy.consultacreditoapi.util.TestRunUtil.ignoreThrows;
import static org.junit.jupiter.api.Assertions.assertThrows;

import br.com.trajy.consultacreditoapi.repository.CreditoRepository;
import br.com.trajy.consultacreditoapi.service.CreditoService;
import br.com.trajy.consultacreditoapi.stream.MessageProducerWrapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.function.Executable;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CreditoUnitTest {

    @InjectMocks
    private CreditoService service;

    @Mock
    CreditoRepository repository;

    @Mock
    MessageProducerWrapper messageProducer;

    @Test
    @DisplayName("Deve lançar EntityNotFoundExcepetion quando nao encontrar dados pelo numero de Credito")
    void deveLancarExceptionQuandoNaoEncontrarDadosPeloNumeroCredito() {

        //Arrange
        Mockito.when(repository.findByNumeroCredito(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        //Action
        Executable action = () -> service.findByNumeroCredito("anyValue");

        //Assert
        assertThrows(EntityNotFoundException.class, action);

    }

    @Test
    @DisplayName("Deve lançar EntityNotFoundExcepetion quando nao encontrar dados pelo numero Nfse")
    void deveLancarExceptionQuandoNaoEncontrarDadosPeloNumeroNfse() {

        //Arrange
        Mockito.when(repository.findByNumeroNfse(ArgumentMatchers.any()))
                .thenReturn(new ArrayList<>());

        //Action
        Executable action = () -> service.findByNumeroNfse("anyValue");

        //Assert
        assertThrows(EntityNotFoundException.class, action);

    }

    @Test
    @DisplayName("Deve enviar notificacao quando nao encontrar resultados pelo numero de Credito")
    void deveEnviarNotificationQuandoDadosNaoEcontradosPorNumeroCredito() {

        //Arrange
        Mockito.when(repository.findByNumeroCredito(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        //Action
        ignoreThrows(() -> service.findByNumeroCredito("anyValue"));

        //Assert
        Mockito.verify(messageProducer).send(ArgumentMatchers.any());
    }

    @Test
    @DisplayName("Deve enviar notificacao quando nao encontrar resultados pelo numero Nfse")
    void deveEnviarNotificationQuandoDadosNaoEcontradosPorNumeroNfse() {

        //Arrange
        Mockito.when(repository.findByNumeroNfse(ArgumentMatchers.any()))
                .thenReturn(new ArrayList<>());

        //Action
        ignoreThrows(() -> service.findByNumeroNfse("anyValue"));

        //Assert
        Mockito.verify(messageProducer).send(ArgumentMatchers.any());
    }

}
