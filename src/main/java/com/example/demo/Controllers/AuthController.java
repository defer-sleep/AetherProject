package com.example.demo.Controllers;

import com.example.demo.DataBase.Entities.User.ERole;
import com.example.demo.DataBase.Entities.User.Role;
import com.example.demo.DataBase.Entities.User.User;
import com.example.demo.DataBase.Repos.RoleRepo;
import com.example.demo.DataBase.Repos.UserRepo;
import com.example.demo.DataBase.Request.AddUserRequest;
import com.example.demo.Requests.LoginRequest;
import com.example.demo.Responses.Jwt.JwtResponse;
import com.example.demo.Responses.Message.MessageResponse;
import com.example.demo.Responses.Message.SuccessResponse;
import com.example.demo.Security.Jwt.JwtUtils;
import com.example.demo.Security.Service.UserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private static String ADMIN_KEY = "admin";

    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    UserRepo userRepo;
    @Autowired
    PasswordEncoder passwordEncoder = NoOpPasswordEncoder.getInstance();
    @Autowired
    RoleRepo roleRepo;

    @PostMapping("/login")
    public ResponseEntity<?> authUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt));
    }


    @PostMapping("/init")
    public ResponseEntity<?> init() {
        for (User user : userRepo.findAll()) {
            System.out.println(user);
        }
        if (1 != 1) {
            //userRepo.deleteAll();
            roleRepo.deleteAll();
            roleRepo.save(new Role(ERole.ROLE_ADMIN));
            roleRepo.save(new Role(ERole.ROLE_MODERATOR));
            roleRepo.save(new Role(ERole.ROLE_USER));
            System.out.println("Initialization Was Successful");
        }
        return ResponseEntity.ok(new MessageResponse(
                "Initialization Was Successful",
                300));
    }

    @PostMapping("/addUser")
    public ResponseEntity<?> addUser(@RequestBody AddUserRequest request) {
        System.out.println(request.getUserName() + " " + request.getEmail() + " " + request.getPassword());
        if (userRepo.existsByEmail(request.getEmail())) {
            System.out.println("User exists");
            return ResponseEntity.badRequest().body(new MessageResponse("User exists", 400));
        }

        Set<Role> roles = new HashSet<>();
        if (request.getKey().equals(ADMIN_KEY)) {
            Role adminRole = roleRepo
                    .findByName(ERole.ROLE_ADMIN)
                    .orElseThrow(() -> new RuntimeException("Error, Role ADMIN is not found"));
            roles.add(adminRole);
        }
        userRepo.save(new User(request.getUserName(), request.getEmail(), passwordEncoder.encode(request.getPassword()), roles));
        return ResponseEntity.ok(new SuccessResponse());
    }
}
