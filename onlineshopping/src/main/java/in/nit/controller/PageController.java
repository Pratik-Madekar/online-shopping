package in.nit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping(value= {"/","home","index"})
	public String showPage(Model model) {
		model.addAttribute("greeting", "Welcome To E-Commerce Site");
		return "page";
	}
}
