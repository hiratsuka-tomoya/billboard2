package billboard.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

public class User extends UpdatableBean {
	private static final long serialVersionUID = 1L;

	private int id;
	private String loginId;
	private String password;
	private String name;
	private int branchId;
	private int departmentId;
	private boolean isStopped;
	private Date createdDate;
	private Date updatedDate;

	@Override
	public void readResultSet(ResultSet rs) {
		try {
			this.setId(rs.getInt("id"));
			this.setLoginId(rs.getString("login_ID"));
			this.setPassword(rs.getString("password"));
			this.setName(rs.getString("name"));
			this.setBranchId(rs.getInt("branch_id"));
			this.setDepartmentId(rs.getInt("department_id"));
			this.setStopped(rs.getBoolean("is_Stopped"));
			this.setCreatedDate(rs.getTimestamp("created_at"));
			this.setUpdatedDate(rs.getTimestamp("updated_at"));
		} catch (SQLException e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
	}

	@Override
	public String getSqlInsert() {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO users ( ");
		sql.append("login_id");
		sql.append(", password");
		sql.append(", name");
		sql.append(", branch_id");
		sql.append(", department_id");
		sql.append(", is_stopped");
		sql.append(") VALUES (");
		sql.append("'" + getLoginId() + "'");
		sql.append("," + "'" +getPassword() + "'");
		sql.append("," + "'" + getName() + "'");
		sql.append("," + getBranchId());
		sql.append("," + getDepartmentId());
		sql.append("," + isStopped());
		sql.append(")");
		return sql.toString();
	}

	public String getSqlUpdate() {
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE users SET ");
		sql.append("login_id = " + "'" + getLoginId() + "'");
		sql.append(", password = " + "'" +getPassword() + "'");
		sql.append(", name = " + "'" + getName() + "'");
		sql.append(", branch_id = " + getBranchId());
		sql.append(", department_id = " + getDepartmentId());
		sql.append(", is_stopped = " + isStopped());
		sql.append(" WHERE ");
		sql.append("id = " + getId());
		return sql.toString();
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLoginId() {
		return loginId;
	}
	public void setLoginId(String loginID) {
		this.loginId = loginID;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBranchId() {
		return branchId;
	}
	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}
	public int getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(int departmentId) {
		this.departmentId = departmentId;
	}
	public boolean isStopped() {
		return isStopped;
	}
	public void setStopped(boolean isStopped) {
		this.isStopped = isStopped;
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


}
