package billboard.dao;

import billboard.beans.Bean;
import billboard.beans.Comment;

public class CommentDao extends UpdatableDao {

	public CommentDao() {
		super("comments");
	}

	@Override
	protected Bean makeNewBean() {
		return new Comment();
	}


//	public User getUser(Connection connection, String loginID,
//			String password) {
//
//		PreparedStatement ps = null;
//		try {
//			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";
//
//			ps = connection.prepareStatement(sql);
//			ps.setString(1, loginID);
//			ps.setString(2, password);
//
//			ResultSet rs = ps.executeQuery();
//			List<Bean> userList = super.toBeanList(rs);
//			if (userList.isEmpty() == true) {
//				return null;
//			} else if (2 <= userList.size()) {
//				//ID重複
//				throw new IllegalStateException("2 <= userList.size()");
//			} else {
//				return (User) userList.get(0);
//			}
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
//			sql.append(" AND");
//			sql.append(" update_date = ?");
//
//			ps = connection.prepareStatement(sql.toString());
//
//			ps.setString(1, user.getLoginID());
//			ps.setString(2, user.getPassword());
//			ps.setString(3, user.getName());
//			ps.setInt(4, user.getBranchID());
//			ps.setInt(5, user.getDepartmentID());
//			ps.setBoolean(6, user.isStopped());
//			ps.setInt(6, user.getId());
//			ps.setTimestamp(7, new Timestamp(user.getUpdatedDate().getTime()));
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
