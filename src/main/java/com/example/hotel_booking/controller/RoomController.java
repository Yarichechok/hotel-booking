package com.example.hotel_booking.controller;

import com.example.hotel_booking.model.Room;
import com.example.hotel_booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    // Отримати всі доступні номери
    @GetMapping
    public List<Room> getAvailableRooms() {
        return roomService.getAvailableRooms();
    }

    // Отримати номери за id готелю
    @GetMapping("/hotel/{hotelId}")
    public List<Room> getRoomsByHotel(@PathVariable Long hotelId) {
        return roomService.getRoomsByHotel(hotelId);
    }

    // Створити новий номер
    @PostMapping
    public Room createRoom(@RequestBody Room room) {
        return roomService.createRoom(room);
    }

    // Оновити номер за id
    @PutMapping("/{id}")
    public Room updateRoom(@PathVariable Long id, @RequestBody Room room) {
        return roomService.updateRoom(id, room);
    }

    // Видалити номер за id
    @DeleteMapping("/{id}")
    public void deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
    }

    // Забронювати номер
    @PostMapping("/{id}/book")
    public Room bookRoom(@PathVariable Long id) {
        return roomService.bookRoom(id);
    }
}
