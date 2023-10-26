package com.app.booking.service;

import com.app.booking.exception.IdNotFoundException;
import com.app.booking.repository.BookingRepository;
import com.app.project.entity.Booking;
import com.app.project.entity.Flight;
import com.app.project.entity.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {
	
	Logger logger = LoggerFactory.getLogger(BookingServiceImpl.class);
	private BookingRepository bookingRepository;
	@Autowired
	private RestTemplate restTemplate;
	@Value("${user-service-url}")
	private String userUrl;
	@Value("${flight-service-url}")
	private String flightUrl;
	
	@Autowired
	public BookingServiceImpl(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	@Override
	public List<Booking> findAll() {
		List<Booking> bookingList = bookingRepository.findAll();
		for(Booking booking : bookingList) {
			booking.setUser(getUserDetails(booking.getUserId()));
			booking.setFlight(getFlightDetails(booking.getFlightId()));
		}
		return bookingList;
	}
	
	@Override
	public Booking findById(int bookingId) {
		Optional<Booking> searchBooking = bookingRepository.findById(bookingId);
		
		Booking booking = null;
		if(searchBooking.isPresent()) {
			booking = searchBooking.get();
			booking.setUser(getUserDetails(booking.getUserId()));
			booking.setFlight(getFlightDetails(booking.getFlightId()));
		} else {
			throw new IdNotFoundException("Did not find the booking id - " + bookingId);
		}
		return booking;
	}
	
	@Override
	public Booking save(Booking booking) {
		Booking savedBooking =  bookingRepository.save(booking);
		savedBooking.setUser(getUserDetails(booking.getUserId()));
		savedBooking.setFlight(getFlightDetails(booking.getFlightId()));
		return savedBooking;
	}
	
	@Override
	public void deleteById(int bookingId) {
		bookingRepository.deleteById(bookingId);
	}
	
	public User getUserDetails(int userId) {
		User user = restTemplate.getForObject(userUrl + userId, User.class);
		return user;
	}
	
	public Flight getFlightDetails(int flightId) {
		Flight flight = restTemplate.getForObject(flightUrl + flightId, Flight.class);
		return flight;
	}
}
