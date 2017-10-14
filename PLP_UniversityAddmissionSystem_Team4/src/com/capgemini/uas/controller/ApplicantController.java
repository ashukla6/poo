package com.capgemini.uas.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.capgemini.uas.dto.ApplicationBean;
import com.capgemini.uas.dto.ProgramScheduledBean;
import com.capgemini.uas.exception.UniversityException;
import com.capgemini.uas.service.IApplicantService;

@Controller
public class ApplicantController {
	
		@Resource
		IApplicantService service;
		
		
		@RequestMapping("/applicantSheduledProgramlist.do")
		public ModelAndView applicantSheduledProgramlist(){
			ModelAndView mAndV = new ModelAndView();
			try {
				List<ProgramScheduledBean> psb = service.showProgramScheduled();
				mAndV.addObject("scheduledProgramList",psb);
			} catch (UniversityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			mAndV.setViewName("Welcome");
			return mAndV;
			
		}
		
		@RequestMapping("/getRegisterationPage.do")
		public ModelAndView getRegisterationPage(){
			ModelAndView mAndV = new ModelAndView();
			List<ProgramScheduledBean> scheduleList = null;
			try {
				scheduleList = service.showProgramScheduled();
			} catch (UniversityException e) {
				
				e.printStackTrace();
			}
			mAndV.setViewName("RegisterPage");
			ApplicationBean applicant = new ApplicationBean();  //command object
			mAndV.addObject("applicant", applicant);
			mAndV.addObject("scheduleIdList", scheduleList);
			return mAndV;
		}


		
@RequestMapping(value="/applicant_register.do",method=RequestMethod.POST)
			public ModelAndView register(@ModelAttribute("applicant") ApplicationBean applicant,BindingResult result){
				ModelAndView mAndV = new ModelAndView();
			if(result.hasErrors())
						{
							mAndV.setViewName("RegisterPage");
						}
					else
						{
							try {
								service.addDetail(applicant);
							} catch (UniversityException e) {
								
								e.printStackTrace();
							}
							mAndV.addObject("Applicant",applicant);
							mAndV.setViewName("Success");
						}
				return mAndV;
			}
		
		
		
}
