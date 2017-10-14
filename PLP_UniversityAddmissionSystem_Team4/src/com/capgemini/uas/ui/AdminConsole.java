package com.capgemini.uas.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ProgramOfferedBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.service.AdminServiceImpl;
import com.capgemini.uas.service.IAdminService;
import com.capgemini.uas.service.IMacService;
import com.capgemini.uas.service.MacServiceImpl;

public class AdminConsole {
	String userName;
	IAdminService service;
	IMacService macService=new MacServiceImpl();
	public AdminConsole(String userName) {
		super();
		this.userName = userName;
	}
	
	@SuppressWarnings("resource")
	public void start() throws UniversityException {
			int choice=-1;
			String scheduledProgramId;
			service = new AdminServiceImpl();
			Scanner sc;
			sc = new Scanner(System.in);
			System.out.println("Welcome "+userName);
			System.out.println("Choose an operation");
			
			do											//do-while loop
			{				
				System.out.println("1. View particular program scheduled Details");
				System.out.println("2. View list of applicants of particular program Confirmed after interview");
				System.out.println("3. Add program Offered");
				System.out.println("4. Delete Program Offered");
				System.out.println("5. Add Programs Scheduled");
				System.out.println("6. Delete Programs Scheduled");
				System.out.println("7. View list of applicants of particular program scheduled");
				System.out.println("8.Generate Confirmed Students List");
				System.out.println("9. Exit");
				System.out.println("******************************");
				System.out.print("\nPlease Enter a Choice : ");
				
				if (sc.hasNextInt()) {
					choice = sc.nextInt();
				} else {
					System.out.println("Please enter a number as in menu");
					sc.next();
					continue;
				}
				
				System.out.println("\n******************************");

				switch (choice) {
				case 1:
					boolean flag2=false;
					
					System.out.println("All Scheduled Program List");
					List<ProgramScheduledBean> list1 = service.getAllDetails();
									 for (ProgramScheduledBean sch : list1) {
						System.out.println(sch);
					}										 
				
				
				break;
					
				case 2:{
					boolean flag;
					do{
						System.out.println("Enter Program Scheduled Id");
						String pScheduledId = sc.next();									
						flag = MacServiceImpl.validateScheduledId(pScheduledId);
						if(flag==false){
							System.err.println("Scheduled ID should be number and less than 5 digit");
						}else{
							List<String> cList = macService.getscheduleId();
							if(cList.contains(pScheduledId)){						
								List<ApplicationBean> confirmedList=service.getApplicantsByStatusByScheduledProgramId("confirmed", pScheduledId);
								if(confirmedList.isEmpty()){
									System.err.println("No confirmed student!!");
								}else{
									System.out.println("Confirmed Students List");
									for (ApplicationBean applicants : confirmedList) 
										System.out.println(applicants.getApplicationId()+": "+applicants.getFullName());		
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
					}
					
				case 3:
					boolean flag3=true;
					System.out.println("Enter Program offered details to add: ");
					ProgramOfferedBean pOffered=new ProgramOfferedBean() ;
					System.out.println("Enter Program Name: ");
					sc.nextLine();
					String pName;
					do{
						pName = sc.nextLine();
						if(pName.matches("[a-zA-Z0-9 .]{1,15}")) {
							flag3=false;
						}else
							System.err.println("Enter Program Name wihtout special characters: ");
					}while(flag3);
					pOffered.setProgramName(pName);
					System.out.print("Enter Program Description: ");
					//sc.nextLine();
					String pDesc;
					do{	
						  pDesc=sc.nextLine();
						  flag3=service.validateProgramDescription(pDesc);
						  if(!flag3)
							  System.err.println("Enter Description in correct format ");
					}while(!flag3);
					pOffered.setDescription(pDesc);
					System.out.println("Enter Program Eligibilty");
					//String eligible = sc.next();
					flag3=true;
					do{
						String eligible = sc.nextLine();
					if(AdminServiceImpl.validateEligibility(eligible)){
						pOffered.setApplicantEligiblity(eligible);
						flag3=false;
					}else{
						System.err.println("special characters are not allowed (length<=10) ");
					}
					}while(flag3);
					
					System.out.println("Enter Program duration");
					String description = sc.next();
					flag3=true;
					do {
					try {			
						int desc=Integer.parseInt(description);
						pOffered.setDuration(desc);
						flag3=false;
					} catch (NumberFormatException e) {
						System.err.println("Enter duration in number");
						description = sc.next();
					}
					}while(flag3);
					System.out.println("Enter Program degree Certificate offered");
					pOffered.setDegreeCertificate(sc.next());
					sc.nextLine();
					service.addProgramOffered(pOffered);
					/*if(service.addProgramOffered(pOffered))
						System.out.println("Program Succesfully Added");
					else
						System.err.println("There was some problem while adding");*/
					break;
					
				case 4:
					flag3=true;
					do {
						System.out.println("Enter the program name Which you want to delete.");
						pName=sc.next();
						List<String> schlist3=service.getAllProgramName();
						
						if(schlist3.contains(pName)){
							flag3=false;
							boolean flag=service.deleteProgramOffered(pName);
		                    if(flag==true)                    
		                    	System.out.println("Delete Successful");                  
		                    else                   
		                    	System.err.println("Failed!!");										
						}else {
							System.err.println("Invalid Program name");
							System.out.println("Want to delete Some Other Program Press 1 else Any ohter key");
							String decision=sc.next();
							if("1".equals(decision))
								flag3=true;
							else
								flag3=false;
							
						}
					}while(flag3);
					break;
					
				case 5:
					boolean flag=true;
					System.out.println("Enter Program Scheduled details to add: ");
					ProgramScheduledBean pScheduled=new ProgramScheduledBean() ;
					do{
						List<String> idList=service.getAllScheduleId();
						System.out.println("Enter Schedule Program Id: ");
						String schId=sc.next();
						if(idList.contains(schId))
						{
							System.err.println("Entered Id Already Exists In the Schedule Table");
							flag=true;
						}
						else
						{
							pScheduled.setScheduledProgramId(schId);
							System.out.println("Enter schedule Program name as Available in Offered program Table(shown below) ");
							List<String> oFPNameList=service.getAllProgramName();
							
							for (String id : oFPNameList) 
								System.out.print(id+" ");
							
							String schProgName= sc.next();
							if(oFPNameList.contains(schProgName)){							
								pScheduled.setProgramName(schProgName);
								flag=false;
								String location;
								do
								{
									System.out.println("Enter the New Location");
									location=sc.next();
									flag=service.validateLocation(location);
								}while(flag==false);
								pScheduled.setLocation(location);
								System.out.println("Enter the Start Date");
								String date;
								flag=false;
								Date date2;
								do{
									//System.out.println("Enter the Correct Start Date");
									date=sc.next();
								if(!AdminServiceImpl.validateDate(date)){
									flag=true;
								}else{
									DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
									LocalDate date1 = LocalDate.parse(date,formatter);
									date2=java.sql.Date.valueOf(date1);
									Date today = java.sql.Date.valueOf(LocalDate.now());
									if(today.after(date2)){
										flag=true;
										System.err.println("Enter date after current date");
									}else{	
										pScheduled.setStartDate(date2);
										flag=false;
									}
								}
								}while(flag);
								
					            System.out.println("Enter the End Date");
								flag=false;
								do{
									date=sc.next();
								if(!AdminServiceImpl.validateDate(date)){
									flag=true;
								}else{
									DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd");
									LocalDate date1 = LocalDate.parse(date,formatter);
									Date date3=java.sql.Date.valueOf(date1);
									Date startDate = pScheduled.getStartDate();
									if(startDate.after(date3)){
										System.out.println("here ");
										flag=true;
										System.err.println("Enter date after current date");
									}									
									else{
									pScheduled.setEndDate(date3);
									flag=false;
									}
								}
								}while(flag);
								////////
					            
					            
					            
					            ////
					            int sessionPerWeek = 0;
					            System.out.println("Enter the number of Sessions per week");
								do
								{	
									if (sc.hasNextInt()) {
										sessionPerWeek = sc.nextInt();
										flag3=true;
									} else {
										System.err.print("Please enter Sessions per week in number ");
										sc.next();
										flag3=false;
									}
								}while(flag3==false);
								pScheduled.setSessionPerWeek(sessionPerWeek);
								///////
					            
								service.addProgramScheduled(pScheduled);
								
								System.out.println("The Program Added successfully with Scheduled id "+ schId);
							}
							else
							{
								System.err.println("Entred Program Not Found");
							}
						}
					}while(flag);
					break;
					
				case 6:
					System.out.println("The List of Schedules is As follows:");
					List<ProgramScheduledBean> schList=service.getAllDetails();
					for(ProgramScheduledBean list:schList)
					System.out.println(list);
					
					System.out.println("Enter the Schedule ID ");
					String schId=sc.next();
					boolean flag1=service.deleteProgramScheduled(schId);
					if(flag1==true)
					{
						System.out.println("Delete Succesful");
					}
					else
					{
						System.err.println("Requested Id Does not Exist ! Please try with Existing id !");
					}
					break;
				case 7:{
					do{
						System.out.println("Enter Program Scheduled Id");
						String pScheduledId = sc.next();									
						flag = MacServiceImpl.validateScheduledId(pScheduledId);
						if(flag==false){
							System.err.println("Scheduled ID should be number and less than 5 digit");
						}else{
							List<String> cList = macService.getscheduleId();
							if(cList.contains(pScheduledId)){						
								List<ApplicationBean> confirmedList=service.getApplicantsByStatusByScheduledProgramId("confirmed", pScheduledId);
								List<ApplicationBean> acceptedList=service.getApplicantsByStatusByScheduledProgramId("accepted", pScheduledId);
								List<ApplicationBean> rejectedList=service.getApplicantsByStatusByScheduledProgramId("rejected", pScheduledId);
								if(confirmedList.isEmpty()){
									System.err.println("No confirmed student!!");
								}else{
									System.out.println("Confirmed Students List");
									for (ApplicationBean applicants : confirmedList) 
										System.out.println(applicants.getApplicationId()+": "+applicants.getFullName());		
								}
								if(acceptedList.isEmpty()){
									System.err.println("No Accepted student!!");
								}else{
									System.out.println("Accepted Students List");
									for (ApplicationBean applicants : acceptedList) 
										System.out.println(applicants.getApplicationId()+": "+applicants.getFullName());		
								}
								if(rejectedList.isEmpty()){
									System.err.println("No Rejected student!!");
								}else{
									System.out.println("Rejected Students List");
									for (ApplicationBean applicants : rejectedList) 
										System.out.println(applicants.getApplicationId()+": "+applicants.getFullName());		
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
					}
				case 8:
					List<ApplicationBean> confirmList=service.getAllConfirmedApplicants();
					if(confirmList.isEmpty()){
						System.err.println("No confirmed student Found");
						continue;
					}
					if(service.generateParticipants(confirmList))
					{
						System.out.println("Participant Added");
					}
					else
					{
						System.err.println("Failed to add Participants");
					}
						
										
					break;
					default:
						if(choice!=9) {
							if(choice<1 || choice>8) {
								System.out.println("No such choice ");
							}
						System.out.println("Do You Want to continue : \nPress 1  to continue\nAny Other key to exit ");
						String decision = sc.next();
						if(!("1".equals(decision))) {
							System.out.println("Thanks For Visiting..");
							choice=9;
						}
						}else
							System.out.println("Thanks For Visiting..");
				}
				}while(choice!=9);
	}
}
