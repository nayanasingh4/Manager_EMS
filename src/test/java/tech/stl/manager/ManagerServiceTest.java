package tech.stl.manager;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import tech.stl.manager.ServiceMockitoTest;
import tech.stl.manager.entity.Manager;
import tech.stl.manager.repository.ManagerRepository;
import tech.stl.manager.repository.TaskRepository;
import tech.stl.manager.service.ManagerService;

@SpringBootTest(classes = { ServiceMockitoTest.class })
class ManagerServiceTest {
	@Mock
	ManagerRepository managerRepository;
//	@Mock
//	TaskRepository taskRepository;
	
	@InjectMocks
	ManagerService managerService;

	@Test
	void testSavemanager() {
		Manager manager = new Manager(1, "Aftab", "0000000000", "aftab@gmail.com", "a1234");
		when(managerRepository.save(manager)).thenReturn(manager);
		assertEquals(manager, managerService.savemanager(manager));
	}

	@Test
	void testGetAllManager() {
		List<Manager> managerList = new ArrayList<>();
		Manager manager = new Manager(1, "Aftab", "0000000000", "aftab@gmail.com", "a1234");
		Manager manager2 = new Manager(2, "Basu", "0000000000", "basu@gmail.com", "b1234");
		managerList.add(manager);
		managerList.add(manager2);
		when(managerRepository.save(manager)).thenReturn(manager);
		when(managerRepository.save(manager2)).thenReturn(manager2);
		when(managerRepository.findAll()).thenReturn(managerList);
		assertEquals(managerList, managerService.getAllManager());
	}

	@Test
	void testGetManagerId() {
		Optional<Manager> manager = Optional.of(new Manager(1, "Aftab", "0000000000", "aftab@gmail.com", "a1234"));
		when(managerRepository.findById(1)).thenReturn(manager);
		assertEquals(manager, managerService.getManagerId(1));
	}
	
	@Test
	void testGetManagerName() {
		Optional<Manager> manager = Optional.of(new Manager(1, "Aftab", "0000000000", "aftab@gmail.com", "a1234"));
		when(managerRepository.findById(1)).thenReturn(manager);
		assertEquals(manager.get().getManagerName(), managerService.getManagerName(1));
	}
	
//	@Test
//	void testUpdateManager() {
//		Manager manager = new Manager(1, "Aftab", "0000000000", "aftab@gmail.com", "a1234");
//		when(managerRepository.save(manager)).thenReturn(manager);
//		when(managerRepository.saveAndFlush(manager)).thenReturn(manager);
//		assert(managerService.updateManager(manager, 1).is);
//		
//		assert(managerService.getManagerId(1).isEmpty());
//	}

	@Test
	void testDeleteManagerById() {
		managerRepository.deleteById(1);
		managerService.deleteManagerById(1);
		assert(managerService.getManagerId(1).isEmpty());
	}
/*
	@Test
	void testSavetask() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTask() {
		fail("Not yet implemented");
	}

	@Test
	void testUpdateTask() {
		fail("Not yet implemented");
	}

	@Test
	void testDeleteTaskById() {
		fail("Not yet implemented");
	}

	@Test
	void testGetEmployeeByManagerId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTaskByEmpId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetTaskByManagerId() {
		fail("Not yet implemented");
	}
/*
	@Test
	void testUpdateLeave() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLeavebyManagerId() {
		fail("Not yet implemented");
	}

	@Test
	void testGetLeavebyLeaveId() {
		fail("Not yet implemented");
	}
*/
}
