package com.example.backspringboot.service;

import com.example.backspringboot.model.Property;
import com.example.backspringboot.model.Rent_review;
import com.example.backspringboot.repository.PropertyRepository;
import com.example.backspringboot.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class PropertyService {

    @Autowired
    private PropertyRepository propertyRepo;

    public List<Property> listAllProperties() {
        return propertyRepo.findAll();
    }

    public Optional<Property> getPropertyById(Long id) {
        return propertyRepo.findById(id);
    }

    public void saveProperty(Property property) {
        propertyRepo.save(property);
    }

    public void deleteProperty(Long id) {
        propertyRepo.deleteById(id);
    }

    public List<Property> getPropertiesByOwner(User user) {
        return propertyRepo.findByOwner(user);
    }
}
