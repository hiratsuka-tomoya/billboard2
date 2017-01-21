package billboard.service;

import static billboard.utils.CloseableUtil.*;
import static billboard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import billboard.beans.Bean;
import billboard.dao.UserPostingDao;

public class UserPostingService {


	private static final int LIMIT_NUM = 1000;

	public List<Bean> getUserPostings() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Bean> ret = new UserPostingDao().getBeans(connection, LIMIT_NUM);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Bean> getUserPostings(String whereColumnName, int whereValue) {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Bean> ret = new UserPostingDao().getBeans(connection, LIMIT_NUM, whereColumnName, whereValue);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Bean> getUserPostings(String whereColumnName, String whereValue) {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Bean> ret = new UserPostingDao().getBeans(connection, LIMIT_NUM, whereColumnName, whereValue);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Bean> getRefinedUserPostings(String category, String startDate, String endDate ) {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Bean> ret = new UserPostingDao().getRefinedBeans(connection, LIMIT_NUM, category, startDate, endDate);

			commit(connection);

			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


}
