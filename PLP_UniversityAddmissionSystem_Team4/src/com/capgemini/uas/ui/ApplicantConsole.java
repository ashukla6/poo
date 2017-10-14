package com.capgemini.uas.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.service.ApplicantServiceImpl;
import com.capgemini.uas.service.IApplicantService;
import com.capgemini.uas.service.IMacService;
import com.capgemini.uas.service.MacServiceImpl;
public class ApplicantConsole {
	
	private IApplicantService appService;
	private Scanner sc;
	private DateTimeFormatter format;
	
	public void start() throws UniversityException {
		boolean flag=true,flag1=true,flag2=true,flag3=true,flag4=true;
		String fullName,dob,highestQualification;
		LocalDate dateOfBirth = null,dateOfInterview=null;
		int marksObtained=0,choice,applicationId = 0;
			
			appService = new ApplicantServiceImpl();
			sc = new Scanner(System.in);
		do											//do-while loop
			{
				System.out.println("Choose an operation");
				System.out.println("1. Program Scheduled");
				System.out.println("2. Apply Online");
				System.out.println("3. Application Status");
				System.out.println("4. Exit");
				System.out.println("******************************");
				System.out.print("\nPlease Enter a Choice : ");
				choice = sc.nextInt();
				System.out.println("\n******************************");
		List<ProgramScheduledBean> programScheduled= null;
		switch (choice) {
		
		case 1:
				programScheduled = appService.showProgramScheduled();
		    for (ProgramScheduledBean pBean : programScheduled) {
				System.out.println(pBean);
			}
			break;
			
		case 2:
			fullName=sc.nextLine();
		do
		{
			System.out.println("Enter Full Name as per 10th Crtificate"); 
			fullName=sc.nextLine();
			flag = ApplicantServiceImpl.validateFullName(fullName);
			if(flag==false)
				System.err.println("Name should be entered less than 20 character and first letter is capital");	
		}while(flag==false);
		do
		{
		System.out.println("Enter Date of Birth(e.g. 02-Feb-1995)");
		dob = sc.next();
		flag1=ApplicantServiceImpl.validateDateOfBirth(dob);
		if(flag1==false)
			System.out.print("");
		}while(flag1==false);
		
		format = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
		dateOfBirth = LocalDate.parse(dob,format);
		//System.out.println(dateOfBirth);
		//Customer Phone Number
		sc.nextLine();
		do
		{
			
			System.out.println("Enter your Highest Quailification : ");
			highestQualification = sc.nextLine();
			flag2=ApplicantServiceImpl.validateHighestQualification(highestQualification);
			if(flag2==false)
				System.err.println("Length should be less than 10");
		}while(flag2==false);
		////////////////////////////////////////////////////////////
			System.out.print("Enter your marks ");
			do
			{	
				if (sc.hasNextInt()) {
					marksObtained = sc.nextInt();
					flag3=true;
				} else {
					System.err.print("Please enter a Marks in number ");
					sc.next();
					flag3=false;
				}
			}while(flag3==false);
		
			String goals;
			sc.nextLine();
			System.out.print("Define your goals ");
			do {
				goals = sc.nextLine();
				flag4=ApplicantServiceImpl.validateGoal(goals);
				if(flag4==false)
					System.err.print("Define Goal in Less Than 20 Characters ");
			} while (flag4==false);
		
			
			System.out.print("Enter your Email Id ");
			String emailId = sc.next();
			while(!ApplicantServiceImpl.validateEmail(emailId)){
				System.err.print("Enter your Correct Email Id ");
				emailId = sc.next();
			}
			
			System.out.println("Please see below programs");	
			programScheduled = appService.showProgramScheduled();
			
			if(programScheduled.isEmpty()){
				System.err.println("No Program Available");
				continue;
			}
		    for (ProgramScheduledBean pBean : programScheduled) {
				System.out.println(pBean);
			}
		    String scheduledProgramId=null;
		    IMacService macService = new MacServiceImpl();
		    System.out.println("Enter Program Scheduled Id from the above table: ");
		    flag4=false;
	    do
		{	
	    	//System.out.println("here ");
			scheduledProgramId = sc.next();	
			//System.out.println("adfsdf"+scheduledProgramId);
			flag = MacServiceImpl.validateScheduledId(scheduledProgramId);
			if(!flag){
				System.err.println("Scheduled ID should be number and less than 5 digit");
				flag=true;
			}else{
				List<String> cList = macService.getscheduleId();
				if(cList.contains(scheduledProgramId)){						
					flag=false;
					flag4=true;
				}else{
					System.err.println("No such Id found");
					System.out.println("To enter other Id press 1 otherwise any other key");
					if("1".equals(sc.next())){
						flag=true;
						System.out.println("Enter Available Program Scheduled Id from the above table: ");
					}else	
						flag=false;
				}
			}
		}while(flag);
	    if(!flag4)
	    	break;
	    ApplicationBean applicantBean = new ApplicationBean();
	    applicantBean.setFullName(fullName);
	    applicantBean.setDateOfBirth(dateOfBirth);
	    applicantBean.setHighestQualification(highestQualification);
	    applicantBean.setMarksObtained(marksObtained);
	    applicantBean.setGoals(goals);
	    applicantBean.setEmailId(emailId);
	    applicantBean.setScheduledProgramId(scheduledProgramId);
		dateOfInterview = LocalDate.parse("2012-12-12");
	    applicantBean.setDateOfInterview(dateOfInterview);
		applicationId  = appService.addDetail(applicantBean);
	    System.out.println("Successfully applied. Your Application Id is : " + applicationId);
	   break;
	   
		case 3:
			do {
				System.out.println("Enter your Application Id ");
				try {
					applicationId = sc.nextInt();
					applicantBean = appService.showStatus(applicationId);
					if (applicantBean!=null) {
						flag4=true;
						LocalDate checkDoi = LocalDate.parse("2032-12-12");
						if (applicantBean.getDateOfInterview().equals(checkDoi)) {
							System.out.println("Status : "
									+ applicantBean.getStatus());
						} else {
							System.out.println("Status : "
									+ applicantBean.getStatus());
							System.out.println("Date of Interview : "
									+ applicantBean.getDateOfInterview());
						}
					}else{
						flag4=true;
						System.err.println("No data Found");
					}
				} catch (Exception e) {
					flag4=false;
					System.err.println("Only Interger allowed and length should be less than 10");
					sc.nextLine();
				}
			} while (flag4==false);
				break;

			default:
				if(choice!=4) {
					if(choice<1 || choice>4) {
						System.err.println("No such choice ");
					}
					System.out.println("Do You Want to continue : \nPress 1  to continue\nAny Other key to exit ");
					String decision = sc.next();
					//System.out.println("Decison: "+decision);
					if(!("1".equals(decision))) {
						System.out.println("Successfully Logged Out.\\nThanks For Visiting..");
						choice=4;
					}
				}else
					System.out.println("Successfully Logged Out.\nThanks For Visiting..");
			
	}
			}while(choice!=4);
}
}
