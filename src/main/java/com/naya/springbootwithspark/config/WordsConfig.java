package com.naya.springbootwithspark.config;

import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Configuration
public class WordsConfig {

    @SneakyThrows
    @Bean
    public List<String> forbiddenWords(@Value("${ignorelist.path}") String filePath){
        return Files.lines(Path.of(filePath))
                .map(String::toLowerCase)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .collect(Collectors.toList());
    }
}
