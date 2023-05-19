package com.example.backspringboot.service;

import com.example.backspringboot.model.Room;
import com.example.backspringboot.repository.RentRepository;
import com.example.backspringboot.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepo;

    public Optional<Room> getRoomById(Long id) {
        return roomRepo.findById(id);
    }

    public void save(Room room) {
        roomRepo.save(room);
    }

}
