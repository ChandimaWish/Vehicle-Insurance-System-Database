package in.abc.vis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import in.abc.vis.constants.ApplicationConstants;
import in.abc.vis.dao.VehicleDAO;
import in.abc.vis.model.VehicleModel;

/**
 * Represents the controller for vehicle-related use cases
 */
@Controller
public class VehicleController {

	@Autowired
	VehicleDAO vehicleDAO;

	/**
	 * Retrieve the vehicle form for adding
	 *
	 * @param userId
	 * @return Vehicle form
	 */
	@RequestMapping(value = "/vehicleForm")
	public ModelAndView showVehicleForm(@RequestParam("userId") int userId) {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);
		VehicleModel vehicle = null;
		vehicle = vehicleDAO.getVehicleBasedOnUserId(userId);
		if (vehicle != null) {

			mav.addObject("vehicle", vehicle);
		} else {

			mav.addObject("userId", userId);
			mav.addObject("vehicle", new VehicleModel());
		}
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.VEHICLE);
		mav.addObject("userClickVehicleForm", true);
		return mav;
	}

	/**
	 * Saves/Update the vehicle data
	 *
	 * @param vehicle
	 * @return Vehicle list
	 */
	@RequestMapping("/saveVehicleData")
	public ModelAndView saveVehicle(@ModelAttribute("vehicle") VehicleModel vehicle,BindingResult bindingResult) {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);

		// Validate the Number Plate format
		if (!isValidNumberPlate(vehicle.getPlateNumber())) {
			bindingResult.addError(new FieldError("vehicle", "plateNumber", "Invalid Number Plate"));
		}

		// Check for validation errors
		if (bindingResult.hasErrors()) {
			// If there are validation errors, return to the form with error messages
			mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.VEHICLE);
			mav.addObject("userClickVehicleForm", true);
			mav.addObject("userId", vehicle.getUser().getId());
			mav.addObject("vehicle", vehicle); // Add the vehicle back to the ModelAndView
			return mav;
		}

		if (vehicle.getId() == null) {
			if (vehicleDAO.save(vehicle)) {
				mav.addObject(ApplicationConstants.MSG, "Data entered successfully");
				mav.addObject("successDialog", true);
			}
		} else {
			if (vehicleDAO.save(vehicle)) {
				mav.addObject(ApplicationConstants.MSG, "Data Updated successfully");
				mav.addObject("updateDialog", true);
			}
		}
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.VEHICLE);
		mav.addObject("userClicksVehicle", true);
		mav.addObject("list", vehicleDAO.list());
		return mav;
	}

	/**
	 * Retrieve the vehicle form for edit
	 *
	 * @param id
	 * @return Vehicle form
	 */
	@RequestMapping(value = "/editVehicle")
	public ModelAndView editVehicle(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);
		mav.addObject("vehicle", vehicleDAO.get(id));
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.VEHICLE);
		mav.addObject("userClickVehicleForm", true);
		mav.addObject("readOnly", true);
		return mav;
	}

	/**
	 * Helper method to check the validity of the Number Plate format
	 *
	 * @param plateNumber
	 * @return true if the format is valid, false otherwise
	 */
	private boolean isValidNumberPlate(String plateNumber) {
		// Check if the length is 7
		if (plateNumber.length() != 7) {
			return false;
		}

		// Check if the first three characters are alphabetic
		for (int i = 0; i < 3; i++) {
			if (!Character.isLetter(plateNumber.charAt(i))) {
				return false;
			}
		}

		// Check if the remaining characters are digits
		for (int i = 3; i < 7; i++) {
			if (!Character.isDigit(plateNumber.charAt(i))) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Delete the vehicle data
	 *
	 * @param id
	 * @return Vehicle list
	 */
	@RequestMapping(value = "/deleteVehicle")
	public ModelAndView deleteVehicle(@RequestParam("id") int id) {
		ModelAndView mav = new ModelAndView(ApplicationConstants.MASTER_PAGE);
		if (vehicleDAO.delete(id)) {
			mav.addObject("deleteDialog", true);
			mav.addObject(ApplicationConstants.MSG, "Data Deleted successfully");
		}
		mav.addObject(ApplicationConstants.TITLE, ApplicationConstants.VEHICLE);
		mav.addObject("userClicksVehicle", true);
		return mav;
	}
}
