package com.metalsa.config;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;

import javax.annotation.PostConstruct;
import java.time.LocalDateTime;

/**
 * Created by IntelliJ Idea
 *
 * @author ruben.bresler
 */
@Configuration
@Log4j
@PropertySource("file:${user.dir}/conf_metalsa/spx_conf.properties")
public class SpxConfiguration {

    private final Environment env;//<R16788>

    public SpxConfiguration(Environment env) {
        this.env = env;
    }

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonBuilder() {
        return b -> b.serializerByType(LocalDateTime.class, new LocalDateTimeConverter());
    }

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

    @PostConstruct
    public void printConfig() {
        if (env instanceof AbstractEnvironment) {
            AbstractEnvironment ae = (AbstractEnvironment) env;
            ae.getSystemProperties().entrySet().forEach(log::debug);
        }
    }
    
    //<R16788>
    public String getProperty(String key) {
        return this.env.getProperty(key);
    }
    //</R16788>
}