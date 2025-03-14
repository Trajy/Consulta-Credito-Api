package br.com.trajy.consultacreditoapi.config;

import br.com.trajy.architecture.openapi.OpenApiCoreConfig;
import br.com.trajy.architecture.restful.exception.RestGlobalExecptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.kafka.annotation.EnableKafka;

@Import({
        RestGlobalExecptionHandler.class,
        OpenApiCoreConfig.class
})
@EnableKafka
@Configuration
public class AutoConfigurations {

}
