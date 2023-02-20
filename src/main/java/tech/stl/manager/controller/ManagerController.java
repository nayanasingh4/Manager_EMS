package tech.stl.manager.controller;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import tech.stl.manager.entity.Manager;
import tech.stl.manager.entity.Task;
import tech.stl.manager.exception.ResourceNotFoundException;
import tech.stl.manager.service.ManagerService;
import tech.stl.manager.value.Employee;
import tech.stl.manager.value.LeaveRequest;

@RestController
@CrossOrigin
public class ManagerController {
	
	@Autowired
	public ManagerService managerService;
	
	@GetMapping("/manager")
	public ResponseEntity<List<Manager>> getManager()  {
		List<Manager> list = managerService.getAllManager();
		if (list.size() <= 0) {
			throw new ResourceNotFoundException("Manager list is empty");

		}
		return ResponseEntity.status(HttpStatus.CREATED).body(list);
	}
	
//	@GetMapping("/manager/{managerId}")
//	public ResponseEntity<Optional<Manager>> getManagerById(@PathVariable("managerId") int managerId) {
//		Optional<Manager> manager = managerService.getManagerById(managerId);
//		if (manager == null) {
//			throw new ResourceNotFoundException("Patient is not present with the id:"+managerId);
//		}
//		return ResponseEntity.of(Optional.of(manager));
//	}
	
	@GetMapping("/manager/{managerId}")
	public Optional<Manager> getManager(@PathVariable("managerId") int managerId)  {
		Optional<Manager> manager = managerService.getManagerId(managerId);
		if (manager == null) {
			throw new ResourceNotFoundException("Manager with the given id is not present");
		}
		return managerService.getManagerId(managerId);
	}
	
	@GetMapping("/managerName/{managerId}")
	public String getManagerName(@PathVariable("managerId") int managerId)  {
		Manager manager = managerService.getManagerId(managerId).get();
		if (manager == null) {
			throw new ResourceNotFoundException("Manager with the given id is not present");
		}
		return managerService.getManagerName(managerId);
	}
	
	@PostMapping("/manager")
	public ResponseEntity<Manager> addManager( @RequestBody Manager manager) {
		Manager b = null;
		try {
			b = this.managerService.savemanager(manager);
			System.out.println(managerService);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@PutMapping("/manager/update/{managerId}")
	public ResponseEntity<Manager> updateManager( @RequestBody Manager manager,
			@PathVariable("managerId") int managerId)  {
		try {
			this.managerService.updateManager(manager, managerId);
			return ResponseEntity.ok().body(manager);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResourceNotFoundException("The requested manager cannot be updated as it is not present in the list");
	}
	
	@DeleteMapping("/manager/delete/{managerId}")
	public ResponseEntity<Void> deleteManager(@PathVariable("managerId") int managerId) {
		try {
			this.managerService.deleteManagerById(managerId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResourceNotFoundException("The requested manager cannot be deleted as it is not present in the list");
	}
	
	@PostMapping("/task")
	public ResponseEntity<Task> addTask( @RequestBody Task task) {
		Task b = null;
		try {
			b = this.managerService.savetask(task);
			System.out.println(managerService);
			return ResponseEntity.of(Optional.of(b));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	@GetMapping("/task/{taskId}")
	public Optional<Task> getTask(@PathVariable("taskId") int taskId)  {
		Optional<Task> task = managerService.getTask(taskId);
		if (task == null) {
			throw new ResourceNotFoundException("Task not present");
		}
		return managerService.getTask(taskId);
	}
	
	@PutMapping("/task/update/{taskId}")
	public ResponseEntity<Task> updateTask( @RequestBody Task task,
			@PathVariable("taskId") int taskId)  {
		try {
			this.managerService.updateTask(task, taskId);
			return ResponseEntity.ok().body(task);
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResourceNotFoundException("The requested task cannot be updated as it is not present in the list");
	}
	
	@DeleteMapping("/task/delete/{taskId}")
	public ResponseEntity<Void> deleteTask(@PathVariable("taskId") int taskId) {
		try {
			this.managerService.deleteTaskById(taskId);
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} catch (Exception e) {
			e.printStackTrace();
		}
		throw new ResourceNotFoundException("The requested task cannot be deleted as it is not present in the list");
	}
	

	
	//Get employee by manager Id
		@GetMapping("/allemployee/managerid/{managerId}")
		public ResponseEntity<Object> getEmployeeByManagerId(@PathVariable("managerId")int managerId) throws URISyntaxException{
			Employee[] manager = this.managerService.getEmployeeByManagerId(managerId);
			if(manager == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.status(HttpStatus.FOUND).body(manager);
		}
		
		//Get task by employeeId
		
		@GetMapping("/task/empid/{empId}")
		public ResponseEntity<List<Task>> getTaskByEmpId(@PathVariable("empId") int empId)  {
			List<Task> list = managerService.getTaskByEmpId(empId);
			if (list.size() <= 0) {
				throw new ResourceNotFoundException("No Employee present in the list ");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(list);
		}
		
		//Get list of task by manager Id
		@GetMapping("/task/managerId/{managerId}")
		public ResponseEntity<List<Task>> getTaskByManagerId(@PathVariable("managerId") int managerId)  {
			List<Task> list = managerService.getTaskByManagerId(managerId);
			if (list.size() <= 0) {
				throw new ResourceNotFoundException("No Task present in the list ");
			}
			return ResponseEntity.status(HttpStatus.CREATED).body(list);
		}
		
		@PutMapping("/getleave/update/{leaveId}")
		public LeaveRequest updateLeaveByLeaveId(@PathVariable("leaveId") int leaveId, @RequestBody LeaveRequest leaveRequest) {
			return this.managerService.updateLeave(leaveId,leaveRequest);
		}
		
		@GetMapping("/getleave/managerid/{managerId}")
		public ResponseEntity<Object> getLeavebyManagerId(@PathVariable("managerId")int managerId) throws URISyntaxException{
			LeaveRequest[] leave = this.managerService.getLeavebyManagerId(managerId);
			if(leave == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.status(HttpStatus.FOUND).body(leave);
		}
		
		@GetMapping("/getleave/leaveid/{leaveId}")
		public ResponseEntity<Object> getLeavebyLeaveId(@PathVariable("leaveId")int leaveId) throws URISyntaxException{
			LeaveRequest[] leave = this.managerService.getLeavebyLeaveId(leaveId);
			if(leave == null) {
				return ResponseEntity.notFound().build();
			}
			return ResponseEntity.status(HttpStatus.FOUND).body(leave);
		}


		
}
