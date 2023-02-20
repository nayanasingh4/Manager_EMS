package tech.stl.manager.jwt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tech.stl.manager.entity.Manager;
import tech.stl.manager.repository.ManagerRepository;


@Service
@Transactional
public class UserService {
	@Autowired
	private ManagerRepository managerRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	 

	public Manager save(Manager manager) {
	        String rawPassword = manager.getPassword();
	        String encodedPassword = passwordEncoder.encode(rawPassword);
	        manager.setPassword(encodedPassword);
	        
	        return managerRepository.save(manager);
	    }
}
