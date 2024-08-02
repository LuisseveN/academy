package com.ctw.workstation.rack.entity;

import io.quarkus.test.junit.QuarkusTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
class RackTest {

    @ParameterizedTest
    @MethodSource("provideArguments")
    void toRecord(Long id, String serialNumber, String status, Long teamId, String defaultLocation) {
        //given
        RackRecord a = new RackRecord(id, serialNumber, status, teamId, defaultLocation);
        Rack b = new Rack(serialNumber, status, teamId, defaultLocation);

        //when
        RackRecord c = b.toRecord();

        //then
        assertEquals(a.getStatus(),c.getStatus(),"Validating toRecord method");
        assertEquals(a.getSerialNumber(), c.getSerialNumber(),"");
        assertEquals(a.getTeamId(), c.getTeamId(), "");

    }

    private static Stream<Arguments> provideArguments(){
        return Stream.of(
                Arguments.of("100-200-300","Available",1L),
                Arguments.of(null,"",null)
        );
    }
}