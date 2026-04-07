package com.example.test_app_apring.sevice;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Service

public class UserApplicationService {

    private final MessageSource messageSource;

    public UserApplicationService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public Map<String, Integer> getGenderMap(Locale locale) {
        Map<String, Integer> genderMap = new LinkedHashMap<>();
        String male = messageSource.getMessage("male", null, locale);
        String female = messageSource.getMessage("female", null, locale);
        genderMap.put(male, 1);
        genderMap.put(female, 2);
        return genderMap;
    }
}
