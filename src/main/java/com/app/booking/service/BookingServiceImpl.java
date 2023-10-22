package com.app.booking.service;

import com.app.booking.entity.Booking;
import com.app.booking.exception.IdNotFoundException;
import com.app.booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
	
	private BookingRepository bookingRepository;
	
	@Autowired
	public BookingServiceImpl(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	@Override
	public List<Booking> findAll() {
		return bookingRepository.findAll();
	}
	
	@Override
	public Booking findById(int bookingId) {
		Optional<Booking> searchBooking = bookingRepository.findById(bookingId);
		
		Booking booking = null;
		if(searchBooking.isPresent()) {
			booking = searchBooking.get();
		} else {
			throw new IdNotFoundException("Did not find the booking id - " + bookingId);
		}
		return booking;
	}
	
	@Override
	public Booking save(Booking booking) {
		return bookingRepository.save(booking);
	}
	
	@Override
	public void deleteById(int bookingId) {
		bookingRepository.deleteById(bookingId);
	}
}
