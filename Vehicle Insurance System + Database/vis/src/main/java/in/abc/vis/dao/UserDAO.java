package in.abc.vis.dao;

import java.util.List;

import in.abc.vis.model.UserModel;


public interface UserDAO {
	
	boolean save(UserModel user);
	
	List<UserModel> list();
	
	UserModel get(int id);
	
	boolean delete(int id);
	
	UserModel findUserByName(String name);
	
	boolean changePassword(String newPassword, UserModel user);
}
