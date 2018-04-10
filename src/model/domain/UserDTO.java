package model.domain;

public class UserDTO {
	private String emailAddress;
	private String password;
	private String name;
	private String sectorOne;
	private String sectorTwo;
	private String sectorThree;
		
	public UserDTO() {
		super();
	}
	
	public UserDTO(String emailAddress, String password ) {
		this.emailAddress = emailAddress;
		this.password = password; 
	}
	
	public UserDTO(String emailAddress, String name, String sectrorOne, String sectorTwo, String sectorThree) {
		this.emailAddress = emailAddress;
		this.name = name;
		this.sectorOne = sectrorOne;
		this.sectorTwo = sectorTwo;
		this.sectorThree = sectorThree;
	}
	
	public UserDTO(String emailAddress, String password, String name, String sectorOne, String sectorTwo,
			String sectorThree) {
		super();
		this.emailAddress = emailAddress;
		this.password = password;
		this.name = name;
		this.sectorOne = sectorOne;
		this.sectorTwo = sectorTwo;
		this.sectorThree = sectorThree;
	}
		
	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSectorOne() {
		return sectorOne;
	}

	public void setSectorOne(String sectorOne) {
		this.sectorOne = sectorOne;
	}

	public String getSectorTwo() {
		return sectorTwo;
	}

	public void setSectorTwo(String sectorTwo) {
		this.sectorTwo = sectorTwo;
	}

	public String getSectorThree() {
		return sectorThree;
	}

	public void setSectorThree(String sectorThree) {
		this.sectorThree = sectorThree;
	}
}
