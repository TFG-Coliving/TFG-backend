package com.example.backspringboot.service;

import com.example.backspringboot.model.Rent;
import com.example.backspringboot.repository.RentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentService {

    @Autowired
    private RentRepository rentRepo;

    public List<Rent> listAllRent() {
        return rentRepo.findAll();
    }

    public void saveRent(Rent rent) {
        rentRepo.save(rent);
    }
}
