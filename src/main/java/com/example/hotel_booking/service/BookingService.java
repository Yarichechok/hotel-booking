package com.example.hotel_booking.service;

import com.example.hotel_booking.model.Booking;
import com.example.hotel_booking.repository.BookingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;

    // Отримати всі бронювання
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    // Отримати бронювання за id
    public Booking getBookingById(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        return booking.orElseThrow(() -> new RuntimeException("Бронювання не знайдено"));
    }

    // Створити нове бронювання
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

    // Оновити бронювання
    public Booking updateBooking(Long id, Booking booking) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Бронювання не знайдено");
        }
        booking.setId(id);
        return bookingRepository.save(booking);
    }

    // Видалити бронювання
    public void deleteBooking(Long id) {
        if (!bookingRepository.existsById(id)) {
            throw new RuntimeException("Бронювання не знайдено");
        }
        bookingRepository.deleteById(id);
    }

    // Скасувати бронювання
    public void cancelBooking(Long id) {
        Optional<Booking> booking = bookingRepository.findById(id);
        if (booking.isPresent()) {
            Booking b = booking.get();
            b.setRoom(null); // Скасувати бронювання, прибираючи зв'язок з номером
            bookingRepository.save(b);
        } else {
            throw new RuntimeException("Бронювання не знайдено для скасування");
        }
    }

    // Отримати всі бронювання по імені клієнта
    public List<Booking> getBookingsByCustomerName(String customerName) {
        return bookingRepository.findByCustomerName(customerName);
    }
}
