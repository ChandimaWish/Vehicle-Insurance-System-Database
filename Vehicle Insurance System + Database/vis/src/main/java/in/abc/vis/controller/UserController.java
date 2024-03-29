package in.abc.vis.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;

import in.abc.vis.constants.ApplicationConstants;
import in.abc.vis.dao.AuthorityDAO;
import in.abc.vis.dao.UserDAO;
import in.abc.vis.model.Authorities;
import in.abc.vis.model.UserModel;
import in.abc.vis.util.PasswordEncode;


@Controller
public class UserController {
	
	@Autowired
	UserDAO userDAO;
	
	@Autowired
	AuthorityDAO authorityDAO;
	
	/**
	 * Create a new UserModel object
	 * @return User form
	 * */
	@RequestMapping(value = "/userForm")
	public ModelAndView showUserForm() {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.USER);
		mav.addObject("userClicksUserForm", true);
		mav.addObject("user", new UserModel());
		return mav;
	}
	
	/**
	 * Save the User data
	 * @param user
	 * @return User list
	 * */
	@RequestMapping(value = "/saveUserData")
	public ModelAndView saveUser(@ModelAttribute("user") UserModel user) {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);
		// Password strength validation
		if (user.getPassword().length() <= 6 || !user.getPassword().matches(".*[a-zA-Z].*")) {
			mav.addObject(ApplicationConstants.MSG, "Strong password needed");
			mav.addObject("errorDialog", true);
			mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.USER);
			mav.addObject("userClicksUserForm", true);
			return mav;
		}


		if(user.getId() == null) {
			if(userDAO.save(user)) {
				mav.addObject(ApplicationConstants.MSG, "Data entered successfully");
				mav.addObject("successDialog", true);
			}
		}else {
			if(userDAO.save(user)) {
				mav.addObject(ApplicationConstants.MSG, "Data updated successfully");
				mav.addObject("updateDialog", true);
			}
		}
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.USER);
		mav.addObject("userClicksUser", true);
		//retrieve the users list
		mav.addObject("list", userDAO.list());
		return mav;
	}
	
	/**
	 * Retrieve the user data based on Id
	 * @param id
	 * @return User form 
	 * */
	@RequestMapping(value = "/editUser")
	public ModelAndView editUser(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);
		UserModel user = userDAO.get(id);
		mav.addObject("user", user);
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.USER);
		mav.addObject("userClicksUserForm", true);
		return mav;
	}
	
	/**
	 * Delete the User data based on Id
	 * @param id
	 * @return User list
	 * */
	@RequestMapping(value = "/deleteUser")
	public ModelAndView deleteUser(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);
		if(userDAO.delete(id)) {
			mav.addObject("deleteDialog", true);
			mav.addObject(ApplicationConstants.MSG, "Data Deleted");
		}
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.USER);
		mav.addObject("userClicksUser", true);
		return mav;
	}
	
	/**
	 * It will execute for all the request mappings
	 * @return Authority list
	 * */
	@ModelAttribute("role")
	public List<Authorities> list(){
		return authorityDAO.list();
	}
	
	@RequestMapping(value = "/change/changePassword", method = RequestMethod.POST)
	public void getUser(HttpServletRequest request, String oldPassword, String newPassword, HttpServletResponse response) {
		try {

			boolean flag = false;
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			UserModel user = userDAO.findUserByName(authentication.getName());

			if(PasswordEncode.checkPassword(oldPassword, user.getPassword())) {
				userDAO.changePassword(newPassword, user);

				flag = true;
			}

			response.getWriter().write(new Gson().toJson(flag));
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
