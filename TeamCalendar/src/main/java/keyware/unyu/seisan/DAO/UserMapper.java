package keyware.unyu.seisan.DAO;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import keyware.unyu.seisan.VO.ScheduleUser;

public interface UserMapper {

	//会員情報の確認
	public ScheduleUser selectUser(String email);

	//会員登録
	public int insertUser(ScheduleUser user);

	//会員登録変更
	public int updateUser(ScheduleUser user);
	
	//全会員情報を読み込む
	public ArrayList<ScheduleUser> listUser(RowBounds rb, Map<String, String> map);

	//全会員の数を読み込む
	public int getTotalUser(Map<String, String> map);
	
}
