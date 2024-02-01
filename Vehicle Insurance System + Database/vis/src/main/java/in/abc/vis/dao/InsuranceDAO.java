package in.abc.vis.dao;

import java.util.List;

import in.abc.vis.model.InsuranceModel;


public interface InsuranceDAO {
	
	boolean save(InsuranceModel insurance);
	
	List<InsuranceModel> list();
	
	boolean delete(int id);
	
	InsuranceModel get(int id);
	
	InsuranceModel getInsuranceBasedOnUserId(int userId);
}
