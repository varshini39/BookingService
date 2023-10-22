package com.app.booking.service;

import com.app.booking.entity.Booking;

import java.util.List;

public interface BookingService {
	
	List<Booking> findAll();
	Booking findById(int bookingId);
	Booking save(Booking booking);
	void deleteById(int bookingId);
	
}
