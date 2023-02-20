package tech.stl.manager.value;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Employee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int employeeId;
	
	private String employeeName;
	
	private String phoneNumber;
	
	private int managerId;
	
	private String emailId;
	
	private String password;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getManagerId() {
		return managerId;
	}

	public void setManagerId(int managerId) {
		this.managerId = managerId;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Employee(int employeeId, String employeeName, String phoneNumber, int managerId, String emailId,
			String password) {
		super();
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.phoneNumber = phoneNumber;
		this.managerId = managerId;
		this.emailId = emailId;
		this.password = password;
	}

	@Override
	public String toString() {
		return "Employee [employeeId=" + employeeId + ", employeeName=" + employeeName + ", phoneNumber=" + phoneNumber
				+ ", managerId=" + managerId + ", emailId=" + emailId + ", password=" + password + "]";
	}

	public Employee() {
		super();
		// TODO Auto-generated constructor stub
	}
}
