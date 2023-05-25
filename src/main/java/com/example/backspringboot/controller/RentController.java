package com.example.backspringboot.controller;

import com.example.backspringboot.model.Rent;
import com.example.backspringboot.model.Room;
import com.example.backspringboot.service.RentService;
import com.example.backspringboot.service.RoomService;
import com.example.backspringboot.user.User;
import com.example.backspringboot.user.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/rent")
@RequiredArgsConstructor
public class RentController {

    private final RentService rentService;
    private final UserService userService;
    private final RoomService roomService;

    @GetMapping
    public List<Rent> listAllRents() {
        return rentService.listAllRent();
    }

    @PostMapping("{id}")
    public ResponseEntity<Rent> addRent(@RequestBody Rent rent, @PathVariable("id") Long id) {
        try {
            User currentUser = userService.getCurrentUser();
            Optional<Room> optRoom = roomService.getRoomById(id);
            if(optRoom.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            rent.setUser(currentUser);
            Room room = optRoom.get();
            room.getRents().add(rent);
            rent.setPrice(room.getPrice());
            LocalDateTime localDateTime = LocalDateTime.now();
            ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
            Instant instant = zonedDateTime.toInstant();
            rent.setBooking_date(Date.from(instant));
            roomService.save(room);
            return ResponseEntity.ok(rent);
        } catch (Exception ignore) {
            System.err.print(ignore);
            return ResponseEntity.badRequest().build();
        }
    }
}
