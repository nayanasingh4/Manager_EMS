package tech.stl.manager.jwt;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import tech.stl.manager.entity.Manager;
import tech.stl.manager.repository.ManagerRepository;



@RestController
public class UserApi {
	
@Autowired private ManagerRepository managerRepository;
    
    @PutMapping("/users")
    public ResponseEntity<?> createUser(@RequestBody  Manager manager) {
    	Manager createdUser = managerRepository.save(manager);
        URI uri = URI.create("/users/" + createdUser.getManagerId());
        
        UserDto userDto = new UserDto (createdUser.getManagerId(), createdUser.getEmailId());
        
        return ResponseEntity.created(uri).body(userDto);
    }

}