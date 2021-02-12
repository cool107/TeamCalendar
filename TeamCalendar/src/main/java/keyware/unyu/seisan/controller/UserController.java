package keyware.unyu.seisan.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import keyware.unyu.seisan.VO.ScheduleUser;
import keyware.unyu.seisan.service.UserService;
import keyware.unyu.seisan.util.PageNavigator;

@Controller
public class UserController {

	@Autowired
	UserService service;

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
			return "redirect:/";
		}
		System.out.println("success");
		return "redirect:/";
	}

	// ログイン
	@RequestMapping(value = "loginUser", method = RequestMethod.POST)
	public String loginUser(String email, String password, HttpSession session) {
		ScheduleUser result = service.selectUser(email);
		if (result != null && result.getPassword().equals(password)) {
			session.setAttribute("loginId", result.getEmail());
			session.setAttribute("loginName", result.getName());
			session.setAttribute("loginDivision", result.getDivision());
			session.setAttribute("teamNumber", result.getTeamNumber());
			return "/cal/main";
		} else {
			return "redirect:/";
		}
	}

	// メインページへ
	@RequestMapping(value = "goMain", method = RequestMethod.GET)
	public String goMain() {
		return "/cal/main";
	}

	// ログアウト
	@RequestMapping(value = "logoutUser", method = RequestMethod.GET)
	public String logoutMember(HttpSession session) {
		session.invalidate();
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

		int result = service.updateUser(user_select);

		model.addAttribute("user", user_select);

		if (result == 1) {
			return "redirect:/goMain";
		}
		return "/user/mypage";
	}

	// チーム登録
	@RequestMapping(value = "makeTeam", method = RequestMethod.GET)
	public String makeTeam(Model model, HttpSession session) {
		service.teamSchedule();
		String teamNumber = Integer.toString(service.getTeamSchedule());
		for (int i = 0; i < savedId.size(); i++) {
			savedId.get(i).setTeamNumber(teamNumber);
			int result = service.insertTeam(savedId.get(i));
			if (result != 1) {
				System.out.println("failed");
				model.addAttribute("error", "チーム登録ができません。");
				return "redirect:/";
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
		String teamNumber =result.getTeamNumber();
		if (teamNumber.equals("0")) {
			return "/user/team";
		}else {
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
	public ArrayList<ScheduleUser> saveId(String email, HttpSession session) {
		ScheduleUser result = service.selectUser(email);
		if (savedId.size()==0) {
			String addSelf =(String) session.getAttribute("loginId");
			ScheduleUser self = service.selectUser(addSelf);
			savedId.add(self);
		}
		for (int i = 0; i < savedId.size(); i++) {
			if (savedId.get(i).getEmail().equals(email) || (!(savedId.get(i).getTeamNumber().equals("0")))) {
				System.out.println("no add");
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
		ScheduleUser user_select = service.selectUser(email);
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
