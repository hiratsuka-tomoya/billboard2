package billboard.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class Posting extends UpdatableBean {

	private int id;
	private String title;
	private String text;
	private String category;
	private int userId;
	private Date createdDate;
	private Date updatedDate;

	@Override
	public void readResultSet(ResultSet rs) {
		try {
			this.setId(rs.getInt("id"));
			this.setTitle(rs.getString("title"));
			this.setText(rs.getString("text"));
			this.setCategory(rs.getString("category"));
			this.setUserId(rs.getInt("user_id"));
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
		sql.append("INSERT INTO postings ( ");
		sql.append("title");
		sql.append(", text");
		sql.append(", category");
		sql.append(", user_id");
		sql.append(") VALUES (");
		sql.append("'" + getTitle() + "'");
		sql.append("," + "'" + getText() + "'");
		sql.append("," + "'" + getCategory() + "'");
		sql.append("," + getUserId());
		sql.append(")");
		return sql.toString();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
