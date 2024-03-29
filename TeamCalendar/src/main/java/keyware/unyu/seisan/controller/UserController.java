package keyware.unyu.seisan.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import keyware.unyu.seisan.VO.SCHEDULE_CALENDAR;
import keyware.unyu.seisan.VO.ScheduleUser;
import keyware.unyu.seisan.service.CalService;
import keyware.unyu.seisan.service.UserService;
import keyware.unyu.seisan.util.PageNavigator;

@Controller
public class UserController {

	@Autowired
	UserService service;

	@Autowired
	CalService serviceCal;

	// 招待する人々の情報を保存するリスト
	ArrayList<ScheduleUser> savedId = new ArrayList<ScheduleUser>();

	// 掲示板の定数定義
	private int boardPerPage = 10; // 1ページで見せる情報数
	private int pagePerGroup = 5; // ページ移動できる項目数

	// IDの重複チェック
	@ResponseBody
	@RequestMapping(value = "checkId", method = RequestMethod.GET)
	public ScheduleUser checkId(String email) {
		System.out.println(email);
		ScheduleUser result = service.selectUser(email);
		System.out.println(result);
		if (result != null) {
			return result;
		}
		return null;
	}

	// 会員登録フォームに移動
	@RequestMapping(value = "goJoin", method = RequestMethod.GET)
	public String joinForm() {
		return "/user/joinForm";
	}

	// 会員登録
	@RequestMapping(value = "joinUser", method = RequestMethod.POST)
	public String joinMember(ScheduleUser user) {
		int result = service.insertUser(user);
		if (result != 1) {
			System.out.println("failed");
			return "redirect:/goJoin";
		}
		System.out.println("success");
		return "redirect:/";
	}

	// ログイン
	@RequestMapping(value = "loginUser", method = RequestMethod.POST)
	public String loginUser(String email, String password, HttpSession session, Model model) {
		ScheduleUser result = service.selectUser(email);
		if (result != null && result.getPassword().equals(password)) {
			session.setAttribute("loginId", result.getEmail());
			session.setAttribute("loginName", result.getName());
			session.setAttribute("loginDivision", result.getDivision());
			session.setAttribute("teamNumber", result.getTeamNumber());
			session.removeAttribute("error");
			model.addAttribute("user", result);
			if (!result.getTeamNumber().equals("0")) {
				String teamNumber = result.getTeamNumber();
				ArrayList<ScheduleUser> memberList = service.memberList(teamNumber);
				model.addAttribute("number", memberList.size());
				SCHEDULE_CALENDAR cal = new SCHEDULE_CALENDAR(); 
				cal.setTeamNumber(teamNumber);
				ArrayList<SCHEDULE_CALENDAR> calList = serviceCal.alert(cal);
				ArrayList<SCHEDULE_CALENDAR> todayList = serviceCal.alertToday(cal);
				model.addAttribute("todayList", todayList);
				System.out.println(todayList);
				int high = 0;
				int mid = 0;
				int low = 0;
				int other = 0;
				if (!calList.isEmpty()) {
					for (int i = 0; i < calList.size(); i++) {
						if (calList.get(i).getType().equals("重要度高")) {
							high++;
						} else if (calList.get(i).getType().equals("重要度中")) {
							mid++;
						} else if (calList.get(i).getType().equals("重要度低")) {
							low++;
						} else {
							other++;
						}
					}
				}
				model.addAttribute("high", high);
				model.addAttribute("mid", mid);
				model.addAttribute("low", low);
				model.addAttribute("other", other);
			}
			return "/cal/main";
		} else {
			session.setAttribute("error", "IDまたはパスワードが間違えます。");
			return "redirect:/";
		}
	}

