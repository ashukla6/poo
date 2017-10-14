package com.capgemini.uas.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.capgemini.uas.dao.AdminDaoImpl;
import com.capgemini.uas.dao.IAdminDao;
import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ProgramOfferedBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;

@Service
@Transactional
public class AdminServiceImpl implements IAdminService {
	
	@Resource
	private IAdminDao dao;
	
	@Override
	public void addProgramOffered(ProgramOfferedBean pOffered)
			throws UniversityException {
		dao.addProgramOffered(pOffered);
	}

	@Override
	public boolean deleteProgramOffered(String programName)
			throws UniversityException {
		return dao.deleteProgramOffered(programName);
	}

	@Override
	public int addProgramScheduled(ProgramScheduledBean pScheduled)
			throws UniversityException {
		// TODO Auto-generated method stub
		return dao.addProgramScheduled(pScheduled);
	}

	@Override
	public boolean deleteProgramScheduled(String programId)
			throws UniversityException {
		return dao.deleteProgramScheduled(programId);
	}
	@Override
	public List<ProgramScheduledBean> getAllDetails()
			throws UniversityException {
		return dao.getAllDetails();
	}
	@Override
	public List<String> getAllScheduleId() throws UniversityException {
		return dao.getAllScheduleId();
	}
	@Override
	public List<String> getAllProgramName() throws UniversityException {
		return dao.getAllProgramName();
	}
	
	@Override
	public List<String> getAllScheduleNames() throws UniversityException {
		return dao.getAllScheduleNames();
	}
	
	@Override
	public List<ApplicationBean> getApplicantsByStatusByScheduledProgramId(String status, String pScheduledId)
			throws UniversityException {
		return dao.getApplicantsByStatusByScheduledProgramId(status, pScheduledId);
	}
	@Override
	public List<ApplicationBean> getApplicantsByScheduledProgramId(String pScheduledId) throws UniversityException {
		return dao.getApplicantsByScheduledProgramId(pScheduledId);
	}
	
	public boolean validateProgramDescription(String pDesc){	
		Pattern pt= Pattern.compile("[A-Za-z ]{3,15}");
		Matcher match=pt.matcher(pDesc);
		return  match.matches();
	}
	
	@Override
	public boolean generateParticipants(List<ApplicationBean> confApplicants)
			throws UniversityException {
		
		return dao.generateParticipants(confApplicants);
	}
	@Override
	public List<ApplicationBean> getAllConfirmedApplicants()
			throws UniversityException {
		return dao.getAllConfirmedApplicants();
	}
	
	
	public boolean validateLocation(String location)
	{
		Pattern pt= Pattern.compile("[A-Za-z ]{3,15}");
		Matcher match=pt.matcher(location);
		return match.matches();
	}
	private static String patEli = "[0-9a-zA-Z]{10}";
	public static boolean validateEligibility(String eligible){
		Pattern pt= Pattern.compile("[0-9a-zA-Z ]{1,10}");
		Matcher match=pt.matcher(eligible);
		return  match.matches();
	}
	
	private static String patDob = "[0-9]{4}-[0-1][0-9]-[0-3][0-9]";
	
	public static boolean validateDate(String dateOfBirth){
		LocalDate dob;
		if(dateOfBirth.matches(patDob)){
			try {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				dob = LocalDate.parse(dateOfBirth,format);
				return true;
			} catch (Exception e) {
				System.err.println("Enter Date in proper format(e.g.yyyy-MM-dd)");
				return false;
			}
			
		}else{
			System.err.println("Enter Date in proper format(e.g.yyyy-MM-dd)");
			return false;
		}
	}
	
}
