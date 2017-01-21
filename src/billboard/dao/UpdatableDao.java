package billboard.dao;

import static billboard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import billboard.beans.UpdatableBean;
import billboard.exception.SQLRuntimeException;

public abstract class UpdatableDao extends Dao {

	UpdatableDao(String tableName) {
		super(tableName);
	}

	public void insert(Connection connection, UpdatableBean bean) {

		Statement statement = null;
		try {
			String sql = bean.getSqlInsert();
			statement = connection.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(statement);
		}
	}

	public Boolean deleteBeans(Connection connection, int id) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM " + tableName + " WHERE id = " + id);
			ps = connection.prepareStatement(sql.toString());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public void update(Connection connection, UpdatableBean bean) {

		Statement statement = null;
		try {
			String sql = bean.getSqlUpdate();
			statement = connection.createStatement();
			statement.executeUpdate(sql.toString());
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(statement);
		}
	}
}
