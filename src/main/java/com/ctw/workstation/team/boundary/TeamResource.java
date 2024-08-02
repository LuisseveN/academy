package com.ctw.workstation.team.boundary;


import com.ctw.workstation.external.ExternalApi;
import com.ctw.workstation.external.ExternalRequest;
import com.ctw.workstation.team.control.TeamRepository;
import com.ctw.workstation.team.entity.Team;
import com.ctw.workstation.team.entity.TeamRecord;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/workstation/teams")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {
    @Inject
    private TeamRepository teamRepository;
    @RestClient
    private ExternalApi externalApi;

    @POST
    @Transactional
    public RestResponse<TeamRecord> createTeam(TeamRecord teamRecord){
        Team team=teamRecord.toEntity();
        teamRepository.persist(team);
        return RestResponse.status(RestResponse.Status.CREATED, team.toRecord());
    }

    @GET
    public RestResponse<List<TeamRecord>> getAllTeams(){
        //externalApi.hello(new ExternalRequest("External API Request"));
        return RestResponse.status(RestResponse.Status.OK, teamRepository.findAll().stream().map(Team::toRecord).toList());
    }

    @Path("/{id}")
    @GET
    public RestResponse<TeamRecord> getTeamById(@PathParam("id") Long id){
        Team team= teamRepository.findById(id);
        return RestResponse.status(RestResponse.Status.OK, team.toRecord());
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public RestResponse<TeamRecord> editTeamById(@PathParam("id") Long id, TeamRecord newTeamRecord){
        Team team= teamRepository.findById(id);
        if(team != null){
            team.name=newTeamRecord.getName();
            team.product=newTeamRecord.getProduct();
            teamRepository.persist(team);
            return RestResponse.status(RestResponse.Status.OK, team.toRecord());
        }else {
            return RestResponse.status(RestResponse.Status.NOT_FOUND, null);
        }
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public RestResponse<String> removeTeamById(@PathParam("id") Long id){
        Team team= teamRepository.findById(id);
        if(team != null){
            teamRepository.delete(team);
            return RestResponse.status(RestResponse.Status.OK, "Team removed.");
        }else {
            return RestResponse.status(RestResponse.Status.NOT_FOUND, "Team not found.");
        }
    }
}
