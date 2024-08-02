package com.ctw.workstation.teammember.entity;

import com.ctw.workstation.team.entity.Team;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_TEAM_MEMBER")
public class TeamMember {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "teamMemberIdGenerator")
    @SequenceGenerator(name = "teamMemberIdGenerator",sequenceName = "SEQ_TEAM_MEMBER_ID")
    public Long id;
    @Column(name = "TEAM_ID")
    public Long teamId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TEAM_ID", insertable = false, nullable = false, updatable = false)
    public Team team;
    @Column(name = "CTW_ID")
    public String ctwId;
    @Column(name = "NAME")
    public String name;
    @Temporal(TemporalType.DATE)
    @Column(name = "CREATED_AT")
    public LocalDate createdAt;
    @Temporal(TemporalType.DATE)
    @Column(name = "MODIFIED_AT")
    public LocalDate modifiedAt;


    public TeamMember(Long teamId,String ctwId, String name){
        this.teamId=teamId;
        this.ctwId=ctwId;
        this.name=name;
    }

    public TeamMember() {

    }

    public TeamMemberRecord toRecord(){
        return new TeamMemberRecord(this.teamId, this.ctwId,this.name);
    }
}
