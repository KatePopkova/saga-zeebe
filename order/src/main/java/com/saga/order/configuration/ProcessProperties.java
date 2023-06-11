package com.saga.order.configuration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Configuration
@ConstructorBinding
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "bpmn-processes")
public class ProcessProperties {
    @NotEmpty
    private String process;
}

