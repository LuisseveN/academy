package com.ctw.workstation.rack.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RackRecordTest {

    @Test
    void toEntity() {
        //given
        RackRecord a = new RackRecord(10L,"100-200-300","Available",1L,"Lisboa");
        Rack b = new Rack("100-200-300", "Available", 1L,"Porto");

        Rack c = a.toEntity();

        assertEquals(a.getStatus(),c.status);
        assertEquals(a.getSerialNumber(), c.serialNumber);
        assertEquals(a.getTeamId(), c.teamId);
    }
}