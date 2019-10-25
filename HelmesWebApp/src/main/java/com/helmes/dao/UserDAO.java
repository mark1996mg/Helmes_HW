package com.helmes.dao;

import com.helmes.model.User;

public interface UserDAO {
	
	public int insertOrUpdateUser(User user);
	public int insertOrReplaceUserSectors(User user);
	public User getUser(String sessionId);

}
