package com.services;

import com.dao.CommonDao;
import com.models.*;

public class LoginService {
	public static Common userValidation(String name, String pass) {
		return CommonDao.authentication(name, pass);
	}
}
