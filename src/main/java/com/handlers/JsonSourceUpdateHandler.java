package com.handlers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import com.dao.InstanceUserDao;
import com.dao.UserDao;
import com.models.JsonUser;

public class JsonSourceUpdateHandler implements UserSourceUpdateHandler<JsonUser>{
	public boolean findDeletedUsers(List<JsonUser> usersList, int instanceId) {
		HashSet<String> currentUsersName = new HashSet<>();
		for(JsonUser users: usersList) {
			currentUsersName.add(users.getUserName());
		}
		HashSet<String> allUsersName = InstanceUserDao.getAllUsersNamebyInstanceId(instanceId);
		allUsersName.removeAll(currentUsersName);
		boolean result = UserDao.deleteUsersByName(allUsersName);
		return result;
	}
	
	public boolean findNewUsers(List<JsonUser> usersList, int instanceId) {
		boolean result = false;
		HashSet<String> currentUsersName = new HashSet<>();
		for(JsonUser users: usersList) {
			currentUsersName.add(users.getUserName());
		}
		HashSet<String> allUsersName = InstanceUserDao.getAllUsersNamebyInstanceId(instanceId);
		currentUsersName.removeAll(allUsersName);
		//System.out.println(currentUsersName);
		List<JsonUser> newUserObject = new ArrayList<>();
		if(currentUsersName.size() != 0) {
			for(String name: currentUsersName) {
				for(JsonUser user: usersList) {
					if(user.getUserName().equals(name)) {
						newUserObject.add(user);
						break;
					}
				}
			}
			result = InstanceUserDao.insertJsonUsers(newUserObject, instanceId);
			return result;
		}
		return true;
		
	}
	
	public boolean findUpdatedUsers(List<JsonUser> usersList, int instanceId) {
		boolean result;
		HashSet<String> currentUsersName = new HashSet<>();
		for(JsonUser users: usersList) {
			currentUsersName.add(users.getUserName());
		}
		HashSet<String> allUsersName = InstanceUserDao.getAllUsersNamebyInstanceId(instanceId);
		HashSet<String> exsistingNames = new HashSet<String>(currentUsersName);
		exsistingNames.retainAll(allUsersName);
		//System.out.println(exsistingNames);
		List<JsonUser> newUserObject = new ArrayList<>();
		if(exsistingNames.size() == allUsersName.size()) {
			result = InstanceUserDao.updateJsonUsers(usersList);
		}else {
			for(String name: exsistingNames) {
				for(JsonUser user: usersList) {
					if(user.getUserName().equals(name)) {
						newUserObject.add(user);
						break;
					}
				}
			}
			result = InstanceUserDao.updateJsonUsers(newUserObject);
		}
		return result;
	}
}	
