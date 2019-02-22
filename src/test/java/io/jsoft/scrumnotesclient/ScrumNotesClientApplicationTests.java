package io.jsoft.scrumnotesclient;

import io.jsoft.scrumnotesclient.models.Project;
import org.assertj.core.api.BDDAssertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.junit.StubRunnerRule;
import org.springframework.cloud.contract.stubrunner.spring.StubRunnerProperties;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ScrumNotesClientApplicationTests {


	@Rule
	public StubRunnerRule stubRunnerRule = new StubRunnerRule()
			.downloadStub("io.jsoft", "scrum-notes-micro-service", "0.0.1-SNAPSHOT", "stubs")
			.withPort(8100)
			.stubsMode(StubRunnerProperties.StubsMode.LOCAL);

	@Test
	public void post_project_from_service_contract() {
		// given:
		RestTemplate restTemplate = new RestTemplate();
		Project project = new Project();
		project.setProjectName("Test Project");
		project.setDescription("Test Project Description");
		project.setStartDate(null);
		project.setEndDate(null);


		// when:
		ResponseEntity<Project> personResponseEntity = restTemplate.postForEntity("http://localhost:8100/projects/add", project, Project.class);

		// then:
		BDDAssertions.then(personResponseEntity.getStatusCodeValue()).isEqualTo(201);
		BDDAssertions.then(personResponseEntity.getBody().getId()).isEqualTo(1l);
		BDDAssertions.then(personResponseEntity.getBody().getProjectName()).isEqualTo("Test Project");
		BDDAssertions.then(personResponseEntity.getBody().getDescription()).isEqualTo("Test Project Description");
	}

	@Test
	public void get_project_from_service_contract() {
		// given:
		RestTemplate restTemplate = new RestTemplate();
		Project project = new Project();
		project.setProjectName("Test Project");
		project.setDescription("Test Project Description");
		project.setStartDate(null);
		project.setEndDate(null);


		// when:
		ResponseEntity<Project> personResponseEntity = restTemplate.getForEntity("http://localhost:8100/projects/1", Project.class);

		// then:
		BDDAssertions.then(personResponseEntity.getStatusCodeValue()).isEqualTo(200);
		BDDAssertions.then(personResponseEntity.getBody().getId()).isEqualTo(1l);
		BDDAssertions.then(personResponseEntity.getBody().getProjectName()).isEqualTo("Test Project");
		BDDAssertions.then(personResponseEntity.getBody().getDescription()).isEqualTo("Test Project Description");
	}

}
