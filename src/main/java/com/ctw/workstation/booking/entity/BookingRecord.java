package com.ctw.workstation.booking.entity;

import java.time.LocalDate;

public record BookingRecord(Long requesterId, Long rackId, LocalDate from, LocalDate to){

    public BookingRecord(Long requesterId, Long rackId, LocalDate from, LocalDate to){
        this.requesterId=requesterId;
        this.rackId=rackId;
        this.from=from;
        this.to=to;
    }

    public Long getRequesterId() {
        return requesterId;
    }

    public Long getRackId() {
        return rackId;
    }

    public LocalDate getFrom() {
        return from;
    }

    public LocalDate getTo() {
        return to;
    }

    public Booking toEntity(){
        return new Booking(this.requesterId, this.rackId, this.from, this.to);
    }
}
