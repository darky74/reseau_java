package com.example.test;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.test.User;
import com.example.test.UserRepository;


@RestController
public class HelloController {
	
	@Autowired
	private UserRepository userRepository;
	
	//Requetes GET
	
	@GetMapping(path="/add")
	public @ResponseBody String addNewUser (@RequestParam String first, @RequestParam String last, @RequestParam String date) {
		User user = new User();
		user.setFirstName(first);
		user.setLastName(last);
		user.setDateNaisance(date);
		userRepository.save(user);
		return "Saved";
	}
	
	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	
	
	
	@GetMapping ("/update/{id}")
	public @ResponseBody String updateUser(@PathVariable(value= "id") Long id, @RequestParam String first, @RequestParam String last, @RequestParam String date) {
		
		
		
		Optional<User> optionalUser =  userRepository.findById(id);
		
		if(optionalUser.isPresent()) {
		
		
		User user = optionalUser.get();
		user.setFirstName(first);
		user.setLastName(last);
		user.setDateNaisance(date);
		userRepository.save(user);
		System.out.println("updated");
		
		}
		else {
			System.out.println("Cet utilisateur n'existe pas");
		}
		return null;
		

		
		
		
	}
	
	@GetMapping(path="/del")
	public @ResponseBody String deleteUser (Long id) {
		userRepository.deleteById(id);
		return "Delete";
	}
	
	
	//Requetes POST
	
	@PostMapping(value = "/Users")
	public String addnewUser(@RequestBody User user) {
		userRepository.save(user);
	
		return "saved";
		
	}
	
	@PostMapping(value="Users/{id}")
	public Optional<User> ShowUser(@PathVariable Long id) {
		return userRepository.findById(id);
		
	}
	
	@PostMapping(value="delete/{id}")
	public String delUser(@PathVariable Long id) {
		userRepository.deleteById(id);
		return "deleted";
		
		
	}
	
	

}
