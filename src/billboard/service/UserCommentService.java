package billboard.service;

import static billboard.utils.CloseableUtil.*;
import static billboard.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import billboard.beans.Bean;
import billboard.dao.UserCommentDao;

public class UserCommentService {

//	public void register(Posting posting) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			MessageDao messageDao = new MessageDao();
//			messageDao.insert(connection, posting);
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

	private static final int LIMIT_NUM = 1000;

	public List<Bean> getUserComments() {

		Connection connection = null;
		try {
			connection = getConnection();

			List<Bean> ret = new UserCommentDao().getBeans(connection, LIMIT_NUM);

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

//	public List<UserMessage> getMessage(String whereColumn, String whereValue) {
//
//		Connection connection = null;
//		try {
//			connection = getConnection();
//
//			UserMessageDao messageDao = new UserMessageDao();
//			List<UserMessage> ret = messageDao.getUserMessages(connection, LIMIT_NUM, whereColumn, whereValue);
//
//			commit(connection);
//
//			return ret;
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
