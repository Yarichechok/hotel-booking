package com.example.hotel_booking.controller;

import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.model.Room;
import com.example.hotel_booking.service.BookingService;
import com.example.hotel_booking.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private RoomService roomService;

    // Отримати всі бронювання
    @GetMapping
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

    // Отримати бронювання за id
    @GetMapping("/{id}")
    public Booking getBookingById(@PathVariable Long id) {
        return bookingService.getBookingById(id);
    }

    // Створити нове бронювання
    @PostMapping
    public Booking createBooking(@RequestBody Booking booking) {
        // Перевірка доступності номера перед створенням бронювання
        Room room = roomService.getRoomById(booking.getRoom().getId());
        if (room != null && room.isAvailable()) {
            return bookingService.createBooking(booking);
        } else {
            throw new RuntimeException("Номер недоступний для бронювання");
        }
    }

    // Оновити бронювання за id
    @PutMapping("/{id}")
    public Booking updateBooking(@PathVariable Long id, @RequestBody Booking booking) {
        return bookingService.updateBooking(id, booking);
    }

    // Видалити бронювання за id
    @DeleteMapping("/{id}")
    public void deleteBooking(@PathVariable Long id) {
        bookingService.deleteBooking(id);
    }

    // Скасувати бронювання
    @PostMapping("/{id}/cancel")
    public void cancelBooking(@PathVariable Long id) {
        bookingService.cancelBooking(id);
    }

    // Отримати всі бронювання для конкретного клієнта
    @GetMapping("/customer/{customerName}")
    public List<Booking> getBookingsByCustomerName(@PathVariable String customerName) {
        return bookingService.getBookingsByCustomerName(customerName);
    }
}
