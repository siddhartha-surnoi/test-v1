package surnoi.FusionIQ.FusionIQ.service;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate5.Hibernate5Module;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        Hibernate5Module module = new Hibernate5Module();
        module.disable(Hibernate5Module.Feature.USE_TRANSIENT_ANNOTATION);
        mapper.registerModule(module);
        return mapper;
    }

//    @Bean
//    public Module hibernate5Module() {
//        Hibernate5Module module = new Hibernate5Module();
//        module.configure(Hibernate5Module.Feature.FORCE_LAZY_LOADING, false);
//        return module;
//    }
}

