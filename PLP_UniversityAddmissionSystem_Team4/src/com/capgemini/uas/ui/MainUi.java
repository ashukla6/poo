package com.capgemini.uas.ui;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.capgemini.uas.dto.UsersBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.service.IUsersService;
import com.capgemini.uas.service.UsersServiceImpl;

public class MainUi {

	public static void main(String[] args) {
		
		int choice = -1;
		String loginId, password;
			Scanner sc = new Scanner(System.in);
			IUsersService userService = new UsersServiceImpl();
			System.out.println("\n*************University Addmission System *************");
			System.out.println("Choose an operation");
			
			do	
			{
				System.out.println("1. Enter as a Admin");
				System.out.println("2. Enter as a MAC");
				System.out.println("3. Enter as a Applicant");
				System.out.println("4. Exit");
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
				switch(choice)
				{
					case 1 :
							System.out.println("Enter User Name: ");
							loginId = sc.next();
							System.out.println("Enter Password: ");
							password = sc.next();
							UsersBean userBeanMain = new UsersBean(loginId, password,"admin");
							try {
								userService.checkUser(userBeanMain);
								AdminConsole admin=new AdminConsole(loginId);
								admin.start();
							} catch (UniversityException e) {
								System.err.println(e.getMessage());
							}
						break;
					case 2:  
						System.out.println("Enter User Name");
						loginId = sc.next();
						System.out.println("Enter Password");
						password = sc.next();
						UsersBean userBeanMain1 = new UsersBean(loginId, password,"mac");
						try {
							userService.checkUser(userBeanMain1);
							MacConsole mc = new MacConsole(loginId);
							mc.start();
						} catch (UniversityException e) {
							System.err.println(e.getMessage());
						}
						break;
					case 3: { 
							System.out.println("Welcome Applicant to University Addmission System");
							ApplicantConsole ac = new ApplicantConsole();
							try {
								ac.start();
							} catch (UniversityException e) {
								System.out.println(e.getMessage());
							}
						break;
					}
					case 4: {
						System.out.println("Thanks for Visiting!!!!");
						break;
					}
					} 
				
			}while(choice!=4);
			sc.close();
	}
}
