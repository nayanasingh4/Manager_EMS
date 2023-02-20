package tech.stl.manager.entity;



public class JwtResponse {

	private Manager manager;
	private String jwtToken;

	public Manager getUser() {
		return manager;
	}

	public void setUser(Manager manager) {
		this.manager = manager;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public JwtResponse(Manager manager, String jwtToken) {
		super();
		this.manager = manager;
		this.jwtToken = jwtToken;
	}

}
