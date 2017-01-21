package billboard.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import billboard.beans.Bean;
import billboard.service.UserService;

@WebServlet(urlPatterns = { "/management/top" })
public class ManagementServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if (request.getSession().getAttribute("loginUser") == null) {
			response.sendRedirect("Billboard/login");
			return;
		}

		List<String> messages = null;
		if (request.getSession().getAttribute("errorMessages") == null) {
			messages = new ArrayList<String>();
		} else {
			messages = (List<String>)request.getSession().getAttribute("errorMessages");
		}

		List<Bean> users = new UserService().getUsers();
		Collections.reverse(users);
		request.getSession().setAttribute("users", users);

		request.getRequestDispatcher("/management.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		String userId = request.getParameter("stopUserId");

		if (StringUtils.isEmpty(userId)) {
			userId = request.getParameter("recoverUserId");
			if (StringUtils.isEmpty(userId)) {
				messages.add("予期せぬエラーが発生しました");
				request.setAttribute("errorMessages", messages);
				response.sendRedirect("billboard/management/top");
			} else {
				new UserService().recoverUser(Integer.parseInt(userId));
			}
		} else {
			new UserService().stopUser(Integer.parseInt(userId));
		}
		List<Bean> users = new UserService().getUsers();
		Collections.reverse(users);
		request.getSession().setAttribute("users", users);
		request.setAttribute("errorMessages", messages);
		request.getRequestDispatcher("/management.jsp").forward(request, response);
	}
}
