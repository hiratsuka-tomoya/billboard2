package billboard.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import billboard.beans.Bean;
import billboard.service.UserCommentService;
import billboard.service.UserPostingService;

@WebServlet(urlPatterns = { "/index.jsp" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		if (request.getSession().getAttribute("loginUser") == null) {
			response.sendRedirect("login");
			return;
		}
		String categories[] = new String[3];
		categories[0] = "";
		categories[1] = "テストカテゴリ１";
		categories[2] = "テストカテゴリ2";

		request.getSession().setAttribute("categories", categories);
		List<String> messages = null;
		if (request.getSession().getAttribute("errorMessages") == null) {
			messages = new ArrayList<String>();
		} else {
			messages = (List<String>)request.getSession().getAttribute("errorMessages");
		}

		List<Bean> userPostings = null;
		List<Bean> userComments = new UserCommentService().getUserComments();

		String refineCategory = request.getParameter("refineCategory");
		String refineStartDate = request.getParameter("refineStartDate");
		String refineEndDate = request.getParameter("refineEndDate");
		if (isValidRefine(request, messages) &
				(
				StringUtils.isNotEmpty(refineCategory)  ||
				StringUtils.isNotEmpty(refineStartDate) ||
				StringUtils.isNotEmpty(refineEndDate))
				) {
			userPostings = new UserPostingService().getRefinedUserPostings(refineCategory, refineStartDate,
					refineEndDate);
		} else {
			userPostings = new UserPostingService().getUserPostings();
		}
		request.setAttribute("errorMessages", messages);
		request.setAttribute("refineCategory", refineCategory);
		request.getSession().setAttribute("userPostings", userPostings);
		request.getSession().setAttribute("userComments", userComments);

		request.getRequestDispatcher("/top.jsp").forward(request, response);
	}

	private boolean isValidRefine(HttpServletRequest request, List<String> messages) {
		String refineStartDate = request.getParameter("refineStartDate");
		String refineEndDate = request.getParameter("refineEndDate");

		if (StringUtils.isNotEmpty(refineStartDate)) {
			try {
			    DateUtils.parseDateStrictly(refineStartDate, new String[] {"yyyy/MM/dd"});
			    request.setAttribute("refineStartDate", refineStartDate);
			} catch (ParseException e) {
				messages.add("日付は yyyy/mm/dd 形式で入力してください");
			}
		}
		if (StringUtils.isNotEmpty(refineEndDate)) {
			try {
			    DateUtils.parseDateStrictly(refineEndDate, new String[] {"yyyy/MM/dd"});
			    request.setAttribute("refineEndDate", refineEndDate);
			} catch (ParseException e) {
				messages.add("日付は yyyy/mm/dd 形式で入力してください");
			}
		}

		return (messages.size() == 0);

	}

}
