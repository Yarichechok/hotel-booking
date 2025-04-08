package com.example.hotel_booking.repository;

import com.example.hotel_booking.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {

    Booking findByCustomerName(String customerName);
}
