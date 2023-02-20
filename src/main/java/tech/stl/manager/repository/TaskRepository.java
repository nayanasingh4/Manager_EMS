package tech.stl.manager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import tech.stl.manager.entity.Task;
import tech.stl.manager.value.Employee;


public  interface TaskRepository extends CrudRepository<Task, Integer> {

	void saveAndFlush(Task task);

	List<Task> findByEmpId(int empId);

	List<Task> findTaskByManagerId(int managerId);

	
}
