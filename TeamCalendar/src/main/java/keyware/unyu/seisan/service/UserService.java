package keyware.unyu.seisan.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import keyware.unyu.seisan.DAO.UserDAO;
import keyware.unyu.seisan.DAO.UserMapper;
import keyware.unyu.seisan.VO.SCHEDULE_CALENDAR;
import keyware.unyu.seisan.VO.ScheduleUser;
import keyware.unyu.seisan.util.PageNavigator;

@Service
public class UserService {

	@Autowired
	UserDAO dao;

	public ScheduleUser selectUser(String email) {
		return dao.selectUser(email);
	}

	public int insertTeam(ScheduleUser user) {
		return dao.insertTeam(user);
	}

	public int insertUser(ScheduleUser user) {
		return dao.insertUser(user);
	}

	public int teamSchedule() {
		return dao.teamSchedule();
	}

	public int getTeamSchedule() {
		return dao.getTeamSchedule();
	}

	public int updateUser(ScheduleUser user_select) {
		return dao.updateUser(user_select);
	}

	public ArrayList<ScheduleUser> listUser(PageNavigator navi, String keyField, String keyWord) {
		return dao.listUser(navi, keyField, keyWord);
	}

	public int getTotalUser(String keyField, String keyWord) {
		return dao.getTotalUser(keyField, keyWord);
	}

	public ArrayList<ScheduleUser> teamUser(PageNavigator navi, String keyField, String keyWord, String teamNumber) {
		return dao.teamUser(navi, keyField, keyWord, teamNumber);
	}

	public int getTeamUser(String keyField, String keyWord, String teamNumber) {
		return dao.getTeamUser(keyField, keyWord, teamNumber);
	}

	public ArrayList<ScheduleUser> memberList(String teamNumber) {
		// TODO Auto-generated method stub
		return dao.memberList(teamNumber);
	}

}
