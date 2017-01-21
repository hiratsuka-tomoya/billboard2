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
import billboard.service.LoginService;

@WebServlet(urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if (request.getSession().getAttribute("loginUser") == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} else {
			response.sendRedirect("./");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();

		if (isValid(request, messages) == true) {

			String loginID = request.getParameter("loginID");
			String password = request.getParameter("password");

			LoginService loginService = new LoginService();
			User user = loginService.login(loginID, password);

			if (user != null) {
				session.setAttribute("loginUser", user);
				response.sendRedirect("./");
			} else {
				messages.add("ログインに失敗しました。");
				session.setAttribute("errorMessages", messages);
				response.sendRedirect("login");
			}

		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("login");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		HttpSession session = request.getSession();
		String loginID = request.getParameter("loginID");
		String password = request.getParameter("password");

		session.removeAttribute("loginID");

		if (StringUtils.isEmpty(loginID) == true) {
			messages.add("ログインIDを入力してください");
		} else {
			session.setAttribute("loginID", loginID);
		}
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}

		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
