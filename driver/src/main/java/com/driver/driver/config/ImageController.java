package com.driver.driver.config;

import com.driver.driver.model.Image;
import com.driver.driver.model.User;
import com.driver.driver.service.ImageService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.security.core.context.SecurityContextHolder;

import java.io.IOException;

@RestController
@RequestMapping("/images")
public class ImageController {

    private final ImageService imageService;

    public ImageController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/upload")
    public String uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        imageService.saveImage(file, userId);
        return "redirect:/home";  // Redirect to home or profile page after upload
    }


    @GetMapping("/images/{imageId}")
    public byte[] viewImage(@PathVariable Long imageId) {
        Long userId = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        return imageService.getImage(userId, imageId);
    }

}
