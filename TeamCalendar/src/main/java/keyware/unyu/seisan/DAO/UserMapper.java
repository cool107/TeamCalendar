package keyware.unyu.seisan.DAO;

import java.util.ArrayList;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

import keyware.unyu.seisan.VO.SCHEDULE_CALENDAR;
import keyware.unyu.seisan.VO.ScheduleUser;

public interface UserMapper {

	// 会員情報の確認
	public ScheduleUser selectUser(String email);

	// 会員登録
	public int insertUser(ScheduleUser user);

	// 会員情報変更
	public int updateUser(ScheduleUser user);

	// チーム作る
	public int insertTeam(ScheduleUser user);

	// TeamScheduleに番号登録
	public int teamSchedule();

	// TeamScheduleに登録した番号読み込む
	public long getTeamSchedule();

	// 全会員情報を読み込む
	public ArrayList<ScheduleUser> listUser(RowBounds rb, Map<String, String> map);

	// 全会員の数を読み込む
	public int getTotalUser(Map<String, String> map);

	// チームメンバー情報を読み込む
	public ArrayList<ScheduleUser> teamUser(RowBounds rb, Map<String, String> map);

	// チームメンバーの数を読み込む
	public int getTeamUser(Map<String, String> map);

	// チームメンバー情報を読み込む2
	public ArrayList<ScheduleUser> memberList(String teamNumber);

}
