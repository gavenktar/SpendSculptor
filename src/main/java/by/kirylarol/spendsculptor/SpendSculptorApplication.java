package by.kirylarol.spendsculptor;

import by.kirylarol.spendsculptor.model.ApiSender;
import by.kirylarol.spendsculptor.model.ApiSenderNanonets;
import by.kirylarol.spendsculptor.model.JsonStringIntoInternalParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class SpendSculptorApplication {


    public static void main(String[] args) throws Exception {
        ApplicationContext applicationContext =  SpringApplication.run(SpendSculptorApplication.class,args);
// File image = new File("C:\\Users\\kirlarla\\Desktop\\STRWEBPR\\New folder\\SpendSculptor\\src\\main\\resources\\image.jpg");
   //     ApiSender apiSender = (ApiSender)applicationContext.getBean(ApiSender.class);
     //   apiSender.send(image);
        File json = new File("src/main/resources/example.json");
        String content = new String(Files.readAllBytes(Path.of("src//main//resources//example.json")));
        JsonStringIntoInternalParser jsonStringIntoInternalParser = new JsonStringIntoInternalParser();
        jsonStringIntoInternalParser.firstParseStageRaw(content).parse();
    }

}
