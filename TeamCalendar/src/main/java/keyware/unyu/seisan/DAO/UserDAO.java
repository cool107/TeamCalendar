package keyware.unyu.seisan.DAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import keyware.unyu.seisan.VO.SCHEDULE_CALENDAR;
import keyware.unyu.seisan.VO.ScheduleUser;
import keyware.unyu.seisan.util.PageNavigator;

@Repository
public class UserDAO {

	@Autowired
	SqlSession session;

	public ScheduleUser selectUser(String email) {
		ScheduleUser result = null;

		UserMapper mapper = session.getMapper(UserMapper.class);

		try {
			result = mapper.selectUser(email);

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

		return result;
	}
	
	public int insertTeam(ScheduleUser user){
		int result = 0;

		UserMapper mapper = session.getMapper(UserMapper.class);

		try {
			result = mapper.insertTeam(user);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return result;
	}
		
	public int insertUser(ScheduleUser user) {
		int result = 0;

		UserMapper mapper = session.getMapper(UserMapper.class);

		try {
			result = mapper.insertUser(user);
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}

		return result;
	}

	public int teamSchedule() {
		int result = 0;
		UserMapper mapper = session.getMapper(UserMapper.class);
		try {
			result = mapper.teamSchedule();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	//TeamScheduleに登録した番号読み込む
	public int getTeamSchedule() {
		int result = 0;
		UserMapper mapper = session.getMapper(UserMapper.class);
		try {
			result = mapper.getTeamSchedule();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
		return result;
	}
	
	public int updateUser(ScheduleUser user) {

		int result = 0;

		UserMapper mapper = session.getMapper(UserMapper.class);

		try {

			result = mapper.updateUser(user);

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result;
	}
	
	public ArrayList<ScheduleUser> teamUser(PageNavigator navi, String keyField, String keyWord, String teamNumber) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyField", keyField);
		map.put("keyWord", keyWord);
		map.put("teamNumber", teamNumber);
		RowBounds rb = new RowBounds(navi.getStartBoardPage(), navi.getBoardPerPage());
		ArrayList<ScheduleUser> result = null;
		try {
			result = mapper.teamUser(rb, map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}
	
	public int getTeamUser(String keyField, String keyWord, String teamNumber) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyField", keyField);
		map.put("keyWord", keyWord);
		map.put("teamNumber", teamNumber);
		int result = 0;
		try {
			result = mapper.getTeamUser(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<ScheduleUser> listUser(PageNavigator navi, String keyField, String keyWord) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyField", keyField);
		map.put("keyWord", keyWord);
		RowBounds rb = new RowBounds(navi.getStartBoardPage(), navi.getBoardPerPage());
		ArrayList<ScheduleUser> result = null;
		try {
			result = mapper.listUser(rb, map);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return result;
	}

	public int getTotalUser(String keyField, String keyWord) {
		UserMapper mapper = session.getMapper(UserMapper.class);
		Map<String, String> map = new HashMap<String, String>();
		map.put("keyField", keyField);
		map.put("keyWord", keyWord);
		int result = 0;
		try {
			result = mapper.getTotalUser(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<ScheduleUser> memberList(String teamNumber) {
		// TODO Auto-generated method stub
		UserMapper mapper = session.getMapper(UserMapper.class);
		ArrayList<ScheduleUser> result = null;
		try {
			result = mapper.memberList(teamNumber);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
