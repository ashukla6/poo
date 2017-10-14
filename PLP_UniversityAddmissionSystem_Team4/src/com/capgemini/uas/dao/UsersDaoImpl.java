package com.capgemini.uas.dao;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.capgemini.uas.dto.UsersBean;
import com.capgemini.uas.exception.UniversityException;

@Repository
public class UsersDaoImpl implements IUsersDao {
	static Logger myLogger = Logger.getLogger("myLogger");
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public UsersBean getUserOnId(String loginId) throws UniversityException {
		TypedQuery<UsersBean> qry = manager.createQuery(IQueryMapper.GET_USER,UsersBean.class);
		qry.setParameter("ptitle",loginId);
		UsersBean ub = qry.getSingleResult();
		return ub;
	}

}
