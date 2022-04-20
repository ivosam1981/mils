package com.ivosam.mils.config;

import com.ivosam.mils.services.DBService;
import com.ivosam.mils.services.EmailService;
import com.ivosam.mils.services.SmptEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.text.ParseException;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DBService dbService;

    @Value("${spring.jpa.hibernate.ddl-auto}")
    private String strategy;

    @Bean
    public boolean instantiateDataBase() throws ParseException {
        if(!"create".equals(strategy)){
            return false;
        }

        dbService.instantiateTestDataBase();
        return true;
    }

    @Bean
    public EmailService emailService(){
        return new SmptEmailService();
    }


}
