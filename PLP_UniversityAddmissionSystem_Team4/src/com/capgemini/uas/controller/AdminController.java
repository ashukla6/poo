package com.capgemini.uas.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import com.capgemini.uas.service.IAdminService;

@Controller
public class AdminController {
	@Resource
	IAdminService service;
}
