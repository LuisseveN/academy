package com.ctw.workstation.team.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_TEAM")
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamIdGenerator")
    @SequenceGenerator(name = "teamIdGenerator",sequenceName = "SEQ_TEAM_ID")
    public Long id;
    @Column(name = "NAME")
    public String name;
    @Column(name = "PRODUCT")
    public String product;
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_AT")
    public LocalDate createdAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFIED_AT")
    public LocalDate modifiedAt;
    @Column(name = "DEFAULT_LOCATION")
    public String defaultLocation;


    public Team(String name,String product, String defaultLocation) {
        this.name=name;
        this.product=product;
        this.defaultLocation=defaultLocation;
    }

    public Team() {

    }

    public TeamRecord toRecord(){
        return new TeamRecord(this.id, this.name,this.product,this.defaultLocation);
    }

}