	// メインページへ
	@RequestMapping(value = "goMain", method = RequestMethod.GET)
	public String goMain(Model model, HttpSession session) {
		String email = (String) session.getAttribute("loginId");
		ScheduleUser result = service.selectUser(email);
		model.addAttribute("user", result);
		if (!result.getTeamNumber().equals("0")) {
			String teamNumber = result.getTeamNumber();
			ArrayList<ScheduleUser> memberList = service.memberList(teamNumber);
			model.addAttribute("number", memberList.size());
			SCHEDULE_CALENDAR cal = new SCHEDULE_CALENDAR();
			cal.setTeamNumber(teamNumber);
			ArrayList<SCHEDULE_CALENDAR> calList = serviceCal.alert(cal);
			ArrayList<SCHEDULE_CALENDAR> todayList = serviceCal.alertToday(cal);
			model.addAttribute("todayList", todayList);
			System.out.println(todayList);
			int high = 0;
			int mid = 0;
			int low = 0;
			int other = 0;
			if (!calList.isEmpty()) {
				for (int i = 0; i < calList.size(); i++) {
					if (calList.get(i).getType().equals("重要度高")) {
						high++;
					} else if (calList.get(i).getType().equals("重要度中")) {
						mid++;
					} else if (calList.get(i).getType().equals("重要度低")) {
						low++;
					} else {
						other++;
					}
				}
			}
			model.addAttribute("high", high);
			model.addAttribute("mid", mid);
			model.addAttribute("low", low);
			model.addAttribute("other", other);
		}
		return "/cal/main";
	}

	// ログアウト
	@RequestMapping(value = "logoutUser", method = RequestMethod.GET)
	public String logoutMember(HttpSession session) {
		session.invalidate();
		savedId.clear();
		return "redirect:/";
	}

	// 会員情報修正
	@RequestMapping(value = "mypage", method = RequestMethod.GET)
	public String userPage(Model model, HttpSession session) {
		String email = (String) session.getAttribute("loginId");
		ScheduleUser result = service.selectUser(email);
		model.addAttribute("user", result);
		return "/user/mypage";
	}

	// 会員情報を修正
	@RequestMapping(value = "updateUser", method = RequestMethod.POST)
	public String updateMember(HttpSession session, ScheduleUser user, Model model) {

		String email = (String) session.getAttribute("loginId");
		ScheduleUser user_select = service.selectUser(email);

		
		if (user.getEmail() != "" && user.getEmail() != null) {
			user_select.setPassword(user.getPassword());
		}

		if (user.getDivision() != "" && user.getDivision() != null) {
			user_select.setDivision(user.getDivision());
		}
		
		if (user.getName() != "" && user.getName() != null) {
			user_select.setName(user.getName());
		}
		
		int result = service.updateUser(user_select);

		model.addAttribute("user", user_select);

		if (result == 1) {
			model.addAttribute("msg", "情報修正成功");
			session.removeAttribute("loginName");
			session.removeAttribute("loginDivision");
			
			session.setAttribute("loginName", user_select.getName());
			session.setAttribute("loginDivision", user_select.getDivision());
			return "redirect:/goMain";
		}
		return "redirect:/user/mypage";
	}

	// チーム登録
	@RequestMapping(value = "makeTeam", method = RequestMethod.GET)
	public String makeTeam(Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		ScheduleUser checkRegi = service.selectUser(loginId);
		if (!checkRegi.getTeamNumber().equals("0")) {
			String teamNumber = checkRegi.getTeamNumber();
			for (int i = 0; i < savedId.size(); i++) {
				savedId.get(i).setTeamNumber(teamNumber);
				int result = service.insertTeam(savedId.get(i));
				if (result != 1) {
					System.out.println("failed");
					model.addAttribute("error", "チーム登録ができません。");
					return "redirect:/";
				}
			}
		} else {
			service.teamSchedule();
			System.out.println("start to get");
			System.out.println("start to get2");
			String teamNumber = Long.toString(service.getTeamSchedule());
			System.out.println("end to get");
			for (int i = 0; i < savedId.size(); i++) {
				savedId.get(i).setTeamNumber(teamNumber);
				int result = service.insertTeam(savedId.get(i));
				if (result != 1) {
					System.out.println("failed");
					model.addAttribute("error", "チーム登録ができません。");
					return "redirect:/";
				}
			}
		}
		savedId.clear();
		return "redirect:/teamCheck";
	}

