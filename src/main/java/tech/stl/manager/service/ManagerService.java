package tech.stl.manager.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import tech.stl.manager.entity.Manager;
import tech.stl.manager.entity.Task;
import tech.stl.manager.repository.ManagerRepository;
import tech.stl.manager.repository.TaskRepository;
import tech.stl.manager.value.Employee;
import tech.stl.manager.value.LeaveRequest;


@Service
public class ManagerService {
	
	@Autowired
	private ManagerRepository managerRepository;
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private RestTemplate restTemplate;


	// add manager
	public Manager savemanager(Manager manager) {
		return managerRepository.save(manager);
	}

	// get manager list
	public List<Manager> getAllManager() {
		return this.managerRepository.findAll();
	}

	// get manager by id
	public Optional<Manager> getManagerId(int managerId) {
		return managerRepository.findById(managerId);
	}
	
	// get manager name by id
	public String getManagerName(int managerId) {
		Manager manager = managerRepository.findById(managerId).get();
		return manager.getManagerName();
	}
	


	// update manager
	public void updateManager(Manager manager, int managerId) {
		manager.setManagerId(managerId);
		managerRepository.saveAndFlush(manager);
	}

	// delete manager by id
	public void deleteManagerById(int managerId) {
		managerRepository.deleteById(managerId);
	}
	
	//add Task
			public Task savetask(Task task) {
				return taskRepository.save(task);
			}
	   
			
		// get task by manager id
			public Optional<Task> getTask(int taskId) {
				return taskRepository.findById(taskId);
			}
			
		// update task
			public void updateTask(Task task, int taskId) {
				task.setTaskId(taskId);
				taskRepository.saveAndFlush(task);
			}
			
		// delete task by task id
			public void deleteTaskById(int taskId) {
				taskRepository.deleteById(taskId);
			}
			
//			public ResponseEntity<Employee> getEmployeeByManagerId( int managerId){
//				return restTemplate.getForEntity(ManagerConstant.GET_ALL_EMPLOYEE.getValue(),Employee.class);
//
//			}
			public Employee[] getEmployeeByManagerId( int managerId) throws URISyntaxException{
//				ResponseEntity<Employee> responseEntityUser = restTemplate.getForEntity("http://localhost:2404/employee/managerid" + "/"+managerId, Employee.class);
//				return responseEntityUser;
				URI uri = new URI("http://localhost:2404/employee/managerid"+"/"+managerId);
				RestTemplate restTemplate = new RestTemplate();
				Employee[] emps = restTemplate.getForObject(uri, Employee[].class);
				return emps; 
			
			}
			

			
			// get task by employee id
			public List<Task> getTaskByEmpId(int empId) {
				return this.taskRepository.findByEmpId(empId);

			}
			
			//get task by manager Id
			public List<Task> getTaskByManagerId(int managerId) {
			   return this.taskRepository.findTaskByManagerId(managerId);
			   }

			
			//put leave status to accept or reject by leave id
			public LeaveRequest updateLeave(int leaveId,LeaveRequest leaveRequest) {
				HttpHeaders headers = new HttpHeaders();
			      headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			      HttpEntity<LeaveRequest> entity = new HttpEntity<LeaveRequest>(leaveRequest,headers);
			      
			      return restTemplate.exchange(
			         "http://localhost:2404/leave/update/"+leaveId, HttpMethod.PUT, entity, LeaveRequest.class).getBody();
			   }
			
			//Get leave by manager id
			public LeaveRequest[] getLeavebyManagerId( int managerId) throws URISyntaxException{
				URI uri = new URI("http://localhost:2404/leave/managerId"+"/"+managerId);
				RestTemplate restTemplate = new RestTemplate();
				LeaveRequest[] leave = restTemplate.getForObject(uri, LeaveRequest[].class);
				return leave; 
			
			}
			
			//Get leave by leave id
			public LeaveRequest[] getLeavebyLeaveId( int leaveId) throws URISyntaxException{
				URI uri = new URI("http://localhost:2404/leave/id"+"/"+leaveId);
				RestTemplate restTemplate = new RestTemplate();
				LeaveRequest[] leave = restTemplate.getForObject(uri, LeaveRequest[].class);
				return leave; 
			
			}

			

}

