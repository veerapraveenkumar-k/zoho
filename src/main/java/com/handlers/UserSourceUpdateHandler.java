package com.handlers;

import java.util.List;

public interface UserSourceUpdateHandler <T> {
	public boolean findDeletedUsers(List<T> usersList, int instanceId);
	public boolean findNewUsers(List<T> usersList, int instanceId);
	public boolean findUpdatedUsers(List<T> usersList, int instanceId);
}
