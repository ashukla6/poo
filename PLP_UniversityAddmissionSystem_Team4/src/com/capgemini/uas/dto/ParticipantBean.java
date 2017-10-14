package com.capgemini.uas.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PARTICIPANTS")
public class ParticipantBean {
	
	@Id
	@Column(name="ROLL_NO")
	private String rollNo;
	
	@Column(name="APPLICATION_ID")
	private int applicationId;
	
	@Column(name="EMAIL_ID")
	private String emailId;
	
	@Column(name="SCHEDULED_PROGRAM_ID")
	private String scheduledProgramId;
	
	public ParticipantBean() {
	}
	public ParticipantBean(String rollNo, int applicationId, String emailId,
			String scheduledProgramId) {
		super();
		this.rollNo = rollNo;
		this.applicationId = applicationId;
		this.emailId = emailId;
		this.scheduledProgramId = scheduledProgramId;
	}
	public String getRollNo() {
		return rollNo;
	}
	public void setRollNo(String rollNo) {
		this.rollNo = rollNo;
	}
	public int getApplicationId() {
		return applicationId;
	}
	public void setApplicationId(int applicationId) {
		this.applicationId = applicationId;
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
	@Override
	public String toString() {
		return "ParticipantBean [rollNo=" + rollNo + ", applicationId="
				+ applicationId + ", emailId=" + emailId
				+ ", scheduledProgramId=" + scheduledProgramId + "]";
	}
	
	

}
