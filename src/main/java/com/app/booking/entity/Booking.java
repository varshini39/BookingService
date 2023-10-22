package com.app.booking.entity;

import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name="booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="pnr_number")
    private int pnr_number;
    @JoinColumn(name="user_id")
    private int userId;
    @JoinColumn(name="flight_id")
    private int flightId;
    @Column(name="booking_date")
    private Date booking_date;
    @Column(name="seats")
    private String seats;
    @Column(name="meal_required")
    private boolean meal_required;
    @Column(name="booking_status")
    private String booking_status;

    public Booking() {  }

    public Booking(int userId, int flightId, Date booking_date, String seats, boolean meal_required,
                   String booking_status) {
        this.userId = userId;
        this.flightId = flightId;
        this.booking_date = booking_date;
        this.seats = seats;
        this.meal_required = meal_required;
        this.booking_status = booking_status;
    }

    public int getPnr_number() {
        return pnr_number;
    }

    public void setPnr_number(int pnr_number) {
        this.pnr_number = pnr_number;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getFlightId() {
        return flightId;
    }

    public void setFlightId(int flightId) {
        this.flightId = flightId;
    }

    public Date getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(Date booking_date) {
        this.booking_date = booking_date;
    }

    public String getSeats() {
        return seats;
    }

    public void setSeats(String seats) {
        this.seats = seats;
    }

    public boolean isMeal_required() {
        return meal_required;
    }

    public void setMeal_required(boolean meal_required) {
        this.meal_required = meal_required;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public void setBooking_status(String booking_status) {
        this.booking_status = booking_status;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "pnr_number=" + pnr_number +
                ", user=" + userId +
                ", flight_id=" + flightId +
                ", booking_date=" + booking_date +
                ", seats='" + seats + '\'' +
                ", meal_required=" + meal_required +
                ", booking_status='" + booking_status + '\'' +
                '}';
    }
}
