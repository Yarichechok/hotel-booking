package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Hotel;
import com.example.hotel_booking.model.Room;
import com.example.hotel_booking.repository.HotelRepository;
import com.example.hotel_booking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HotelService {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private RoomRepository roomRepository;

    // Отримати всі готелі
    public List<Hotel> getAllHotels() {
        return hotelRepository.findAll();
    }

    // Отримати готель за id
    public Hotel getHotelById(Long id) {
        Optional<Hotel> hotel = hotelRepository.findById(id);
        return hotel.orElseThrow(() -> new RuntimeException("Готель не знайдений"));
    }

    // Створити новий готель
    public Hotel createHotel(Hotel hotel) {
        if (hotelRepository.findByName(hotel.getName()) != null) {
            throw new RuntimeException("Готель з таким ім'ям вже існує");
        }
        return hotelRepository.save(hotel);
    }

    // Оновити готель
    public Hotel updateHotel(Long id, Hotel hotel) {
        if (!hotelRepository.existsById(id)) {
            throw new RuntimeException("Готель не знайдений");
        }
        hotel.setId(id);
        return hotelRepository.save(hotel);
    }

    // Видалити готель
    public void deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) {
            throw new RuntimeException("Готель не знайдений");
        }
        hotelRepository.deleteById(id);
    }

    // Отримати доступні номери в готелі
    public List<Room> getAvailableRoomsInHotel(Long id) {
        Hotel hotel = getHotelById(id);
        List<Room> rooms = roomRepository.findByHotelId(id);
        return rooms.stream()
                .filter(Room::isAvailable)
                .collect(Collectors.toList());
    }
}
