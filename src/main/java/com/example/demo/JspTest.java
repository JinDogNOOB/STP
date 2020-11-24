package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class JspTest {


	@RequestMapping("/test") // if you write IP:8080/test  then you are going to here
	private String jspTest() {
		return "test"; // jsp file name that you created
	}
}
