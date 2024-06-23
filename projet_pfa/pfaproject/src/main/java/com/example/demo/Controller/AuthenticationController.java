package com.example.demo.Controller;

import com.example.demo.Repository.*;
import com.example.demo.entities.*;
import com.example.demo.playloadRequest.LoginRequest;
import com.example.demo.playloadRequest.SignupRequest;
import com.example.demo.playloadResponse.JwtResponse;
import com.example.demo.playloadResponse.MessageResponse;
import com.example.demo.security.UserDetailsImpl;
import com.example.demo.security.jwt.JwtUtils;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;
    @Autowired
    ParticipantRepository participantRepository;
    @Autowired
    AdminRepository adminRepository;
    @Autowired
    FormateurRepository formateurRepository;


    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String jwt = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                roles,
                userDetails.getEmail()));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        try {
            if (userRepository.existsByUsername(signUpRequest.getUsername())) {
                return ResponseEntity
                        .badRequest()
                        .body(new MessageResponse("Error: Username is already taken!"));
            }

            // Create new user's account
            Utilisateur user = new Utilisateur(
                    signUpRequest.getUsername(),
                    encoder.encode(signUpRequest.getPassword()),
                    signUpRequest.getNom(),
                    signUpRequest.getPrenom(),
                    signUpRequest.getEmail()
            );

            ERole role = signUpRequest.getRole();
            user.setRole(role);
            userRepository.save(user);


            switch (role) {
                case PARTICIPANT:
                    Participant participant = new Participant();
                    participant.setUser(user);
                    participant.setCompetence(signUpRequest.getCompetence());
                    participantRepository.save(participant);
                    break;

                case FORMATEUR:
                    Formateur formateur = new Formateur();
                    formateur.setUser(user);
                    formateur.setCompetences(signUpRequest.getCompetences());
                    formateur.setExperiencesProfessionnelles(signUpRequest.getExperiencesProfessionnelles());
                    formateur.setDateDebutDisponibilite(signUpRequest.getDateDebutDisponibilite());
                    formateur.setHeureDebutDisponibilite(signUpRequest.getHeureDebutDisponibilite());
                    formateur.setDateFinDisponibilite(signUpRequest.getDateFinDisponibilite());
                    formateur.setHeureFinDisponibilite(signUpRequest.getHeureFinDisponibilite());
                    formateurRepository.save(formateur);
                    break;

                case ADMIN:
                    break;

                default:
                    return ResponseEntity.badRequest().body(new MessageResponse("Error: Role is not recognized!"));
            }

            return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MessageResponse("An error occurred during registration."));
        }
    }


    @PostMapping("/sign-out")
    public ResponseEntity<?> logoutUser() {
        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new MessageResponse("You've been signed out!"));
    }




}

