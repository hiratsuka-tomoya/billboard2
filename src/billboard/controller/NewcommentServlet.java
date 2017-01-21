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

import billboard.beans.Comment;
import billboard.beans.User;
import billboard.service.CommentService;

@WebServlet(urlPatterns = { "/newcomment" })
public class NewcommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	@Override
//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
//			throws IOException, ServletException {
//
//		User loginUser = (User) request.getSession().getAttribute("loginUser");
//
//		if (loginUser == null) {
//			response.sendRedirect("login");
//		} else {
//			request.getRequestDispatcher("newcomment.jsp").forward(request, response);
//		}
//	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {

			User loginUser = (User) request.getSession().getAttribute("loginUser");
			Comment comment = new Comment();
			comment.setText(request.getParameter("text"));
			comment.setUserId(loginUser.getId());
			comment.setPostingId(Integer.parseInt(request.getParameter("postingId")));
			new CommentService().register(comment);

			response.sendRedirect("./");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("./");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		HttpSession session = request.getSession();
		String text = request.getParameter("text");

		session.setAttribute("text", text);

		if (StringUtils.isEmpty(text)) {
			messages.add("本文を入力してください");
		} else if (text.length() > 500) {
			messages.add("本文は500文字以下で入力してください");
		}

		return (messages.size() == 0);

	}

}
