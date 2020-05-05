package com.env.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value="view")
public class ViewController {

	@RequestMapping("")
	public String view(String url) {
		return url;
	}
}
