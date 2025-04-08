package com.example.hotel_booking.repository;

import com.example.hotel_booking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {

    Hotel findByName(String name);
}
