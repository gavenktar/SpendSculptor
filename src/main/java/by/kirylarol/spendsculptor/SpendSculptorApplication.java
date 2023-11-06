package by.kirylarol.spendsculptor;

import by.kirylarol.spendsculptor.Service.ReceiptService;
import by.kirylarol.spendsculptor.entities.*;
import by.kirylarol.spendsculptor.repos.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.testng.TestNGAntTask;

import java.io.File;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class SpendSculptorApplication {


    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext =  SpringApplication.run(SpendSculptorApplication.class,args);



    }




}
