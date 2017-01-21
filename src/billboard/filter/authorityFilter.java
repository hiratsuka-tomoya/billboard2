package billboard.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import billboard.beans.User;

@WebFilter("/management/*")
public class authorityFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String target = ((HttpServletRequest) request).getRequestURI();
		HttpSession session = ((HttpServletRequest) request).getSession();

		if (session == null) {
			session = ((HttpServletRequest) request).getSession(true);
			session.setAttribute("target", target);
			((HttpServletResponse) response).sendRedirect("/Billboard/login");
		} else {
			User loginUser = (User)session.getAttribute("loginUser");
			if (loginUser != null) {
				if (loginUser.getDepartmentId() != 1) {
				session.setAttribute("target", target);
				((HttpServletResponse) response).sendRedirect("/Billboard/login");
				return;
				}
			}
		}
		chain.doFilter(request, response); // サーブレットを実行
	}

	@Override
	public void init(FilterConfig config) {
	}

	@Override
	public void destroy() {
	}

}