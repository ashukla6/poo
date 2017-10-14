package com.capgemini.uas.dao;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.exception.UniversityException;

@Repository
public class MacDaoImpl implements IMacDao {
	static Logger myLogger = Logger.getLogger("myLogger");
	
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<ApplicationBean> getApplicantsOnSchduledId(
			String scheduledProgramId) throws UniversityException {
		TypedQuery<ApplicationBean> qry = manager.createQuery(IQueryMapper.LIST_APPLICANTS,ApplicationBean.class);
		qry.setParameter("ptitle",scheduledProgramId);
		List<ApplicationBean> applicantsList = qry.getResultList();
		return applicantsList;
	}

	@Override
	public List<ApplicationBean> getApplicantsAfterInterviewOnId(
			String scheduledProgramId) throws UniversityException {
		// Need to change query
		TypedQuery<ApplicationBean> qry = manager.createQuery(IQueryMapper.LIST_APPLICANTS_ACCEPTED1,ApplicationBean.class);
		qry.setParameter("ptitle",scheduledProgramId);
		List<ApplicationBean> applicantsAcceptedList = qry.getResultList();
		return applicantsAcceptedList;
	}

	@Override
	public String updateApplicantStatus(int applicationId, String status)
			throws UniversityException {
		ApplicationBean ab = manager.find(ApplicationBean.class,applicationId);
		ab.setStatus(status);
		manager.merge(ab);
		return status;
	}

	@Override
	public void updateApplicantDateOfInterview(int applicationId, LocalDate dateOfInterview)
			throws UniversityException {
		ApplicationBean ab = manager.find(ApplicationBean.class,applicationId);
		ab.setDateOfInterview(dateOfInterview);
		manager.merge(ab);
	}

	@Override
	public List<Integer> getApplicantId() throws UniversityException {
		TypedQuery<Integer> qry = manager.createQuery(IQueryMapper.GET_APPLICANT_ID,Integer.class);
		List<Integer> applicantsIdList = qry.getResultList();
		return applicantsIdList;
	}

	@Override
	public List<String> getscheduleId() throws UniversityException {
		TypedQuery<String> qry = manager.createQuery(IQueryMapper.LIST_PROGRAMID,String.class);
		List<String> applicantsIdList = qry.getResultList();
		return applicantsIdList;
	}

}
