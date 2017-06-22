package com.tfg.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class InicioController {
	@Autowired
	private ApplicationContext applicationContext;	
	
	@RequestMapping(value = {"/","/inicio"}, method = RequestMethod.GET)
	public String inicio(){
		return "inicio";
	}
	@RequestMapping(value = {"/favicon.ico"}, method = RequestMethod.GET)
	public String getInicioDesdeFavicon(){
		return "inicio";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logout(){
		return "/";
	}
}
