package keyware.unyu.seisan.VO;

public class TEAM_SEQUENCE {
	private String team_sequence;
	private String email;
	private String name;

	public TEAM_SEQUENCE() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TEAM_SEQUENCE(String team_sequence, String email, String name) {
		super();
		this.team_sequence = team_sequence;
		this.email = email;
		this.name = name;
	}

	public String getTeam_sequence() {
		return team_sequence;
	}

	public void setTeam_sequence(String team_sequence) {
		this.team_sequence = team_sequence;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "TEAM_SEQUENCE [team_sequence=" + team_sequence + ", email=" + email + ", name=" + name + "]";
	}

}
