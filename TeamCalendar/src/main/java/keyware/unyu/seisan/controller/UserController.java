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
		System.out.println("id check finish");
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
		System.out.println(user);
		return "redirect:/";
	}

//	// ログインフォーム
//	@RequestMapping(value = "loginUser", method = RequestMethod.GET)
//	public String loginForm() {
//		return "/user/loginForm";
//	}

	// ログイン
	@RequestMapping(value = "loginUser", method = RequestMethod.POST)
	public String loginUser(String email, HttpSession session) {
			System.out.println(email);
			ScheduleUser result = service.selectUser(email);
			if (result!=null) {
				session.setAttribute("loginId", result.getEmail());
				session.setAttribute("loginName", result.getName());
				session.setAttribute("loginDivision", result.getDivision());
				return "/user/mainPage";
			}else {
				return "redirect:/";
			}
			
	}
		
//		if (email!=null&&password!=null) {
////			ScheduleUser result = service.loginUser(email, password);
//			if (result!=null) {
//					System.out.println("loginSuccess");
//					session.setAttribute("loginId", result.getEmail());
//					session.setAttribute("loginName", result.getName());
//					session.setAttribute("loginDivision", result.getDivision());
//					return "/user/mainPage";
//			}
//		}
//		String error = "IDまたはPASSWORDが違います。";
//		session.setAttribute("error", error);
//		return "redirect:/";

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
		model.addAttribute("member", result);

		return "/user/mypage";

	}

	// 会員情報修正フォームに移動
	@RequestMapping(value = "updateUser", method = RequestMethod.GET)
	public String updateUser_GET(HttpSession session, Model model) {

		String email = (String) session.getAttribute("loginId");
		ScheduleUser result = service.selectUser(email);
		model.addAttribute("user", result);

		return "/user/updateForm";
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
			return "redirect:/";
		}

		return "/user/mypage";
	}

	// 全会員情報を読み込む
	@RequestMapping(value = "userList", method = RequestMethod.GET)
	public String list(@RequestParam(value = "page", defaultValue = "1") int page,
			@RequestParam(value = "keyField", defaultValue = "") String keyField,
			@RequestParam(value = "keyWord", defaultValue = "") String keyWord, Model model, HttpSession session) {
		int totalCount = service.getTotalUser(keyField, keyWord);
		savedId.clear();
		PageNavigator navi = new PageNavigator(boardPerPage, pagePerGroup, page, totalCount);
		ArrayList<ScheduleUser> userList = service.listUser(navi, keyField, keyWord);
		model.addAttribute("userList", userList);
		model.addAttribute("navi", navi);
		return "/user/userList";
	}

	// チームメンバーの追加
	@ResponseBody
	@RequestMapping(value = "saveId", method = RequestMethod.GET)
	public ArrayList<ScheduleUser> saveId(String email) {
		ScheduleUser result = service.selectUser(email);
		if (savedId.size() == 0) {
			savedId.add(result);
		} else {
			for (int i = 0; i < savedId.size(); i++) {
				if (savedId.get(i).getEmail().equals(email)) {
					System.out.println("no add");
					return savedId;
				}
			}
			savedId.add(result);
		}
		return savedId;
	}

	// チームメンバーの削除
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

}
