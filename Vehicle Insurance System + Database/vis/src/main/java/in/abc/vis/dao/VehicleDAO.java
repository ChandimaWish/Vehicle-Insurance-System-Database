package in.abc.vis.dao;

import java.util.List;

import in.abc.vis.model.VehicleModel;


public interface VehicleDAO {
	
	boolean save(VehicleModel vehicle);
	
	List<VehicleModel> list();
	
	boolean delete(int id);
	
	VehicleModel get(int id);
	
	VehicleModel getVehicleBasedOnUserId(int userId);
}
