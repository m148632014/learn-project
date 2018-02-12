package org.mfm.learn.springhateoas.json;


import java.time.LocalDateTime;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.module.SimpleSerializers;

@Component
public class JacksonModule extends SimpleModule {
    private JacksonModule() {
        super("JacksonModule", new Version(1, 0, 0, "", "", ""));
    }

    @Override
    public void setupModule(SetupContext setupContext) {
        SimpleSerializers serializers = new SimpleSerializers();
        serializers.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        setupContext.addSerializers(serializers);
    }
}
