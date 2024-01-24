package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Member;
import com.example.service.MCFOservice;

@RestController
public class MemberControllerForOrder {
	@Autowired
	MCFOservice memberservice;
	
	@PostMapping("/getMemberInfo")
	public Member getMemberInfo(@RequestBody String memberId) {
		return memberservice.getMemberInfo(memberId);
	}
}
