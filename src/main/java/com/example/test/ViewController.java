package com.example.test;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Controller
public class ViewController implements WebMvcConfigurer{
	
	
	 @Override
	    public void addViewControllers(ViewControllerRegistry registry) {
	        registry.addViewController("/usersList").setViewName("ulsersList");
	    }

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/form")
	public String userForm(Model model) {
		model.addAttribute("user", new User());
		return "form";
	}

	@PostMapping("/form")
	public String userSubmit(@Valid User user, BindingResult bindingResult ) {
		 if (bindingResult.hasErrors()) {
	            return "form";
	        }
		userRepository.save(user);
		return "redirect:/UsersList";
	}

	@GetMapping("/UsersList")
	public String userlist(Model model) {
		Iterable<User> allUsers = userRepository.findAll();
		model.addAttribute("allUsers", allUsers);
		return "usersList";
	}

	@GetMapping("/user/{id}")
	public String user(@PathVariable(value = "id") Long id, Model model) {

		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "result";
	}

	@GetMapping("/updateUser/{id}")
	public String updateuser(@PathVariable(value = "id") Long id, Model model) {

		User user = userRepository.findById(id).get();
		model.addAttribute("user", user);
		return "formUpdate";

	}
	
	@PostMapping("/formUpdate")
	public String userSubmited(@ModelAttribute User user) {
		userRepository.save(user);
		return "redirect:/UsersList";
		
	}

	@GetMapping("/delete/{id}")
	public String deleteUser(@PathVariable(value = "id") Long id) {

		userRepository.deleteById(id);

		return "redirect:/UsersList";

	}

}