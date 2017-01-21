package billboard.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Comment extends UpdatableBean {

	private int id;
	private String text;
	private int userId;
	private int postingId;
	private String userName;
	private Date createdDate;
	private Date updatedDate;

	@Override
	public void readResultSet(ResultSet rs) {
		try {
			this.setId(rs.getInt("id"));
			this.setText(rs.getString("text"));
			this.setUserId(rs.getInt("user_id"));
			this.setPostingId(rs.getInt("posting_id"));
			this.setUserName(rs.getString("user_name"));
			this.setCreatedDate(rs.getDate("created_at"));
			this.setUpdatedDate(rs.getDate("updated_at"));
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@Override
	public String getSqlInsert() {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO comments ( ");
		sql.append("text");
		sql.append(", user_id");
		sql.append(", posting_id");
		sql.append(") VALUES (");
		sql.append("'" + getText() + "'");
		sql.append("," + getUserId());
		sql.append("," + getPostingId());
		sql.append(")");
		return sql.toString();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPostingId() {
		return postingId;
	}
	public void setPostingId(int postingId) {
		this.postingId = postingId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	@Override
	public String getSqlUpdate() {
		// TODO 自動生成されたメソッド・スタブ
		return null;
	}

}
