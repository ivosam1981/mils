package com.ivosam.mils.config;

import com.ivosam.mils.services.DBService;
import com.ivosam.mils.services.EmailService;
import com.ivosam.mils.services.MockEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("test")
public class TesteConfig {

    @Autowired
    private DBService dbService;

    @Bean
    public boolean instantiateDataBase() throws ParseException {

        dbService.instantiateTestDataBase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new MockEmailService();
    }

}
