package org.codeacademy.productionplanapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
public class ProductionPlanApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductionPlanApiApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test(){
        // cia paduosim testinius duomenys, kuriant Controller jau nebereiks
    }

}
