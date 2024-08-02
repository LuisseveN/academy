package com.ctw.workstation.rack.entity;

import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name="T_RACK")
public class Rack {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rackIdGenerator")
    @SequenceGenerator(name = "rackIdGenerator",sequenceName = "SEQ_RACK_ID")
    public Long id;
    @Column(name = "SERIAL_NUMBER",length = 16, nullable = false)
    public String serialNumber;
    @Column(name = "STATUS", length = 20)
    public String status;
    @Column(name = "TEAM_ID")
    public Long teamId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="TEAM_ID", updatable = false, insertable = false, nullable = false)
    public Team team;
    @Temporal(TemporalType.DATE)
    @Column(name = "ASSEMBLED_AT")
    public LocalDate assembledAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_AT")
    public LocalDate createdAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFIED_AT")
    public LocalDate modifiedAt;
    @Column(name = "DEFAULT_LOCATION")
    public String defaultLocation;


    public Rack(String serialNumber, String status, Long teamId, String defaultLocation){
        this.serialNumber=serialNumber;
        this.status=status;
        this.teamId = teamId;
        this.defaultLocation=defaultLocation;
    }

    public Rack() {

    }

    public RackRecord toRecord(){
        return new RackRecord(this.id, this.serialNumber, this.status, this.teamId, this.defaultLocation);
    }

}
