package keyware.unyu.seisan.VO;

public class TEAM_SEQUENCE {
	private long team_sequence;

	public TEAM_SEQUENCE() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TEAM_SEQUENCE(int team_sequence) {
		super();
		this.team_sequence = team_sequence;
	}

	public long getTeam_sequence() {
		return team_sequence;
	}

	public void setTeam_sequence(long team_sequence) {
		this.team_sequence = team_sequence;
	}

	@Override
	public String toString() {
		return "TEAM_SEQUENCE [team_sequence=" + team_sequence + "]";
	}

}
