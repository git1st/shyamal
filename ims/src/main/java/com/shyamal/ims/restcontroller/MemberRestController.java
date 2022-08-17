package com.shyamal.ims.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shyamal.ims.model.Member;
import com.shyamal.ims.repository.MemberRepository;
import com.shyamal.ims.service.MemberService;

@RestController
@RequestMapping(value = "/rest/member")
public class MemberRestController {

	@Autowired
	//private MemberService memberService;
	private MemberRepository memberService;
	
	@GetMapping(value = {"/", "/list"})
	public List<Member> all() {
		//return memberService.getAll();
		return memberService.findAll();
	}
	
}
