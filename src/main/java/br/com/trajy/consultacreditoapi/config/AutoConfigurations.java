package br.com.trajy.consultacreditoapi.config;

import br.com.trajy.architecture.openapi.OpenApiCoreConfig;
import br.com.trajy.architecture.restful.exception.RestGlobalExecptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Import({
        RestGlobalExecptionHandler.class,
        OpenApiCoreConfig.class
})
@Configuration
public class AutoConfigurations {

}
