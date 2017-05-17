package com.tfg.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class InicioController {
	
	
	
	@RequestMapping(value = "/inicio", method = RequestMethod.GET)
	public String getInicio( Model model){
		model.addAttribute("","");
		return "/index.html";
	}
	
	@RequestMapping(value = "/error", method = RequestMethod.GET)
	public String getError(){
		return "/404";
	}

}
