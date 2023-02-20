package tech.stl.manager.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.stl.manager.entity.Manager;
import tech.stl.manager.repository.ManagerRepository;
import tech.stl.manager.util.JwtTokenUtil;

@RestController
public class AuthApi {
	
	@Autowired AuthenticationManager authManager;
    @Autowired JwtTokenUtil jwtUtil;
    
    @PostMapping("/auth/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest request) {
        try {
            Authentication authentication = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(), request.getPassword())
            );
            
            Manager user = (Manager) authentication.getPrincipal();
            String accessToken = jwtUtil.generateAccessToken(user);
            AuthResponse response = new AuthResponse(user.getEmailId(), accessToken);
            
            return ResponseEntity.ok().body(response);
            
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
    
    
    @Autowired ManagerRepository managerRepository;
    @Autowired PasswordEncoder passwordEncoder;
    
    @PostMapping("/auth/signup/manager")
    public Manager signin(@RequestBody Manager manager) {
        //patient.addRole(new Role(1,"Patient"));
        String rawPassword = manager.getPassword();
        String encodedPassword = passwordEncoder.encode(rawPassword);
        manager.setPassword(encodedPassword);
        managerRepository.save(manager);
        return manager;
    }

}

