package com.capgemini.uas.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="PROGRAM_OFFERED")
public class ProgramOfferedBean {
	
	@Id
	@Column(name="PROGRAM_NAME")
	private String programName;
	
	@Column(name="DESCRIPTION")
	private String description;
	
	@Column(name="APPLICANT_ELIGIBILITY")
	private String applicantEligiblity;
	
	@Column(name="DURATION")
	private int duration;
	
	@Column(name="DEGREE_CERTIFICATE_OFFERED")
	private String degreeCertificate;
	
	public ProgramOfferedBean() {
	}
	
	public ProgramOfferedBean(String programName, String description,
			String applicantEligiblity, int duration, String degreeCertificate) {
		super();
		this.programName = programName;
		this.description = description;
		this.applicantEligiblity = applicantEligiblity;
		this.duration = duration;
		this.degreeCertificate = degreeCertificate;
	}

	public String getProgramName() {
		return programName;
	}
	public void setProgramName(String programName) {
		this.programName = programName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getApplicantEligiblity() {
		return applicantEligiblity;
	}
	public void setApplicantEligiblity(String applicantEligiblity) {
		this.applicantEligiblity = applicantEligiblity;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getDegreeCertificate() {
		return degreeCertificate;
	}
	public void setDegreeCertificate(String degreeCertificate) {
		this.degreeCertificate = degreeCertificate;
	}
	@Override
	public String toString() {
		return "ProgramOffered [programName=" + programName + ", description="
				+ description + ", applicantEligiblity=" + applicantEligiblity
				+ ", duration=" + duration + ", degreeCertificate="
				+ degreeCertificate + "]";
	}
	
	
}
