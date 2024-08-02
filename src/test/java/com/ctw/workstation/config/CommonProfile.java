package com.ctw.workstation.config;

import io.quarkus.test.junit.QuarkusTestProfile;

import java.util.List;

public class CommonProfile implements QuarkusTestProfile {

    @Override
    public List<TestResourceEntry> testResources(){
        return List.of(
                new TestResourceEntry(TestConfig.class),
                new TestResourceEntry(WiremockResource.class)
        );
    }
}
