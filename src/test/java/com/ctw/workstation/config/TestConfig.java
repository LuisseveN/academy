package com.ctw.workstation.config;


import io.quarkus.logging.Log;
import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.Map;

public class TestConfig implements QuarkusTestResourceLifecycleManager {

    PostgreSQLContainer container = new PostgreSQLContainer<>("postgres:latest");

    @Override
    public Map<String, String> start() {
        container.withUsername("postgres").withPassword("postgres").withDatabaseName("fs_academy").start();
        Log.infov("%s","START");
        return Map.of("quarkus.datasource.username",container.getUsername(),
                "quarkus.datasource.password",container.getPassword(),
                "quarkus.datasource.jdbc.url",container.getJdbcUrl());
    }

    @Override
    public void stop() {
        container.stop();
        Log.infov("%s","STOP");
    }
}
