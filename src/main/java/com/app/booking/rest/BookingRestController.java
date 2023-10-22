package com.app.booking.rest;

import com.app.booking.entity.Booking;
import com.app.booking.exception.IdNotFoundException;
import com.app.booking.service.BookingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bookings")
public class BookingRestController {
	
	private BookingService bookingService;
	
	@Autowired
	public BookingRestController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@GetMapping
	public List<Booking> findAll() {
		return bookingService.findAll();
	}
	
	@GetMapping("/{bookingId}")
	public Booking getBooking(@PathVariable int bookingId) {
		Booking booking = bookingService.findById(bookingId);
		if(booking == null) {
			throw new IdNotFoundException("Booking ID not found - " + bookingId);
		}
		return booking;
	}
	
	@PostMapping
	public Booking addBooking(@RequestBody Booking booking) {
		booking.setPnr_number(0);
		Booking newBooking = bookingService.save(booking);
		return newBooking;
	}
	
	@PutMapping
	public Booking updateBooking(@RequestBody Booking booking) {
		Booking newBooking = bookingService.save(booking);
		return newBooking;
	}
	
	@DeleteMapping("/{bookingId}")
	public String deleteBooking(@PathVariable int bookingId) {
		Booking booking = bookingService.findById(bookingId);
		if(booking == null) {
			throw new IdNotFoundException("Booking ID not found - " + bookingId);
		}
		bookingService.deleteById(bookingId);
		return "Deleted Booking ID - " + bookingId;
	}
}
