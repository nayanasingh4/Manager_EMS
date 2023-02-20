package tech.stl.manager;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import tech.stl.manager.repository.ManagerRepository;
import tech.stl.manager.service.ManagerService;



@SpringBootTest(classes= {ServiceMockitoTest.class})
public class ServiceMockitoTest {
	
	@Mock
	ManagerRepository managerRepository;
	
	@InjectMocks
	ManagerService managerService;

}
