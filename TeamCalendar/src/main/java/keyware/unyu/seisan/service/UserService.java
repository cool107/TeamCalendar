package keyware.unyu.seisan.service;

import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import keyware.unyu.seisan.DAO.UserDAO;
import keyware.unyu.seisan.DAO.UserMapper;
import keyware.unyu.seisan.VO.ScheduleUser;
import keyware.unyu.seisan.util.PageNavigator;

@Service
public class UserService {

	@Autowired
	UserDAO dao;

	public ScheduleUser selectUser(String email) {
		return dao.selectUser(email);
	}

//	public ScheduleUser loginUser(String email, String password) {
//		return dao.loginUser(email, password);
//	}
	
	public int insertUser(ScheduleUser user) {
		return dao.insertUser(user);
	}

	public int updateUser(ScheduleUser user_select) {
		return dao.updateUser(user_select);
	}

	public ArrayList<ScheduleUser> listUser(PageNavigator navi, String keyField, String keyWord) {
		System.out.println("service listUser navi:"+navi);
		System.out.println("service listUser keyField:"+keyField);
		System.out.println("service listUser keyWord:"+keyWord);
		return dao.listUser(navi, keyField, keyWord);
	}

	public int getTotalUser(String keyField, String keyWord) {
		System.out.println("service getTotalUser keyField:"+keyField);
		System.out.println("service getTotalUser keyWord:"+keyWord);
		return dao.getTotalUser(keyField, keyWord);
	}
}
