package com.capgemini.uas.dto;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="APPLICANTS")
public class ApplicationBean {
	
	@Id
	@Column(name="APPLICATION_ID")
	private int applicationId;
	
	@Column(name="FULL_NAME")
	private String fullName;
	
	@Column(name="DATE_OF_BIRTH")
	private LocalDate dateOfBirth;
	
	@Column(name="HIGHEST_QUALIFICATION")
	private String highestQualification;
	
	@Column(name="MARKS_OBTAINED")
	private int marksObtained;
	
	@Column(name="GOALS")
	private String goals;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="SCHEDULED_PROGRAM_ID")
	private String scheduledProgramId;
	
	@Column(name="STATUS")
	private String status="applied";
	
	@Column(name="DATE_OF_INTERVIEW")
	private LocalDate dateOfInterview;
	
	public ApplicationBean() {
	}
	
	
	
	public ApplicationBean(int applicationId, String fullName,
			LocalDate dateOfBirth, String highestQualification,
			int marksObtained, String goals, String emailId,
			String scheduledProgramId, String status, LocalDate dateOfInterview) {
		super();
		this.applicationId = applicationId;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.highestQualification = highestQualification;
		this.marksObtained = marksObtained;
		this.goals = goals;
		this.emailId = emailId;
		this.scheduledProgramId = scheduledProgramId;
		this.status = status;
		this.dateOfInterview = dateOfInterview;
	}



	public ApplicationBean(String fullName, LocalDate dateOfBirth,
			String highestQualification, int marksObtained, String goals,
			String emailId, String scheduledProgramId, String status,
			LocalDate dateOfInterview) {
		super();
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.highestQualification = highestQualification;
		this.marksObtained = marksObtained;
		this.goals = goals;
		this.emailId = emailId;
		this.scheduledProgramId = scheduledProgramId;
		this.status = status;
		this.dateOfInterview = dateOfInterview;
	}

	public ApplicationBean( String fullName, LocalDate dateOfBirth,int applicationId, String goals, String emailId) {
		super();
		this.applicationId = applicationId;
		this.fullName = fullName;
		this.dateOfBirth = dateOfBirth;
		this.goals = goals;
		this.emailId = emailId;
	}



	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getHighestQualification() {
		return highestQualification;
	}
	public void setHighestQualification(String highestQualification) {
		this.highestQualification = highestQualification;
	}
	public int getMarksObtained() {
		return marksObtained;
	}
	public void setMarksObtained(int marksObtained) {
		this.marksObtained = marksObtained;
	}
	public String getGoals() {
		return goals;
	}
	public void setGoals(String goals) {
		this.goals = goals;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getScheduledProgramId() {
		return scheduledProgramId;
	}
	public void setScheduledProgramId(String scheduledProgramId) {
		this.scheduledProgramId = scheduledProgramId;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public LocalDate getDateOfInterview() {
		return dateOfInterview;
	}
	public void setDateOfInterview(LocalDate dateOfInterview) {
		this.dateOfInterview = dateOfInterview;
	}
	@Override
	public String toString() {
		return "ApplicationBean [applicationId=" + applicationId
				+ ", fullName=" + fullName + ", dateOfBirth=" + dateOfBirth
				+ ", highestQualification=" + highestQualification
				+ ", marksObtained=" + marksObtained + ", goals=" + goals
				+ ", emailId=" + emailId + ", scheduledProgramId="
				+ scheduledProgramId + ", status=" + status
				+ ", dateOfInterview=" + dateOfInterview + "]";
	}
	
	
	
}
