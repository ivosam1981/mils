package com.ivosam.mils;

import com.ivosam.mils.domain.*;
import com.ivosam.mils.domain.enums.EstadoPagamento;
import com.ivosam.mils.domain.enums.TipoCliente;
import com.ivosam.mils.repositories.*;
import org.hibernate.boot.jaxb.hbm.spi.JaxbHbmTimestampSourceEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.util.Arrays;

@SpringBootApplication
public class MilsApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MilsApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

    }
}
