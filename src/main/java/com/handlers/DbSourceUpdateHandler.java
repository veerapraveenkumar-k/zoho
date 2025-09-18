package com.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.dao.InstanceUserDao;
import com.dao.UserDao;
import com.models.DbUsers;

public class DbSourceUpdateHandler implements UserSourceUpdateHandler<DbUsers> {
	public boolean findDeletedUsers(List<DbUsers> usersList, int instanceId) {
		HashSet<String> currentUsersName = new HashSet<>();
		for(DbUsers users: usersList) {
			currentUsersName.add(users.getUserName());
		}
		HashSet<String> allUsersName = InstanceUserDao.getAllUsersNamebyInstanceId(instanceId);
		allUsersName.removeAll(currentUsersName);
		boolean result = UserDao.deleteUsersByName(allUsersName);
		return result;
	}
	
	public boolean findNewUsers(List<DbUsers> usersList, int instanceId) {
		boolean result = false;
		HashSet<String> currentUsersName = new HashSet<>();
		for(DbUsers users: usersList) {
			currentUsersName.add(users.getUserName());
		}
		HashSet<String> allUsersName = InstanceUserDao.getAllUsersNamebyInstanceId(instanceId);
		currentUsersName.removeAll(allUsersName);
		List<DbUsers> newUserObject = new ArrayList<>();
		if(currentUsersName.size() != 0) {
			for(String name: currentUsersName) {
				for(DbUsers user: usersList) {
					if(user.getUserName().equals(name)) {
						newUserObject.add(user);
						break;
					}
				}
			}
			result = InstanceUserDao.insertDbUsers(newUserObject, instanceId);
			return result;
		}
		return true;
		
	}
	
	public boolean findUpdatedUsers(List<DbUsers> usersList, int instanceId) {
		boolean result;
		HashSet<String> currentUsersName = new HashSet<>();
		for(DbUsers users: usersList) {
			currentUsersName.add(users.getUserName());
		}
		HashSet<String> allUsersName = InstanceUserDao.getAllUsersNamebyInstanceId(instanceId);
		HashSet<String> exsistingNames = new HashSet<String>(currentUsersName);
		exsistingNames.retainAll(allUsersName);
		//System.out.println(exsistingNames);
		List<DbUsers> newUserObject = new ArrayList<>();
		if(exsistingNames.size() == allUsersName.size()) {
			result = InstanceUserDao.updateDbUsers(usersList);
		}else {
			for(String name: exsistingNames) {
				for(DbUsers user: usersList) {
					if(user.getUserName().equals(name)) {
						newUserObject.add(user);
						break;
					}
				}
			}
			result = InstanceUserDao.updateDbUsers(newUserObject);
		}
		return result;
	}	
}
