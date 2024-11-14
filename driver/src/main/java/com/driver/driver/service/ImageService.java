package com.driver.driver.service;
import com.driver.driver.Repo.ImageRepo;
import com.driver.driver.Repo.UserRepo;
import com.driver.driver.model.Image;
import com.driver.driver.model.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class ImageService {

    private final ImageRepo imageRepository;
    private final UserRepo userRepository;

    public ImageService(ImageRepo imageRepository, UserRepo userRepository) {
        this.imageRepository = imageRepository;
        this.userRepository = userRepository;
    }

    // Save uploaded image associated with the user
    public void saveImage(MultipartFile file, Long userId) throws IOException {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Image image = new Image();
        image.setData(file.getBytes());  // Store image as binary data
        image.setUser(user);  // Associate image with the logged-in user
        imageRepository.save(image);
    }
    public List<Image> getUserImages(Long userId) {
        return imageRepository.findByUserId(userId);
    }
    // Retrieve an image associated with the user
    public byte[] getImage(Long userId, Long imageId) {
        Image image = imageRepository.findByIdAndUserId(imageId, userId)
                .orElseThrow(() -> new RuntimeException("Image not found or not accessible by the user"));
        return image.getData();
    }
}
