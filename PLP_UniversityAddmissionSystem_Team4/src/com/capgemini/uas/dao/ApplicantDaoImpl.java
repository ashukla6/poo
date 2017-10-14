package com.capgemini.uas.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;

@Repository
public class ApplicantDaoImpl implements IApplicantDao {
	static Logger myLogger = Logger.getLogger("myLogger");
	
	@PersistenceContext
	private EntityManager manager;
	
	// same function getAllDetails in admin dao
	@Override
	public List<ProgramScheduledBean> showProgramScheduled()
			throws UniversityException {
		TypedQuery<ProgramScheduledBean> qry = manager.createQuery(IQueryMapper.LIST_PROGRAM,ProgramScheduledBean.class);
		List<ProgramScheduledBean> programSchList = qry.getResultList();
		return programSchList;	
	}

	@Override
	public int addDetail(ApplicationBean ab) throws UniversityException {
		manager.persist(ab);
		return ab.getApplicationId();
	}
	
	@Override
	public ApplicationBean showStatus(int applicationId)
			throws UniversityException {
		ApplicationBean ab = manager.find(ApplicationBean.class,applicationId);
		return ab;
	}
}