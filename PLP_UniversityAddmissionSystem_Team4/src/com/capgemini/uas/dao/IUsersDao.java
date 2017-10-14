package com.capgemini.uas.dao;

import com.capgemini.uas.dto.UsersBean;
import com.capgemini.uas.exception.UniversityException;

public interface IUsersDao {
	public UsersBean getUserOnId(String loginId) throws UniversityException;
}
