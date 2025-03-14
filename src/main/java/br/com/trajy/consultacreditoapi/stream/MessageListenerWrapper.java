package br.com.trajy.consultacreditoapi.stream;

import br.com.trajy.consultacreditoapi.model.entity.AnalyticsModel;
import br.com.trajy.consultacreditoapi.service.AnalyticsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@RequiredArgsConstructor
public class MessageListenerWrapper {

    private final AnalyticsService service;

    @KafkaListener(topics = "SEARCH_ANALITICS", groupId = "cadastro-api-group")
    public void listen(AnalyticsModel message) {
        log.info("Analytics Received: {}", message);
        service.save(message);
    }

}
