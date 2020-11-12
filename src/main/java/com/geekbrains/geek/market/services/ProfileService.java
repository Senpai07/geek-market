package com.geekbrains.geek.market.services;

import com.geekbrains.geek.market.entities.UserProfile;
import com.geekbrains.geek.market.repositories.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProfileService {
    private final ProfileRepository profileRepository;

    public Optional<UserProfile> findById(Long id) {
        return profileRepository.findById(id);
    }

    public void saveProfile(UserProfile userProfile) {
        profileRepository.save(userProfile);
    }
}