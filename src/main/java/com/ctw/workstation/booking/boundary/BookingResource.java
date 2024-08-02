package com.ctw.workstation.booking.boundary;


import com.ctw.workstation.booking.control.BookingRepository;
import com.ctw.workstation.booking.entity.Booking;
import com.ctw.workstation.booking.entity.BookingRecord;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.logging.Logger;
import org.jboss.logging.MDC;
import org.jboss.resteasy.reactive.RestResponse;
import org.slf4j.LoggerFactory;

import java.util.List;

@Path("/workstation/bookings")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BookingResource {
    @Inject
    BookingRepository bookingRepository;

    Logger log = Logger.getLogger(BookingResource.class.getName());

    @POST
    @Transactional
    public RestResponse<BookingRecord> createBooking(BookingRecord bookingRecord) {
        Booking booking = bookingRecord.toEntity();
        bookingRepository.persist(booking);
        String id="2";
        log.infof("[%{0}]Created a Booking with id: %{0}", id);
        MDC.put("id",id);
        return RestResponse.status(RestResponse.Status.CREATED, booking.toRecord());
    }

    @GET
    public RestResponse<List<BookingRecord>> getAllBookings(){
        return RestResponse.status(RestResponse.Status.OK, bookingRepository.findAll().stream().map(Booking::toRecord).toList());
    }

    @Path("/{id}")
    @GET
    public RestResponse<BookingRecord> getBookingById(@PathParam("id") Long id){
        BookingRecord bookingRecord = bookingRepository.findById(id).toRecord();
        if(bookingRecord != null){
            return RestResponse.status(RestResponse.Status.OK, bookingRecord);
        }else {
            return RestResponse.status(RestResponse.Status.NOT_FOUND, null);
        }
    }

    @Path("/{id}")
    @PUT
    @Transactional
    public RestResponse<BookingRecord> editBookingById(@PathParam("id") Long id, BookingRecord newBookingRecord){
        Booking booking= bookingRepository.findById(id);
        if(booking != null){
            booking.requesterId=newBookingRecord.getRequesterId();
            booking.rackId=(newBookingRecord.getRackId());
            booking.from=newBookingRecord.getFrom();
            booking.to=newBookingRecord.getTo();
            bookingRepository.persist(booking);
            return RestResponse.status(RestResponse.Status.OK, booking.toRecord());
        }else {
            return RestResponse.status(RestResponse.Status.NOT_FOUND, null);
        }
    }

    @Path("/{id}")
    @DELETE
    @Transactional
    public RestResponse<String> removeBookingById(@PathParam("id") Long id){
        Booking b = bookingRepository.findById(id);
        if(b != null){
            bookingRepository.delete(b);
            return RestResponse.status(RestResponse.Status.OK, "Booking removed.");
        }else {
            return RestResponse.status(RestResponse.Status.NOT_FOUND, "Booking not found.");
        }
    }

}
