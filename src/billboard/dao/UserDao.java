package billboard.dao;

import static billboard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import billboard.beans.Bean;
import billboard.beans.User;
import billboard.exception.SQLRuntimeException;

public class UserDao extends UpdatableDao {

	public UserDao() {
		super("users");
	}

	@Override
	protected Bean makeNewBean() {
		return new User();
	}

	public User getUser(Connection connection, String loginId,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";

			ps = connection.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);

			ResultSet rs = ps.executeQuery();
			List<Bean> userList = super.toBeanList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (2 <= userList.size()) {
				//ID重複
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return (User) userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public boolean stopUser(Connection connection, int id) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE " + tableName + " SET is_stopped = 1 WHERE id = " + id);
			ps = connection.prepareStatement(sql.toString());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public boolean recoverUser(Connection connection, int id) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE " + tableName + " SET is_stopped = 0 WHERE id = " + id);
			ps = connection.prepareStatement(sql.toString());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

//	public void insert(Connection connection, User user) {
//
//		PreparedStatement ps = null;
//		try {
//			StringBuilder sql = new StringBuilder();
//			sql.append("INSERT INTO users ( ");
//			sql.append("login_id");
//			sql.append(", password");
//			sql.append(", name");
//			sql.append(", branch_id");
//			sql.append(", department_id");
//			sql.append(", is_stopped");
//			sql.append(") VALUES (");
//			sql.append("?"); // login_id
//			sql.append(", ?"); // password
//			sql.append(", ?"); // name
//			sql.append(", ?"); // branch_id
//			sql.append(", ?"); // department_id
//			sql.append(", ?"); // is_stopped
//			sql.append(")");
//
//			ps = connection.prepareStatement(sql.toString());
//
//			ps.setString(1, user.getLoginID());
//			ps.setString(2, user.getPassword());
//			ps.setString(3, user.getName());
//			ps.setInt(4, user.getBranchID());
//			ps.setInt(5, user.getDepartmentID());
//			ps.setBoolean(5, user.isStopped());
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			throw new SQLRuntimeException(e);
//		} finally {
//			close(ps);
//		}
//	}
//
//	public void update(Connection connection, User user) {
//
//		PreparedStatement ps = null;
//		try {
//			StringBuilder sql = new StringBuilder();
//			sql.append("UPDATE users SET");
//			sql.append("  login_id = ?");
//			sql.append(", password = ?");
//			sql.append(", name = ?");
//			sql.append(", branch_id = ?");
//			sql.append(", department_id = ?");
//			sql.append(", is_stopped = ?");
//			sql.append(" WHERE");
//			sql.append(" id = ?");
////			sql.append(" AND");
////			sql.append(" update_date = ?");
//
//			ps = connection.prepareStatement(sql.toString());
//
//			ps.setString(1, user.getLoginId());
//			ps.setString(2, user.getPassword());
//			ps.setString(3, user.getName());
//			ps.setInt(4, user.getBranchId());
//			ps.setInt(5, user.getDepartmentId());
//			ps.setBoolean(6, user.isStopped());
//			ps.setInt(6, user.getId());
////			ps.setTimestamp(7, new Timestamp(user.getUpdatedDate().getTime()));
//
//			int count = ps.executeUpdate();
//			if (count == 0) {
//				throw new NoRowsUpdatedRuntimeException();
//			}
//		} catch (SQLException e) {
//			throw new SQLRuntimeException(e);
//		} finally {
//			close(ps);
//		}
//
//	}

//	public User getUser(Connection connection, String accountOrEmail) {
//
//		PreparedStatement ps = null;
//		try {
//			String sql = "SELECT * FROM user WHERE (account = ? OR email = ?)";
//
//			ps = connection.prepareStatement(sql);
//			ps.setString(1, accountOrEmail);
//			ps.setString(2, accountOrEmail);
//
//			ResultSet rs = ps.executeQuery();
//			List<User> userList = toUserList(rs);
//			if (userList.isEmpty() == true) {
//				return null;
//			} else if (2 <= userList.size()) {
//				//ID重複
//				throw new IllegalStateException("2 <= userList.size()");
//			} else {
//				return userList.get(0);
//			}
//		} catch (SQLException e) {
//			throw new SQLRuntimeException(e);
//		} finally {
//			close(ps);
//		}
//	}

}
