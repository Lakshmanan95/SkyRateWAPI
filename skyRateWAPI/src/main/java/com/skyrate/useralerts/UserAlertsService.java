package com.skyrate.useralerts;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.skyrate.useralerts.dao.UserAlertsMappingrepository;
import com.skyrate.useralerts.dao.UserAlertsQuery;
import com.skyrate.useralerts.dao.UserAlertsRepository;
import com.skyrate.useralerts.dao.UserAlertsResponseRepository;
import com.skyrate.useralerts.entity.UserAlerts;
import com.skyrate.useralerts.entity.UserAlertsMapping;
import com.skyrate.useralerts.entity.UserAlertsResponse;
import com.skyrate.useralerts.model.UserAlertResponseCounts;


@Service
@Transactional
public class UserAlertsService {

	@Autowired
	UserAlertsRepository userAlertsRepository;
	@Autowired
	UserAlertsQuery userAlertsQuery;
	@Autowired
	UserAlertsResponseRepository userAlertsResponseRepository;
	@Autowired
	UserAlertsMappingrepository userAlertsMappingRepository;

	public UserAlertsMapping getByUserMappingId(int id){
		return this.userAlertsMappingRepository.findById(id);
	}
	public UserAlertsMapping getByUserIdAndAlertId(int id,int alertId,int businessId){
		return this.userAlertsMappingRepository.findByUserIdAndAlertIdAndBusinessId(id,alertId, businessId);
	}
	public UserAlertsMappingrepository getUserAlertsMappingRepository() {
		return userAlertsMappingRepository;
	}

	public void setUserAlertsMappingRepository(UserAlertsMappingrepository userAlertsMappingRepository) {
		this.userAlertsMappingRepository = userAlertsMappingRepository;
	}

	public UserAlertsResponseRepository getUserAlertsResponseRepository() {
		return userAlertsResponseRepository;
	}

	public void setUserAlertsResponseRepository(UserAlertsResponseRepository userAlertsResponseRepository) {
		this.userAlertsResponseRepository = userAlertsResponseRepository;
	}

	public UserAlertsRepository getUserAlertsRepository() {
		return userAlertsRepository;
	}

	public void setUserAlertsRepository(UserAlertsRepository userAlertsRepository) {
		this.userAlertsRepository = userAlertsRepository;
	}

	public List<UserAlertsResponse> getUserAlerts(){
		return this.userAlertsResponseRepository.findAll();
	}

	public UserAlertsResponse getByUserId(int id){
		return this.userAlertsResponseRepository.findById(id);
	}
	//	public List<UserAlertsResponse> getByUseralertsType(Integer responseYes ){
	//		return this.userAlertsResponseRepository.findByType(responseYes);
	//	}
	public List<UserAlerts> getMyAlerts(int userId){

		List<UserAlerts> retUserAlerts = new ArrayList<UserAlerts>();

		/* get the alert */
		List<UserAlerts> myAlerts = userAlertsQuery.getMyEligibleAlerts(userId);
		//System.out.println(JSONUtil.toJson(myAlerts));
		for(UserAlerts userAlert : myAlerts){
			UserAlertResponseCounts allCnt = userAlertsQuery.getUserAlertResponseCounts(userId, userAlert.getId());
		//	int noCnt = userAlertsQuery.getUserAlertResponseNoCount(userId, userAlert.getId());
			/*check the user has already responded for the alerts */
			int noCnt= allCnt.getNoCnt();
			if(allCnt.getYesCnt()>0) {
				return retUserAlerts;
			}
			System.out.println(userAlert.getAlertFor() +":NoCount:::"+noCnt);
			if(noCnt>0){
				if(userAlert.getTotalReminderTimes() > noCnt){
					int remCnt = userAlertsQuery.getAlertByFrequency(userId, userAlert.getId(),userAlert.getReminderFrequency());
					System.out.println(userAlert.getAlertFor() +":REMCount:::"+remCnt);
					if(remCnt >0){
						//userAlerts = userAlertsQuery.getMyAlerts(userId);
						retUserAlerts.add(userAlert);
						return retUserAlerts;
					}
				}
			}else{
				/* check the logged in count */
				int loginCnt = userAlertsQuery.getLoginCount(userId);
				if(loginCnt >2){
					//System.out.println("User Alert : User Login cnt"+ loginCnt);
					//List<UserAlerts> userAlerts = userAlertsQuery.getMyAlerts(userId);
					//if(myAlerts!=null && myAlerts.size()>0){
						retUserAlerts.add(userAlert);
						return retUserAlerts;
				//	}

				}
				
			}

	}
	return retUserAlerts;
}

public UserAlertsQuery getUserAlertsQuery() {
	return userAlertsQuery;
}

public void setUserAlertsQuery(UserAlertsQuery userAlertsQuery) {
	this.userAlertsQuery = userAlertsQuery;
}




}
