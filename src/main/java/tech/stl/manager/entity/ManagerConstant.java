package tech.stl.manager.entity;

public enum ManagerConstant {
	
	GET_ALL_EMPLOYEE("http://localhost:2404/employee/managerid");
	
	public String value;

	ManagerConstant(String value) {

		this.value = value;

	}

	public String getValue() {
		return value;
	}

}
