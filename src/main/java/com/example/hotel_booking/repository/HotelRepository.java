package com.example.hotel_booking.repository;

import com.example.hotel_booking.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel findByName(String name);
    List<Hotel> findByRooms_SizeGreaterThanEqual(int numberOfRooms);
}
