package com.capgemini.uas.dao;

import java.util.List;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ProgramOfferedBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;

public interface IAdminDao {
	
	public void addProgramOffered(ProgramOfferedBean pOffered) throws UniversityException;
	public boolean deleteProgramOffered(String programName) throws UniversityException;
	public boolean deleteProgramScheduled(String programId) throws UniversityException;
	
	public int addProgramScheduled(ProgramScheduledBean pScheduled) throws UniversityException;
	
	public List<ProgramScheduledBean> getAllDetails() throws UniversityException;
	
	public List<String> getAllScheduleId() throws UniversityException;
	public List<String> getAllProgramName() throws UniversityException;
	public List<String> getAllScheduleNames() throws UniversityException;
	
	public List<ApplicationBean> getApplicantsByStatusByScheduledProgramId(String status,String pScheduledId) throws UniversityException;
	public List<ApplicationBean> getApplicantsByScheduledProgramId(String pScheduledId) throws UniversityException;
	public boolean generateParticipants(List<ApplicationBean> confApplicants) throws UniversityException;
	public List<ApplicationBean> getAllConfirmedApplicants() throws UniversityException;
}
