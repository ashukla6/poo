package com.capgemini.uas.service;

import java.time.LocalDate;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.capgemini.uas.dao.ApplicantDaoImpl;
import com.capgemini.uas.dao.IApplicantDao;
import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;

@Service
@Transactional
public class ApplicantServiceImpl implements IApplicantService {
	
	@Resource
	private IApplicantDao appDao;
	
	private static String patName = "[A-Z][A-Za-z ]{2,19}";
	private static String patDob = "[0-3][0-9]-[a-zA-Z]{3}-[0-9]{4}";
	private static String patQual = "[0-9A-Za-z .]{1,10}";
	private static String patGoal = "[0-9A-Za-z]{1,20}";
	private static String patEmail = "[a-zA-Z0-9]{4,15}[@][a-zA-Z]{1,10}[.](com|net|gov|in|org)";
	
	
	public static boolean validateFullName(String fullName){
		return fullName.matches(patName);
	}

	public static boolean isAgeEligilble(LocalDate date){
		//System.out.println("yahan pahuncgha kya");
		int dDob = date.getDayOfMonth();
		//int mDob = date.getMonthValue();
		int yDob = date.getYear();
		
//		int dNow =LocalDate.now().getDayOfMonth();
//		int mNow = LocalDate.now().getMonthValue();
		int yNow = LocalDate.now().getYear();
		//System.out.println("yDob "+yDob+"ynow= "+ yNow);
		if((yNow-yDob)>=17 && (yNow-yDob)<25){
			//System.out.println("Returning true");
			return true;
			
		}else
			//System.out.println("Returning False");
			return false;
	}
	public static boolean validateDateOfBirth(String dateOfBirth){
		LocalDate dob;
		if(dateOfBirth.matches(patDob)){
			try {
				DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
				dob = LocalDate.parse(dateOfBirth,format);
				if(!isAgeEligilble(dob))
					System.err.println("Age should be 17 to 25 years");
				return isAgeEligilble(dob);
			} catch (Exception e) {
				System.err.println("Date of Birth should be entered in proper format(e.g. 02-Feb-1995)");
				return false;
			}
			
		}else{
			System.err.println("Date of Birth should be entered in proper format(e.g. 02-Feb-1995)");
			return false;
		}
	}
	
	public static boolean validateHighestQualification(String highestQualification){
		return highestQualification.matches(patQual);
	}
	
	public static boolean validateGoal(String goal){
		return goal.matches(patGoal);
	}
	public static boolean validateEmail(String eMail){
		return eMail.matches(patEmail);
	}
	@Override
	public List<ProgramScheduledBean> showProgramScheduled()
			throws UniversityException {
		// TODO Auto-generated method stub
		return appDao.showProgramScheduled();
	}

	@Override
	public int addDetail(ApplicationBean ab) throws UniversityException {
		// TODO Auto-generated method stub
		return appDao.addDetail(ab);
	}

	@Override
	public ApplicationBean showStatus(int applicationId)
			throws UniversityException {
		// TODO Auto-generated method stub
		return appDao.showStatus(applicationId);
	}

}
