package com.ctw.workstation.rack.boundary;

import com.ctw.workstation.rack.control.RackRepository;
import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.rack.entity.RackRecord;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestResponse;

import java.util.List;

@Path("/workstation/racks")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RackResource {
    @Inject
    RackRepository rackRepository;

    @POST
    @Transactional
    public RestResponse<List<RackRecord>> createRack(RackRecord rackRecord){

        Rack rack = rackRecord.toEntity();
        rackRepository.persist(rack);
        return RestResponse.status(RestResponse.Status.CREATED, rackRepository.findAll().stream().map(Rack::toRecord).toList());
    }

    @GET
    public RestResponse<List<RackRecord>> getAllRacks(@QueryParam("status") String status){
        if(status!=null){
            return RestResponse.status(RestResponse.Status.OK, rackRepository.findAll().stream().map(Rack::toRecord).filter(rackRecord -> rackRecord.getStatus().equals(status)).toList());
        }
        return RestResponse.status(RestResponse.Status.OK, rackRepository.findAll().stream().map(Rack::toRecord).toList());
    }

    @Path("/{id}")
    @GET
    public RestResponse<RackRecord> getRackById(@PathParam("id") Long id){
        Rack rack= rackRepository.findById(id);
        if(rack != null){
            return RestResponse.status(RestResponse.Status.OK, rack.toRecord());
        }else {
            return RestResponse.status(RestResponse.Status.NOT_FOUND, null);
        }
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public RestResponse<RackRecord> editRackById(@PathParam("id") Long id, RackRecord newRackRecord){
        Rack rack= rackRepository.findById(id);
        if(rack != null){
            rack.status=newRackRecord.getStatus();
            rack.teamId=newRackRecord.getTeamId();
            rack.serialNumber=newRackRecord.getSerialNumber();
            rack.defaultLocation=newRackRecord.getDefaultLocation();
            rackRepository.persist(rack);
            return RestResponse.status(RestResponse.Status.OK, rack.toRecord());
        }else {
            return RestResponse.status(RestResponse.Status.NOT_FOUND, null);
        }
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public RestResponse<List<RackRecord>> removeRackById(@PathParam("id") Long id){
        Rack rack= rackRepository.findById(id);
        if(rack != null){
            rackRepository.delete(rack);
            return RestResponse.status(RestResponse.Status.OK, rackRepository.findAll().stream().map(Rack::toRecord).toList());
        }else {
            return RestResponse.status(RestResponse.Status.NOT_FOUND, rackRepository.findAll().stream().map(Rack::toRecord).toList());
        }
    }
}
