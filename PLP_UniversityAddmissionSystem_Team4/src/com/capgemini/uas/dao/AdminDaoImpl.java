package com.capgemini.uas.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ParticipantBean;
import com.capgemini.uas.dto.ProgramOfferedBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;

@Repository
public class AdminDaoImpl implements IAdminDao {
	static Logger myLogger = Logger.getLogger("myLogger");
	
	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public void addProgramOffered(ProgramOfferedBean pOffered)
			throws UniversityException {
			manager.persist(pOffered);
	}
	
	@Override
	public boolean deleteProgramOffered(String programName)
			throws UniversityException {
		ProgramOfferedBean pn = manager.find(ProgramOfferedBean.class,programName);
		manager.remove(pn);
		return true;
	}

	@Override
	public int addProgramScheduled(ProgramScheduledBean pScheduled)
			throws UniversityException {
		manager.persist(pScheduled);
		return 1;
	}

	@Override
	public boolean deleteProgramScheduled(String programId)
			throws UniversityException {
		ProgramScheduledBean psb = manager.find(ProgramScheduledBean.class,programId);
		manager.remove(psb);
		return true;
	}

	@Override
	public List<ProgramScheduledBean> getAllDetails()
			throws UniversityException {
		TypedQuery<ProgramScheduledBean> qry = manager.createQuery(IQueryMapper.LIST_PROGRAM,ProgramScheduledBean.class);
		List<ProgramScheduledBean> programSchList = qry.getResultList();
		return programSchList;		
	}
	
    // same function getscheduleId in mac
	@Override
	public List<String> getAllScheduleId() throws UniversityException {
		
		TypedQuery<String> qry = manager.createQuery(IQueryMapper.LIST_PROGRAMID,String.class);
		List<String> applicantsIdList = qry.getResultList();
		return applicantsIdList;
			}
	@Override
	public List<String> getAllProgramName() throws UniversityException {
		
		TypedQuery<String> qry = manager.createQuery(IQueryMapper.LIST_PROGRAMNAME_OFFERED,String.class);
		List<String> programNameOfferedList = qry.getResultList();
		return programNameOfferedList;

}

	@Override
	public List<String> getAllScheduleNames() throws UniversityException {
		TypedQuery<String> qry = manager.createQuery(IQueryMapper.LIST_PROGRAMNAME_SCHEDULED,String.class);
		List<String> programNameScheduledList = qry.getResultList();
		return programNameScheduledList;
	}

	// same function getApplicantsOnSchduledId in mac
	@Override
	public List<ApplicationBean> getApplicantsByScheduledProgramId(String pScheduledId) throws UniversityException {
		TypedQuery<ApplicationBean> qry = manager.createQuery(IQueryMapper.LIST_APPLICANTS,ApplicationBean.class);
		qry.setParameter("ptitle",pScheduledId);
		List<ApplicationBean> applicantsList = qry.getResultList();
		return applicantsList;
	}

	@Override
	public List<ApplicationBean> getApplicantsByStatusByScheduledProgramId(String status, String pScheduledId)
			throws UniversityException {
		TypedQuery<ApplicationBean> qry = manager.createQuery(IQueryMapper.LIST_APPLICANTS_BYSTATUS,ApplicationBean.class);
		qry.setParameter("st",status);
		qry.setParameter("ptitle",pScheduledId);
		List<ApplicationBean> applicantsList = qry.getResultList();
		return applicantsList;
	}
	@Override
	public boolean generateParticipants(List<ApplicationBean> confApplicants) throws UniversityException {
		for (ApplicationBean applicationBean : confApplicants) {
			ParticipantBean pb = new ParticipantBean();
			pb.setApplicationId(applicationBean.getApplicationId());
			pb.setEmailId(applicationBean.getEmailId());
			pb.setScheduledProgramId(applicationBean.getScheduledProgramId());
			manager.persist(pb);
		}
		return true;
	}
	@Override
	public List<ApplicationBean> getAllConfirmedApplicants()
			throws UniversityException {
		TypedQuery<ApplicationBean> qry = manager.createQuery(IQueryMapper.LIST_APPLICANTS_CONFIRMED,ApplicationBean.class);
		List<ApplicationBean> confApplicantsList = qry.getResultList();
		return confApplicantsList;
	}	
}