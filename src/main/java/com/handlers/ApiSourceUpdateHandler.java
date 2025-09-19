package com.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.dao.InstanceUserDao;
import com.dao.UserDao;
import com.models.ApiUsers;

public class ApiSourceUpdateHandler implements UserSourceUpdateHandler<ApiUsers>{
	public boolean findDeletedUsers(List<ApiUsers> usersList, int instanceId) {
		HashSet<String> currentUsersName = new HashSet<>();
		for(ApiUsers users: usersList) {
			currentUsersName.add(users.getUserName());
		}
		HashSet<String> allUsersName = InstanceUserDao.getAllUsersNamebyInstanceId(instanceId);
		allUsersName.removeAll(currentUsersName);
		boolean result = UserDao.deleteUsersByName(allUsersName);
		//System.out.println(result);
		return result;
	}
	
	public boolean findNewUsers(List<ApiUsers> usersList, int instanceId) {
		boolean result = false;
		HashSet<String> currentUsersName = new HashSet<>();
		for(ApiUsers users: usersList) {
			currentUsersName.add(users.getUserName());
		}
		HashSet<String> allUsersName = InstanceUserDao.getAllUsersNamebyInstanceId(instanceId);
		currentUsersName.removeAll(allUsersName);
		//System.out.println(currentUsersName);
		List<ApiUsers> newUserObject = new ArrayList<>();
		if(currentUsersName.size() != 0) {
			for(String name: currentUsersName) {
				for(ApiUsers user: usersList) {
					if(user.getUserName().equals(name)) {
						newUserObject.add(user);
						break;
					}
				}
			}
			result = InstanceUserDao.insertApiUsers(newUserObject, instanceId);
			return result;
		}
		return true;
		
	}
	
	public boolean findUpdatedUsers(List<ApiUsers> usersList, int instanceId) {
		boolean result;
		HashSet<String> currentUsersName = new HashSet<>();
		for(ApiUsers users: usersList) {
			currentUsersName.add(users.getUserName());
		}
		HashSet<String> allUsersName = InstanceUserDao.getAllUsersNamebyInstanceId(instanceId);
		HashSet<String> exsistingNames = new HashSet<String>(currentUsersName);
		exsistingNames.retainAll(allUsersName);
		//System.out.println(exsistingNames);
		List<ApiUsers> newUserObject = new ArrayList<>();
		if(exsistingNames.size() == allUsersName.size()) {
			result = InstanceUserDao.updateApiUsers(usersList);
		}else {
			for(String name: exsistingNames) {
				for(ApiUsers user: usersList) {
					if(user.getUserName().equals(name)) {
						newUserObject.add(user);
						break;
					}
				}
			}
			result = InstanceUserDao.updateApiUsers(newUserObject);
		}
		//System.out.println(result);
		return result;
	}
}
