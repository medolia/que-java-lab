package com.medolia.demo.retry.spring.cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

/**
 * @author lbli
 * @date 2021/11/13
 */
@CucumberContextConfiguration
@SpringBootTest
public class ApplicationIntegrationClass {
    @When("^the client calls /version$")
    public void the_client_issues_GET_version() throws Throwable {
        // executeGet("http://localhost:8080/version");
    }

    @Then("^the client receives status code of (\\d+)$")
    public void the_client_receives_status_code_of(int statusCode) throws Throwable {
        assertEquals(statusCode, 200);
    }

    @And("^the client receives server version (.+)$")
    public void the_client_receives_server_version_body(String version) throws Throwable {
        assertEquals(version, "1.0");

    }
}