	// チームメンバー情報を読み込む
	@RequestMapping(value = "teamCheck", method = RequestMethod.GET)
	public String teamCheck(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "keyField", defaultValue = "") String keyField,
			@RequestParam(value = "keyWord", defaultValue = "") String keyWord, Model model, HttpSession session) {
		String loginId = (String) session.getAttribute("loginId");
		ScheduleUser result = service.selectUser(loginId);
		String teamNumber = result.getTeamNumber();
		session.setAttribute("teamNumber", result.getTeamNumber());
		if (teamNumber.equals("0")) {
			return "/user/team";
		} else {
			int totalCount = service.getTeamUser(keyField, keyWord, teamNumber);
			PageNavigator navi = new PageNavigator(boardPerPage, pagePerGroup, page, totalCount);
			ArrayList<ScheduleUser> teamList = service.teamUser(navi, keyField, keyWord, teamNumber);
			model.addAttribute("teamList", teamList);
			model.addAttribute("navi", navi);
		}
		return "/user/team";
	}

	// 全会員情報を読み込む
	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "keyField", defaultValue = "") String keyField,
			@RequestParam(value = "keyWord", defaultValue = "") String keyWord, Model model, HttpSession session) {
		int totalCount = service.getTotalUser(keyField, keyWord);
		PageNavigator navi = new PageNavigator(boardPerPage, pagePerGroup, page, totalCount);
		ArrayList<ScheduleUser> userList = service.listUser(navi, keyField, keyWord);
		model.addAttribute("userList", userList);
		model.addAttribute("navi", navi);
		return "/user/invite";
	}

	// チームメンバーの追加
	@ResponseBody
	@RequestMapping(value = "saveId", method = RequestMethod.GET)
	public ArrayList<ScheduleUser> saveId(String email, HttpSession session, Model model) {
		ScheduleUser result = service.selectUser(email);
		String Id = (String) session.getAttribute("loginId");
		ScheduleUser checkId = service.selectUser(Id);

		if (savedId.size() == 0 && (checkId.getTeamNumber().equals("0"))) {
			String addSelf = (String) session.getAttribute("loginId");
			ScheduleUser self = service.selectUser(addSelf);
			savedId.add(self);
		}

		if (!result.getTeamNumber().equals("0")) {
			return savedId;
		}

		for (int i = 0; i < savedId.size(); i++) {
			if (savedId.get(i).getEmail().equals(email)) {
				return savedId;
			}
		}
		savedId.add(result);
		return savedId;
	}

	// 選択メンバーリストを画面に渡す
	@ResponseBody
	@RequestMapping(value = "listId", method = RequestMethod.GET)
	public ArrayList<ScheduleUser> listId() {
		return savedId;
	}

	// 選択メンバーの削除
	@ResponseBody
	@RequestMapping(value = "updateId", method = RequestMethod.GET)
	public ArrayList<ScheduleUser> updateId(String email) {
		for (int i = 0; i < savedId.size(); i++) {
			if (savedId.get(i).getEmail().equals(email)) {
				savedId.remove(i);
			}
		}
		return savedId;
	}

	// チームメンバーの削除
	@RequestMapping(value = "deleteUser", method = RequestMethod.GET)
	public String deleteUser(String email) {
		System.out.println(email);
		ScheduleUser user_select = service.selectUser(email);
		System.out.println(user_select);
		user_select.setTeamNumber("0");
		int result = service.updateUser(user_select);
		if (result != 1) {
			System.out.println("faildelete");
			return "redirect:/teamCheck";
		}
		System.out.println("deleteFromTeam");
		return "redirect:/teamCheck";
	}
}
