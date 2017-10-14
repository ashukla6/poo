package com.capgemini.uas.dao;

import java.util.List;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;

public interface IApplicantDao {
	public List<ProgramScheduledBean> showProgramScheduled() throws UniversityException;
	public int addDetail(ApplicationBean ab) throws UniversityException;
	public ApplicationBean showStatus(int applicationId) throws UniversityException;
}
