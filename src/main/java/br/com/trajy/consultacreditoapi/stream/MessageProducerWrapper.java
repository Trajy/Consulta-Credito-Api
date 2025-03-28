package br.com.trajy.consultacreditoapi.stream;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MessageProducerWrapper {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Async
    public <T> void send(T message) {
        kafkaTemplate.send("SEARCH_ANALITICS", message);
    }

}
