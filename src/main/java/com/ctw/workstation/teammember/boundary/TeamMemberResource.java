package com.ctw.workstation.teammember.boundary;


import com.ctw.workstation.teammember.entity.TeamMemberRecord;
import com.ctw.workstation.teammember.entity.TeamMember;
import com.ctw.workstation.teammember.control.TeamMemberRepository;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/workstation/members")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamMemberResource {
    @Inject
    TeamMemberRepository teamMemberRepository;

    @POST
    @Transactional
    public RestResponse<TeamMemberRecord> createTeamMember(TeamMemberRecord TeamMemberRecord){
        TeamMember TeamMember=TeamMemberRecord.toEntity();
        teamMemberRepository.persist(TeamMember);
        return RestResponse.status(RestResponse.Status.CREATED, TeamMember.toRecord());
    }

    @GET
    public RestResponse<List<TeamMemberRecord>> getAllTeamMembers(){
        return RestResponse.status(RestResponse.Status.OK, teamMemberRepository.findAll().stream().map(TeamMember::toRecord).toList());
    }

    @Path("/{id}")
    @GET
    public RestResponse<TeamMemberRecord> getTeamMemberById(@PathParam("id") Long id){
        TeamMember TeamMember= teamMemberRepository.findById(id);
        return RestResponse.status(RestResponse.Status.OK, TeamMember.toRecord());
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public RestResponse<TeamMemberRecord> editTeamMemberById(@PathParam("id") Long id, TeamMemberRecord newTeamMemberRecord){
        TeamMember TeamMember= teamMemberRepository.findById(id);
        if(TeamMember != null){
            TeamMember.name=newTeamMemberRecord.getName();
            TeamMember.teamId=newTeamMemberRecord.getTeamId();
            TeamMember.ctwId=newTeamMemberRecord.getCtwId();
            teamMemberRepository.persist(TeamMember);
            return RestResponse.status(RestResponse.Status.OK, TeamMember.toRecord());
        }else {
            return RestResponse.status(RestResponse.Status.NOT_FOUND, null);
        }
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public RestResponse<String> removeTeamMemberById(@PathParam("id") Long id){
        TeamMember TeamMember= teamMemberRepository.findById(id);
        if(TeamMember != null){
            teamMemberRepository.delete(TeamMember);
            return RestResponse.status(RestResponse.Status.OK, "TeamMember removed.");
        }else {
            return RestResponse.status(RestResponse.Status.NOT_FOUND, "TeamMember not found.");
        }
    }


}
