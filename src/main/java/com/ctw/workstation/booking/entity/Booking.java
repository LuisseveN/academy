package com.ctw.workstation.booking.entity;

import com.ctw.workstation.rack.entity.Rack;
import com.ctw.workstation.teammember.entity.TeamMember;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "T_BOOKING")
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookingIdGenerator")
    @SequenceGenerator(name = "bookingIdGenerator",sequenceName = "SEQ_BOOKING_ID")
    public Long id;
    @Column(name = "REQUESTER_ID")
    public Long requesterId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "REQUESTER_ID", updatable = false, insertable = false, nullable = false)
    public TeamMember requester;
    @Column(name = "RACK_ID")
    public Long rackId;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RACK_ID", updatable = false, insertable = false, nullable = false)
    public Rack rack;
    @Temporal(TemporalType.DATE)
    @Column(name = "BOOK_FROM")
    public LocalDate from;
    @Temporal(TemporalType.DATE)
    @Column(name = "BOOK_TO")
    public LocalDate to;

   public Booking(Long requesterId, Long rackId,LocalDate from, LocalDate to){

   }

    public Booking() {

    }

   public BookingRecord toRecord(){
       return new BookingRecord(this.requesterId, this.rackId, this.from, this.to);
   }
}
