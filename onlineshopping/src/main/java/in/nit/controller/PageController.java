package in.nit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping(value= {"/","home","index"})
	public String showPage(Model model) {
		model.addAttribute("title", "Home");
		model.addAttribute("userClickHome", true);
		return "page";
	}
	
	@RequestMapping(value= "/about")
	public String aboutPage(Model model) {
		model.addAttribute("title", "About Us");
		model.addAttribute("userClickAbout", true);
		return "page";
	}
	
	@RequestMapping(value= "/contact")
	public String contactPage(Model model) {
		model.addAttribute("title", "Contact Us");
		model.addAttribute("userClickContact", true);
		return "page";
	}
}
