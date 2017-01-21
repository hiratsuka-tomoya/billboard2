package billboard.dao;

import static billboard.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import billboard.beans.Bean;
import billboard.beans.UserPosting;
import billboard.exception.SQLRuntimeException;

public class UserPostingDao extends Dao {

	public UserPostingDao() {
		super("users_postings");
	}

	@Override
	protected Bean makeNewBean() {
		return new UserPosting();
	}

	public List<Bean> getRefinedBeans(Connection connection, int num, String category, String startDate, String endDate) {
		PreparedStatement ps = null;
		String strConnect = "";
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("SELECT * FROM " + tableName + " WHERE ");
			if (StringUtils.isNotEmpty(category)) {
				sql.append("category = " + "'" + category + "'");
				strConnect = " AND ";
			}
			if (StringUtils.isNotEmpty(startDate)) {
//				startDate = startDate.replaceAll("/", "-");	スラッシュでも比較してくれるっぽい
				sql.append(strConnect + "created_at >= " +  "'" + startDate + "'");
				strConnect = " AND ";
			}
			if (StringUtils.isNotEmpty(endDate)) {
//				endDate = endDate.replaceAll("/", "-");	スラッシュでも比較してくれるっぽい
				sql.append(strConnect + "created_at <= " +  "'" + endDate + " 23:59:59'");
				strConnect = " AND ";
			}
			sql.append(" ORDER BY created_at DESC limit " + num);

			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Bean> ret = toBeanList(rs);
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

}
