package billboard.service;

import static billboard.utils.CloseableUtil.*;
import static billboard.utils.DBUtil.*;

import java.sql.Connection;

import billboard.beans.Posting;
import billboard.dao.PostingDao;

public class PostingService {

	public void register(Posting posting) {

		Connection connection = null;
		try {
			connection = getConnection();

			PostingDao postingDao = new PostingDao();
			postingDao.insert(connection, posting);

			commit(connection);
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

	public Boolean deleteUserPosting(int id) {

		Connection connection = null;
		try {
			connection = getConnection();

			Boolean ret = new PostingDao().deleteBeans(connection, id);

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
//	private void setDefaultIcon(User user) {
//
//		InputStream is = null;
//		try {
//			long randomNum = System.currentTimeMillis() % 5;
//			String filePath = "/duke_" + randomNum + ".jpg";
//			is = UserService.class.getResourceAsStream(filePath);
//			ByteArrayOutputStream baos = new ByteArrayOutputStream();
//			StreamUtil.copy(is, baos);
//			user.setIcon(baos.toByteArray());
//		} finally {
//			close(is);
//		}
//	}

//	public void update(User user) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			String encPassword = CipherUtil.encrypt(user.getPassword());
//			user.setPassword(encPassword);
//
//			UserDao userDao = new UserDao();
//			userDao.update(connection, user);
//
//			commit(connection);
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}

//	public User getUser(int userId) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			UserDao userDao = new UserDao();
//			User user = userDao.getUser(connection, userId);
//
//			commit(connection);
//
//			return user;
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}

//	public User getUser(String accountOrEmail) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			UserDao userDao = new UserDao();
//			User user = userDao.getUser(connection, accountOrEmail);
//
//			commit(connection);
//
//			return user;
//		} catch (RuntimeException e) {
//			rollback(connection);
//			throw e;
//		} catch (Error e) {
//			rollback(connection);
//			throw e;
//		} finally {
//			close(connection);
//		}
//	}

}
