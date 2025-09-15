package com.services;

import com.utils.Db;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.dao.InstanceDao;
import com.dao.InstanceUserDao;
import com.models.*;

public class ImportUserService {
	public static String dbValidaion(int adminId, String host, String port, String dbname, String tableName, String user, String password) {
		if(host == "" || port == "" || dbname == "" || tableName == "" || user == "" || password == "") {
			return "Values cannot be empty..";
		}else {
			String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname;
			if(Db.checkConnection(url, user, password)) {
				return InstanceDao.checkDbInstance(adminId, host, port, dbname, tableName, user, password);
			}
			return "Failed to Create Instance.";
		}
	}
	
	public static boolean importUsers(int instanceId) throws FileNotFoundException, IOException {
		boolean result = false;
		Instance instance = InstanceDao.getInstance(instanceId);
		if(instance.getType().equals("DB")) {
			result = importUserFromDb(instance, instanceId);
		}else if(instance.getType().equals("JSON")) {
			result = importUsersFromJson(instance, instanceId);
		}
		return result;
	}
	
	public static boolean importUserFromDb(Instance instanceObj, int instanceId) {
		DbInstance dbInstanceObj = InstanceDao.getDbInstanceDetails(instanceObj.getId());
		ArrayList<DbUsers> dbUsersList = InstanceUserDao.getDbUsersList(dbInstanceObj);
		boolean result = InstanceUserDao.importDbUsers(dbUsersList, instanceId);
		return result;
	}
	
	public static boolean importUsersFromJson(Instance instanceObj, int instanceId) throws FileNotFoundException, IOException {
		JsonInstance jsonInstanceObj = InstanceDao.getJsonInstanceDetails(instanceId);
		List<JsonUser> usersList = InstanceUserDao.getJsonUsersList(jsonInstanceObj.getFileName());
		boolean result = InstanceUserDao.importJsonUsers(usersList, instanceId);
		return result;
	}
	

}
