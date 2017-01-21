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

import billboard.beans.Posting;
import billboard.beans.User;
import billboard.service.PostingService;

@WebServlet(urlPatterns = { "/newpost" })
public class NewPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		User loginUser = (User) request.getSession().getAttribute("loginUser");

		if (loginUser == null) {
			response.sendRedirect("login");
		} else {
			request.getRequestDispatcher("newpost.jsp").forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {

			User loginUser = (User) request.getSession().getAttribute("loginUser");
			Posting posting = new Posting();
			posting.setTitle(request.getParameter("title"));
			posting.setText(request.getParameter("text"));
			posting.setCategory(request.getParameter("category"));
			posting.setUserId(loginUser.getId());
			new PostingService().register(posting);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("newpost");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		HttpSession session = request.getSession();
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String category = request.getParameter("category");

		session.setAttribute("title", title);
		session.setAttribute("text", text);
		session.setAttribute("category", category);

		if (StringUtils.isEmpty(title)) {
			messages.add("件名を入力してください");
		} else if (title.length() > 50) {
			messages.add("件名は50文字以下で入力してください");
		}

		if (StringUtils.isEmpty(text)) {
			messages.add("本文を入力してください");
		} else if (text.length() > 1000) {
			messages.add("本文は1000文字以下で入力してください");
		}

		if (StringUtils.isEmpty(category)) {
			messages.add("カテゴリーを入力してください");
		} else if (category.length() > 10) {
			messages.add("カテゴリーは10文字以下で入力してください");
		}

		return (messages.size() == 0);

	}

}
