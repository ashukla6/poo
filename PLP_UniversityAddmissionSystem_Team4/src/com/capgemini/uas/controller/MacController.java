package com.capgemini.uas.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.capgemini.uas.service.IMacService;

@Controller
public class MacController {
	
	@Resource
	IMacService service;
}
