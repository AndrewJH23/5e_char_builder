package com.example.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repositorys.CharRepo;
import com.example.Repositorys.ResourceRepo;
import com.example.demo.CharacterSheet;
import com.example.demo.Resource;

@RestController
@RequestMapping("api/v1/resource")
public class ResourceController {
	
	@Autowired
	private ResourceRepo repo;
	
	@Autowired
	private CharRepo charRepo;
	
	//query
	@GetMapping("/all")
	public List<Resource> fetchAllResources(){ 
		return repo.findAll();
	}
	
	@GetMapping("/all/{id}")
	public List<Resource> fetchResources(@PathVariable int id){
		Optional<CharacterSheet> ch = charRepo.findById(id);
		if(!ch.isPresent()) {
			return new ArrayList<Resource>();
		}
		return ch.get().getResources();
	}
	
	//command
	@PostMapping("/add")
	public Resource addResource(@RequestBody Resource resource) {
		return repo.save(resource);
	}
	
}
