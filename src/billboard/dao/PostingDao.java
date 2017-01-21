package billboard.dao;

import billboard.beans.Bean;
import billboard.beans.Posting;

public class PostingDao extends UpdatableDao {

	public PostingDao() {
		super("postings");
	}

	@Override
	protected Bean makeNewBean() {
		return new Posting();
	}

}
