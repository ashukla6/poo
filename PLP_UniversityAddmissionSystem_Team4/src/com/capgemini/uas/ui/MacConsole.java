package com.capgemini.uas.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.service.AdminServiceImpl;
import com.capgemini.uas.service.IMacService;
import com.capgemini.uas.service.MacServiceImpl;

public class MacConsole {

	private String userName;
	private IMacService macService;
	private Scanner sc;
	
	public MacConsole(String userName) {
		this.userName = userName;
	}

	public void start() throws UniversityException {
		int choice=-1, applicationId;
		boolean flag;
		String scheduledProgramId,status;
			macService = new MacServiceImpl();
			sc = new Scanner(System.in);
			System.out.println("Welcome "+userName);
			System.out.println("Choose an operation");
			do											//do-while loop
			{				
				System.out.println("1. View list of applicants of particular program scheduled");
				System.out.println("2. View list of applicants of particular program scheduled after interview");
				System.out.println("3. Update status of applicant");
				System.out.println("4. Back to Home Page");
				System.out.println("******************************");
				System.out.print("\nPlease Enter a Choice: ");
				if (sc.hasNextInt()) {
					choice = sc.nextInt();
				} else {
					System.out.println("Please enter a number as in menu");
					sc.next();
					continue;
				}
				List<ApplicationBean> applicantDetails = new ArrayList<ApplicationBean>();
				switch (choice) {
				case 1:
					do{
						System.out.println("Enter Program Scheduled Id: ");
						scheduledProgramId = sc.next();										
						flag = MacServiceImpl.validateScheduledId(scheduledProgramId);
						if(flag==false){
							System.err.println("Scheduled ID should be number and less than 5 digit.");
						}else{
							List<String> cList = macService.getscheduleId();
							if(cList.contains(scheduledProgramId)){						
								applicantDetails = macService.getApplicantsOnSchduledId(scheduledProgramId);
								if(applicantDetails.isEmpty()){
									System.err.println("Sorry No Data Found!!");
								}else{
									for (ApplicationBean applicants : applicantDetails) 
										System.out.println(applicants);		
								}
							}else{
								System.err.println("No such Id found");
							}
						}
						System.out.println("To continue press 1 otherwise any other key");
						if("1".equals(sc.next())){
							flag=true;	
						}else	
							flag=false;
					}while(flag);
					break;
				case 2:
					do{
						System.out.println("Enter Program Scheduled Id");
						scheduledProgramId = sc.next();										
						flag = MacServiceImpl.validateScheduledId(scheduledProgramId);
						if(flag==false){
							System.err.println("Scheduled ID should be number and less than 5 digit");
						}else{
							List<String> cList = macService.getscheduleId();
							if(cList.contains(scheduledProgramId)){						
								applicantDetails = macService.getApplicantsAfterInterviewOnId(scheduledProgramId);
								if(applicantDetails.isEmpty()){
									System.err.println("Sorry No Data Found!!");
								}else{
									for (ApplicationBean applicants : applicantDetails) 
										System.out.println(applicants);		
								}
							}else{
								System.err.println("No such Id found");
							}
						}
						System.out.println("To continue press 1 otherwise any other key");
						if("1".equals(sc.next())){
							flag=true;	
						}else	
							flag=false;
					}while(flag);
					break;
					
				case 3:
					do{
						System.out.println("Enter Applicant Id");
						List<Integer> idList =macService.getApplicantId();
						System.out.println("Available Appicants Id "+idList);
						String applId = sc.next();
						flag = MacServiceImpl.validateScheduledId(applId);  //validateScheduledId is validating number 
						if(flag==false){
							System.err.println("Aplcation ID should be number and less than 5 digit");
						}else{
							applicationId=Integer.parseInt(applId);
							
							
							if(idList.contains(applicationId)){
								System.out.println("Enter Updated Status ");
								ArrayList<String> staustList=new ArrayList<>();
								staustList.add("accepted");
								staustList.add("rejected");
								staustList.add("confirmed");
								
								
								do{
									status = sc.next();
									if(staustList.contains(status)){
										flag=false;
									}else{
										System.err.println("Allowed status are : accepted, rejected, confirmed\nEnter Again: ");
									}
								}while(flag);
									status = macService.updateApplicantStatus(applicationId, status);
								
								System.out.println(status);
								if(status.equals("accepted")){
									System.out.println("Please Enter Date of Interview of Accepted Applicant(yyyy-MM-dd)");
									
									///
									String date;
									flag=false;
									Date date2;
									do{
										//System.out.println("Enter the Correct Start Date");
										date=sc.next();
									if(!AdminServiceImpl.validateDate(date)){
										flag=true;
									}
									DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
									LocalDate date1 = LocalDate.parse(date,formatter);/*else{
										DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
										LocalDate date1 = LocalDate.parse(date,formatter);
										date2=java.sql.Date.valueOf(date1);
										Date today = java.sql.Date.valueOf(LocalDate.now());
										if(today.after(date2)){
											flag=true;
											System.err.println("Enter date after current date");
										}else{	
											macService.updateApplicantDateOfInterview(applicationId, date1);
											flag=false;
										}*/
									//}
									}while(flag);
										
								} 
							}else{
								System.err.println("No such Application id");
							}
						}
						System.out.println("To continue press 1 otherwise any other key");
						if("1".equals(sc.next())){
							flag=true;	
						}else	
							flag=false;
					}while(flag);
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
								System.out.println("Successfully Logged Out.\\nThanks For Visiting MacServices..");
								choice=4;
							}
						}else
							System.out.println("Successfully Logged Out.\nThanks For Visiting MacServices..");
				}
				
}while(choice!=4);
		
	}
}
