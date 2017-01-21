package billboard.dao;

import billboard.beans.Bean;
import billboard.beans.UserComment;

public class UserCommentDao extends Dao {

	public UserCommentDao() {
		super("users_comments");
	}

	@Override
	protected Bean makeNewBean() {
		return new UserComment();
	}
}
