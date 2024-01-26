package com.example.controller;

import java.util.HashMap;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.frontStage.BigDiv;
import com.example.interf.FrontStageStyleService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpSession;

@RestController
public class FrontStageStyle {
	@Autowired 
	FrontStageStyleService fss;
	
	@PostMapping("/FrontStageSet/update")
	public String setStyle(HttpSession session,@RequestBody  LinkedList<BigDiv> li) {
		
//		int check= fss.updateStyle((String)session.getAttribute("email"),(String)session.getAttribute("nav"), li);
		int check= fss.updateStyle("test",(String)session.getAttribute("nav"), li);
		  ObjectMapper objectMapper = new ObjectMapper();
          String jsonMessage = null;
		try {
			
			jsonMessage = objectMapper.writeValueAsString((check==0)?"ok":"error");
		} catch (JsonProcessingException e) {
			
			e.printStackTrace();
		}
          return jsonMessage;
		
	}
	
	@PostMapping("/FrontStageSet/update/nav")
	public String setNav(HttpSession session, @RequestBody String nav) {
		System.out.println(nav);
		session.setAttribute("nav", nav);
		return"ok";
		
	}
}