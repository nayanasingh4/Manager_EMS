package tech.stl.manager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import tech.stl.manager.entity.Manager;

public interface ManagerRepository extends JpaRepository<Manager, Integer> {

	List<Manager> findByemailId(String emailId);

	public Manager findByEmailId(String emailId);
}
