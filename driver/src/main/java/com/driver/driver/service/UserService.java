package com.driver.driver.service;



import com.driver.driver.Repo.ImageRepo;
//import com.driver.driver.Repo.RoleRepo;
import com.driver.driver.Repo.UserRepo;
import com.driver.driver.model.Image;
import com.driver.driver.model.Role;
//import com.driver.driver.model.RoleClass;
import com.driver.driver.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
//    private final RoleRepo roleRepo;

    private final ImageRepo imageRepo;
    @Bean
    PasswordEncoder passwordEncoder()
    {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
    public User getUserById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.orElseThrow(()->new RuntimeException("User not found")) ;
    }
    public List<User> findAll(){
        return userRepo.findAll();
    }
    public User save(User user) {
        return userRepo.save(user);
    }
    public  User findByEmail(String email) {
        Optional<User> user=userRepo.findByEmail(email);

        return user.orElseThrow(()->new RuntimeException("User not found"));
    }
//    public RoleClass findByName(String email) {
//        Optional<Set<RoleClass>> role=roleRepo.findByName(email);
//
//        return (RoleClass) role.orElseThrow(()->new RuntimeException("User not found"));
//    }
//    public String register(User user) throws Exception {
//        String email = user.getEmail();
//        Optional<User> existingUser = userRepo.findByEmail(email);
//        if (existingUser.isPresent()) {
//            throw new Exception(String.format("User with the email address '%s' already exists.", email));
//        }
//        user.setPassword(passwordEncoder().encode(user.getPassword()));
//        user.setRole(Role.USER);
//         userRepo.save(user);
//        return "redirect:/login"  ;
//    }
    public Boolean Login(String email,String password) {

        if (userRepo.findByEmail(email).isPresent()&&
                userRepo.findByEmail(email).get().getPassword().equals(password)) {

            return true;
        }else
            return false;
    }
    public void delete(Long id) {
        User user = userRepo.findById(id).get();
        userRepo.deleteById(user.getId());
    }
    public User update(User user) {return userRepo.save(user);}

//    public String saveADMINUsers(User user) {
//        user.setPassword(passwordEncoder().encode(user.getPassword()));
//        user.setRole(Role.ADMIN);
//       userRepo.save(user);
//        return "redirect:/login";
//    }

    public void registerUser(User user, Model model) {
        if (userRepo.existsByEmail(user.getEmail())) {
            model.addAttribute("error", "Username already exists");

        }
        user.setPassword(passwordEncoder().encode(user.getPassword()));

        userRepo.save(user);


    }
    public void uploadImageToUser(Long userId, MultipartFile file) throws IOException {
        User user = userRepo.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Image imageFile = new Image();
        imageFile.setData(file.getBytes());
        imageFile.setUser(user);

        imageRepo.save(imageFile);
    }
    public List<Image> getUserImages(Long userId) {
        return imageRepo.findByUserId(userId);
    }
    public Optional<Image> getImageByIdAndUser(Long imageId, Long userId) {
        return imageRepo.findByIdAndUserId(imageId, userId);
    }

}

