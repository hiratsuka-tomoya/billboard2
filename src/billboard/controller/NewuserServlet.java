package billboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import billboard.beans.User;
import billboard.service.UserService;

@WebServlet(urlPatterns = { "/management/newuser" })
public class NewuserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		User loginUser = (User) request.getSession().getAttribute("loginUser");

		if (loginUser == null) {
//			response.sendRedirect("login");
		} else if (loginUser.getDepartmentId() != 1) {
			List<String> messages = new ArrayList<String>();
			messages.add("権限がありません");
			request.getSession().setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		} else {
			if (request.getSession().getAttribute("branchId") == null) {
				request.getSession().setAttribute("branchId", 1);
			}
			if (request.getSession().getAttribute("departmentId") == null) {
				request.getSession().setAttribute("departmentId", 1);
			}
			request.getRequestDispatcher("/newuser.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {

			User user = new User();
			user.setLoginId(request.getParameter("loginId"));
			user.setPassword(request.getParameter("password"));
			user.setName(request.getParameter("name"));
			user.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			user.setDepartmentId(Integer.parseInt(request.getParameter("departmentId")));
			user.setStopped(false);
			new UserService().register(user);

			response.sendRedirect("./top");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("newuser");	//▲そのページでしか使わないからリクエスト.setAttributeするように変更して、リダイレクトだと消えるしページも移動しないからforwardに変更する
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		HttpSession session = request.getSession();
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		int branchId = Integer.valueOf(request.getParameter("branchId"));
		int departmentId = Integer.valueOf(request.getParameter("departmentId"));

		session.removeAttribute("loginId");
		session.removeAttribute("name");

		session.setAttribute("branchId", branchId);
		session.setAttribute("departmentId", departmentId);

		if (StringUtils.isEmpty(loginId)) {
			messages.add("ログインIDを入力してください");
		} else if (!loginId.matches("^[0-9a-zA-Z]{6,20}$")) {
			if (!loginId.matches("^[0-9a-zA-Z]+$")) {
				messages.add("ログインIDは半角英数字のみ使用できます");
			}
			if (!(loginId.length() >= 6 && loginId.length() <= 20)) {
				messages.add("ログインIDは6文字以上20文字以内で入力してください");
			}
			// } else if (new UserService().getUser(loginId) != null) {
			// messages.add("そのログインIDは登録できません");
			// } else {▲既存ID
		} else {
			session.setAttribute("loginId", loginId);
		}

		if (StringUtils.isEmpty(password)) {
			messages.add("パスワードを入力してください");
		} else if (!password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]{6,255}+$")) {
			if (!password.matches("^[a-zA-Z0-9 -/:-@\\[-\\`\\{-\\~]+$")) {
				messages.add("パスワードは記号を含む半角文字のみ使用できます");
			}
			if (!(password.length() >= 6 && password.length() <= 255)) {
				messages.add("パスワードは6文字以上255文字以内で入力してください");
			}
		}

		if (StringUtils.isEmpty(name)) {
			messages.add("ユーザー名を入力してください");
		} else if (!(name.length() >= 1 && name.length() <= 10)) {
			messages.add("ユーザー名は10文字以下で入力してください");
		} else {
			session.setAttribute("name", name);
		}

		return (messages.size() == 0);

	}

}
