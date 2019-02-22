package io.jsoft.scrumnotesclient.controllers;

import io.jsoft.scrumnotesclient.models.Project;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ProjectClientController {

    private final RestTemplate restTemplate;

    ProjectClientController(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    @RequestMapping("/message/{projectId}")
    String getMessage(@PathVariable("projectId") Long projectId) {
        Project project = this.restTemplate.getForObject("http://localhost:8080/project/{projectId}", Project.class, projectId);
        return "Hello " + project.getProjectName();
    }
}
