package com.cd.autoTest.service;

import java.security.MessageDigest;
import java.util.List;

import com.cd.autoTest.action.UserAction;
import com.cd.autoTest.dao.UserDAO;
import com.cd.autoTest.model.User;

import sun.security.provider.MD5;

public class UserService extends IService {
	private UserDAO userDao = null;

	public int deleteUser(int id) {
		try {
			return userDao.deleteUser(id);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public List<User> findUserList() {
		return userDao.findUserList();
	}

	public int insertUser(User user) {
		try {
			String password = user.getPassword();
			password = UserAction.MD5(password);
			user.setPassword(password);
			return userDao.insertUser(user);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public int updateUser(User user) {
		try {
			return userDao.updateUser(user);
		} catch (Exception e) {
			log.info(e.toString());
			throw new RuntimeException(e.toString());
		}
	}

	public User findUserByName(String username) {
		return userDao.findUserByName(username);
	}

	public User findUserById(int id) {
		User user=userDao.findUserById(id);
		if(user==null){
			user=new User();
		}
		return user;
	}

	public UserDAO getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDAO userDao) {
		this.userDao = userDao;
	}
}
