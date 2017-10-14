package com.capgemini.uas.service;

import java.time.LocalDate;
import java.util.List;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.UsersBean;
import com.capgemini.uas.exception.UniversityException;

public interface IMacService {
	public List<Integer> getApplicantId() throws UniversityException;
	public List<String> getscheduleId() throws UniversityException;
	public List<ApplicationBean> getApplicantsOnSchduledId(String scheduledProgramId) throws UniversityException;
	public List<ApplicationBean> getApplicantsAfterInterviewOnId(String scheduledProgramId) throws UniversityException;
	public String updateApplicantStatus(int applicationId,String status) throws UniversityException;
	public void updateApplicantDateOfInterview(int applicationId, LocalDate dateOfInterview) throws UniversityException;
}
