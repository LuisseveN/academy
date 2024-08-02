package com.ctw.workstation.team.boundary;

import com.ctw.workstation.config.CommonProfile;
import com.ctw.workstation.config.WiremockResource;
import com.ctw.workstation.config.TestConfig;
import com.ctw.workstation.team.control.TeamRepository;
import com.ctw.workstation.team.entity.TeamRecord;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static org.hamcrest.Matchers.equalTo;

@WireMockTest
@QuarkusTest
@TestProfile(CommonProfile.class)
class TeamResourceTest {

    @Inject
    TeamRepository teamRepository;

    @Transactional
    @BeforeEach
    void init(){
        TeamRecord team = new TeamRecord(999L,"teamone","prodone","localone");
        teamRepository.persist(team.toEntity());
    }

    @Test
    void getTeamById() {
        RestAssured.baseURI = "http://localhost:9002";
        RestAssured.get("/workstation/teams/1")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("name", equalTo("teamone"));
    }

    @Test
    void getTeamByIdNull() {
        RestAssured.baseURI = "http://localhost:9002";
        RestAssured.get("/workstation/teams/100")
                .then().assertThat().statusCode(404);
    }

    @Test
    void createTeam(){
        RestAssured.baseURI="http://localhost:9002";


        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", "teamtwo");
        jsonAsMap.put("product", "prodtwo");
        jsonAsMap.put("defaultLocation", "localtwo");

        given().
                contentType(ContentType.JSON).
                body(jsonAsMap).
                when().
                post("/workstation/teams")
                .then().assertThat().statusCode(201)
                .and().contentType(ContentType.JSON)
                .and().body("name", equalTo("teamtwo"));

    }

    @Test
    void getAllTeams(){
        stubFor(get(urlEqualTo("/teams")).willReturn(ok()));
        when().get().then().statusCode(200);
    }
}