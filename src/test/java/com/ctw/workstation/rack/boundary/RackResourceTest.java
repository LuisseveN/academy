package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.control.RackRepository;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.RackRecord;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.jboss.resteasy.reactive.RestResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;

class RackResourceTest {

    @Test
    @DisplayName("Validate createRack() from /racks")

    void getAllRacks() {
        RestAssured.baseURI = "http://localhost:9001";
        RestAssured.get("/workstation/racks")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON);
                //.and().body();
    }
}