package com.services;

import com.dao.InstanceDao;
import com.dao.InstanceUserDao;
import com.dao.UserDao;
import com.models.ApiInstance;
import com.models.DbInstance;
import com.models.Instance;
import com.models.JsonInstance;

public class SourceService {
	public static void deleteUsersFromSource(int userId) throws Exception {
		String name = UserDao.getUserNameById(userId);
		Instance instanceObj = InstanceDao.getInstanceByUserId(userId);
		switch(instanceObj.getType()) {
		case "DB":
				DbInstance dbInstanceObj = InstanceDao.getDbInstanceDetails(instanceObj.getId());
				InstanceUserDao.deleteDbUsersFromSource(dbInstanceObj, name);
		case "JSON":
				JsonInstance jsonInstanceObj = InstanceDao.getJsonInstanceDetails(instanceObj.getId());
				InstanceUserDao.deleteUserFromJsonFile(jsonInstanceObj, name);
		case "API":
				ApiInstance apiInstanceObj = InstanceDao.getApiInstanceDetails(instanceObj.getId());
				InstanceUserDao.deleteUserFromOktaApi(apiInstanceObj, name);
		}
	}
	
	public static void updateUserService(int id, String name, String pass, String email, String mobileNum) throws Exception {
		Instance instanceObj = InstanceDao.getInstanceByUserId(id);
		switch(instanceObj.getType()) {
		case "DB":
			DbInstance dbInstanceObj = InstanceDao.getDbInstanceDetails(instanceObj.getId());
			InstanceUserDao.updateDbUserInSource(name, pass, email, mobileNum, dbInstanceObj);
		case "JSON":
			JsonInstance jsonInstanceObj = InstanceDao.getJsonInstanceDetails(instanceObj.getId());
			InstanceUserDao.updateUserInJsonFile(pass, email, mobileNum, jsonInstanceObj, name);
		case "API":
			ApiInstance apiInstanceObj = InstanceDao.getApiInstanceDetails(instanceObj.getId());
		}
	}
}
