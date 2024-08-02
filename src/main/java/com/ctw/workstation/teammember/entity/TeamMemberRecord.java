package com.ctw.workstation.teammember.entity;

import java.time.LocalDate;

public record TeamMemberRecord(Long teamId, String ctwId, String name) {


    public Long getTeamId() {
        return teamId;
    }

    public String getCtwId() {
        return ctwId;
    }

    public String getName() {
        return name;
    }

    public TeamMember toEntity(){
        return new TeamMember(this.teamId,this.ctwId,this.name);
    }
}
