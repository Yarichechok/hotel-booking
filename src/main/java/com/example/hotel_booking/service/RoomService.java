package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Room;
import com.example.hotel_booking.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    // Отримати всі доступні номери
    public List<Room> getAvailableRooms() {
        return roomRepository.findByAvailableTrue();
    }

    // Отримати номери за готелем
    public List<Room> getRoomsByHotel(Long hotelId) {
        return roomRepository.findByHotelId(hotelId);
    }
    public Room getRoomById(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        return room.orElseThrow(() -> new RuntimeException("Номер не знайдений"));
    }

    // Створити номер
    public Room createRoom(Room room) {
        if (roomRepository.existsById(room.getId())) {
            throw new RuntimeException("Номер з таким id вже існує");
        }
        return roomRepository.save(room);
    }

    // Оновити номер
    public Room updateRoom(Long id, Room room) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Номер не знайдений");
        }
        room.setId(id);
        return roomRepository.save(room);
    }

    // Видалити номер
    public void deleteRoom(Long id) {
        if (!roomRepository.existsById(id)) {
            throw new RuntimeException("Номер не знайдений");
        }
        roomRepository.deleteById(id);
    }

    // Забронювати номер
    public Room bookRoom(Long id) {
        Optional<Room> room = roomRepository.findById(id);
        if (room.isEmpty() || !room.get().isAvailable()) {
            throw new RuntimeException("Номер недоступний для бронювання");
        }
        Room r = room.get();
        r.setAvailable(false);
        return roomRepository.save(r);
    }
}
