package com.ctw.workstation.rack.entity;

public record RackRecord(Long id, String serialNumber, String status, Long teamId, String defaultLocation) {

    private Long getId() { return id;}

    public String getSerialNumber() {
        return serialNumber;
    }

    public String getStatus() {
        return status;
    }

    public Long getTeamId() {
        return teamId;
    }

    public String getDefaultLocation() {
        return defaultLocation;
    }

    public Rack toEntity(){
        return new Rack(this.serialNumber, this.status, this.teamId, this.defaultLocation );
    }
}
