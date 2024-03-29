package in.abc.vis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import in.abc.vis.constants.ApplicationConstants;
import in.abc.vis.dao.InsuranceDAO;
import in.abc.vis.model.InsuranceModel;


@Controller
public class InsuranceController {
	
	@Autowired
	InsuranceDAO insuranceDAO;
	
	/**
	 * Retrieve insurance form for adding
	 * @param userId
	 * @return Insurance form
	 * */
	@RequestMapping(value = "/insuranceForm")
	public ModelAndView openInsuranceForm(@RequestParam("userId") int userId) {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);

		InsuranceModel insurance = insuranceDAO.getInsuranceBasedOnUserId(userId);
		if(insurance != null) {

			mav.addObject("insurance", insurance);
		}else {

			mav.addObject("userId", userId);
			mav.addObject("insurance", new InsuranceModel());
		}
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.INSURANCE);
		mav.addObject("userClickInsuranceForm", true);
		return mav;
	}
	
	/**
	 * Save/Update the insurance data
	 * @param insurance
	 * @return Insurance list
	 * */
	@RequestMapping("/saveInsuranceData")
	public ModelAndView saveInsurance(@ModelAttribute("insurance") InsuranceModel insurance) {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);
		if(insurance.getId() == null) {
			if(insuranceDAO.save(insurance)) {
				mav.addObject(ApplicationConstants.MSG, "Data Saved");
				mav.addObject("successDialog", true);
			}
		}else {
			if(insuranceDAO.save(insurance)) {
				mav.addObject(ApplicationConstants.MSG, "Data Updated");
				mav.addObject("updateDialog", true);
			}
		}
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.INSURANCE);
		mav.addObject("userClicksInsurance", true);
		mav.addObject("list", insuranceDAO.list());
		return mav;
	}
	
	/**
	 * Retrieve insurance form for edit
	 * @param id
	 * @return Insurance form
	 * */
	@RequestMapping(value = "/editInsurance")
	public ModelAndView editInsurace(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);
		mav.addObject("insurance", insuranceDAO.get(id));
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.INSURANCE);
		mav.addObject("userClickInsuranceForm", true);
		return mav;
	}
	
	/**
	 * Delete the insurance data
	 * @param id
	 * @return Insurance list
	 * */
	@RequestMapping(value = "/deleteInsurance")
	public ModelAndView deleteInsurace(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);
		if(insuranceDAO.delete(id)) {
			mav.addObject("deleteDialog", true);
			mav.addObject(ApplicationConstants.MSG, "Data Deleted");
		}
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.INSURANCE);
		mav.addObject("userClicksInsurance", true);
		return mav;
	}
}
