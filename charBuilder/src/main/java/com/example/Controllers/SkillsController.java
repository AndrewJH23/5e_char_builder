package com.example.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repositorys.CharRepo;
import com.example.demo.CharacterSheet;
import com.example.demo.*;

@RestController
@RequestMapping("api/v1/skills/{id}")
public class SkillsController {
	@Autowired
	private CharRepo repo;
	
	@GetMapping("/all")
	public Skills getAllStats(@PathVariable int id) {
		Optional<CharacterSheet> person = repo.findById(id);
		if(!person.isPresent()) {
			return null;
		}
		return person.get().getSkills();
	}
	
	@GetMapping("/") //need skilName
	public int getSkill(@PathVariable int id, @RequestParam String skill) {
		Optional<CharacterSheet> person = repo.findById(id);
		if(!person.isPresent()) {
			return -99;
		}
		
		switch(skill) {
		case "Acrobatics":  return person.get().getSkills().getAcrobatics();
		case "AnimalHandling": return person.get().getSkills().getAnimalHandling();
		case "Arcana": return person.get().getSkills().getArcana();
		case "Athletics": return person.get().getSkills().getAthletics();
		case "Deception": return person.get().getSkills().getDeception();
		case "History": return person.get().getSkills().getHistory();
		case "Insight": return person.get().getSkills().getInsight();
		case "Intimidation": return person.get().getSkills().getIntimidation();
		case "Investigation": return person.get().getSkills().getInvestigation();
		case "Medicine": return person.get().getSkills().getMedicine();
		case "Nature": return person.get().getSkills().getNature();
		case "Perception": return person.get().getSkills().getPerception();
		case "Performance": return person.get().getSkills().getPerformance();
		case "Persuasion": return person.get().getSkills().getPersuasion();
		case "Religion": return person.get().getSkills().getReligion();
		case "SleightOfHand": return person.get().getSkills().getSleightOfHand();
		case "Stealth": return person.get().getSkills().getStealth();
		case "Survival": return person.get().getSkills().getSurvival();		
		}
		return -98;
	}
	//commands
}