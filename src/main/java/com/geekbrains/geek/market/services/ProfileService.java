package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.Category;
import com.geekbrains.geek.market.entities.Profile;
import com.geekbrains.geek.market.exceptions.ResourceNotFoundException;
import com.geekbrains.geek.market.repositories.CategoryRepository;
import com.geekbrains.geek.market.repositories.ProductRepository;
import com.geekbrains.geek.market.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public Optional<Profile> findById(Long id) {
        return profileRepository.findById(id);
    }

    public Optional<Profile> findByUsername(String username) {
        return profileRepository.findByUsername(username);
    }

    public Profile saveOrUpdate(Profile profile) {
        return profileRepository.save(profile);
    }
}
