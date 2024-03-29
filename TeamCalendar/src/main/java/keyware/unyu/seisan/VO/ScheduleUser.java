package keyware.unyu.seisan.VO;

public class ScheduleUser {
	private String email;
	private String password;
	private String name;
	private String division;
	private String indate;
	private String teamNumber;

	public ScheduleUser(String email, String password, String name, String division, String indate, String teamNumber) {
		super();
		this.email = email;
		this.password = password;
		this.name = name;
		this.division = division;
		this.indate = indate;
		this.teamNumber = teamNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
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

	public String getDivision() {
		return division;
	}

	public void setDivision(String division) {
		this.division = division;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getTeamNumber() {
		return teamNumber;
	}

	public void setTeamNumber(String teamNumber) {
		this.teamNumber = teamNumber;
	}

	public ScheduleUser() {
		super();
		// TODO Auto-generated constructor stub
	}

}
