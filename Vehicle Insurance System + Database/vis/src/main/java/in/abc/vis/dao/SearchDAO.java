package in.abc.vis.dao;

import in.abc.vis.model.SearchModel;
import in.abc.vis.model.UserModel;


public interface SearchDAO {
	
	UserModel searchResult(SearchModel search);
}
