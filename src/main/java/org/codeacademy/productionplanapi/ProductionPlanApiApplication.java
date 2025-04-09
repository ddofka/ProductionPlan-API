package org.codeacademy.productionplanapi;

import lombok.RequiredArgsConstructor;
import org.codeacademy.productionplanapi.service.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@RequiredArgsConstructor
public class ProductionPlanApiApplication {

    private final VideoService videoService;
    private final DirectorService directorService;
    private final EditorService editorService;
    private final UserService userService;

    public static void main(String[] args) {
        SpringApplication.run(ProductionPlanApiApplication.class, args);
    }

    @EventListener(ApplicationReadyEvent.class)
    public void test(){
        directorService.addTestDirector();
        editorService.addTestEditor();
        userService.addTestUser();
        videoService.addTestData();
    }

}
