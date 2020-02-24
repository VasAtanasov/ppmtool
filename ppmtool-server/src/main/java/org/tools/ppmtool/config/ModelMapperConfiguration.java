package org.tools.ppmtool.config;

import org.modelmapper.AbstractConverter;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration.AccessLevel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class ModelMapperConfiguration {
    private static ModelMapper mapper;

    static {
        mapper = new ModelMapper();
    }

    @Bean
    public ModelMapper modelMapper() {

        mapper.getConfiguration()
                .setFieldAccessLevel(AccessLevel.PRIVATE)
                .setFieldMatchingEnabled(true);

        Converter<LocalDateTime, String> toLocalDate = new AbstractConverter<LocalDateTime, String>() {
            @Override
            protected String convert(LocalDateTime localDateTime) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm");
                return localDateTime == null ? null : localDateTime.format(formatter);
            }
        };

        mapper.addConverter(toLocalDate);
        return mapper;
    }
}
