package com.example.Controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repositorys.CharRepo;
import com.example.demo.CharacterSheet;
import com.example.demo.Stats;

@RestController
@RequestMapping("api/v1/stats/{id}")
public class StatsController {
	@Autowired
	private CharRepo repo;
	
	@GetMapping("/all")
	public Stats getAllStats(@PathVariable int id) {
		Optional<CharacterSheet> person = repo.findById(id);
		if(!person.isPresent()) {
			return new Stats();
		}
		return person.get().getStats();
	}
	
	@GetMapping("/") //need statName
	public int getStat(@PathVariable int id,@RequestParam String stat) {
		Optional<CharacterSheet> person = repo.findById(id);
		if(!person.isPresent()) {
			return -101;
		}
		switch(stat) {
		case "Charisma": return person.get().getStats().getCharisma();
		case "Constitution": return person.get().getStats().getConstitution();
		case "Dexterity": return person.get().getStats().getDexterity();
		case "Intelligence": return person.get().getStats().getIntelligence();
		case "Strength": return person.get().getStats().getStrength();
		case "Wisdom": return person.get().getStats().getWisdom();
		}
		return -100;	
	}
	//commands
	@PutMapping("/update") //need statName and value
	public Stats updateStats(@PathVariable int id, @RequestParam String stat, @RequestParam int value){
		Optional<CharacterSheet> person = repo.findById(id);
		if(!person.isPresent()) {
			return new Stats();
		}
		switch(stat) {
		case "Charisma": person.get().getStats().setCharisma(value);
		case "Constitution": person.get().getStats().setConstitution(value);
		case "Dexterity": person.get().getStats().setDexterity(value);
		case "Intelligence": person.get().getStats().setIntelligence(value);
		case "Strength": person.get().getStats().setStrength(value);
		case "Wisdom": person.get().getStats().setWisdom(value);
		}
		return person.get().getStats();
	}
}