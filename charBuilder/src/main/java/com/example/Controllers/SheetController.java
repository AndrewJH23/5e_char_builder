package com.example.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Repositorys.*;
import com.example.demo.CharacterSheet;
import com.example.demo.*;

@RestController
@RequestMapping("api/v1/charSheet")
public class SheetController {
	@Autowired
	private CharRepo repo;
	
	@Autowired
	private ResourceRepo rRepo;
	
	@Autowired
	private PlayerClassRepo pRepo;

	
	// Querys 
	@GetMapping("/")
	public List<CharacterSheet> getAllCharacters() {
		return repo.findAll();
	}

	@GetMapping("/{id}")
	public CharacterSheet getSheet(@PathVariable int id) {
		Optional<CharacterSheet> person = repo.findById(id);
		if (person.isPresent()) {
			return person.get();
		}
		return new CharacterSheet();
	}

	// commands
	@PostMapping("/add")
	public CharacterSheet addCharacter(@RequestBody CharacterSheet character) {
		return repo.save(character);
	}

	@PostMapping("/addEmpty")
	public CharacterSheet addFreshCharacter() {
		CharacterSheet c = new CharacterSheet();
		return repo.save(c);
	}
	
	@PostMapping("/addResource/{id}") //add resource name
	public String addResource(@PathVariable int id, @RequestParam String rName) {
		Optional<CharacterSheet> c = repo.findById(id);
		if (!c.isPresent()) {
			return "no character found";
		}
		Optional<Resource> r = rRepo.findByName(rName);
		if (!r.isPresent()) {
			return "no resource found"; 
		}
		List<Resource> ls = c.get().getResources();
		ls.add(r.get());
		c.get().setResources(ls);
		repo.save(c.get());
		return "added "+rName;
	}
	@PostMapping("/addPlayerClass/{id}") //add resource name
	public String addPlayerClass(@PathVariable int id, @RequestParam String pName) {
		Optional<CharacterSheet> c = repo.findById(id);
		if (!c.isPresent()) {
			return "no character found";
		}
		Optional<PlayerClass> p = pRepo.findByName(pName);
		if (!p.isPresent()) {
			return "no class found"; 
		}
		List<PlayerClass> ls = c.get().getPlayerClasses();
		ls.add(p.get());
		c.get().setPlayerClasses(ls);
		repo.save(c.get());
		return "added "+pName;
	}

	@PutMapping("/update/{id}")
	public CharacterSheet updateCharacter(@PathVariable int id, @RequestBody CharacterSheet characterSheet) {
		Optional<CharacterSheet> c = repo.findById(id);
		if (!c.isPresent()) {
			return new CharacterSheet();
		}
		c.get().setArmourClass(characterSheet.getArmourClass());
		c.get().setHealth(characterSheet.getHealth());
		c.get().setInitative(characterSheet.getInitative());
		c.get().setSpeed(characterSheet.getSpeed());
		c.get().setSkills(characterSheet.getSkills());
		c.get().setStats(characterSheet.getStats());
		c.get().setTempHealth(characterSheet.getTempHealth());
		c.get().setName(characterSheet.getName());
		c.get().setProficiencyBonus(characterSheet.getProficiencyBonus());
		c.get().setBackground(characterSheet.getBackground());
		c.get().setPlayerClasses(characterSheet.getPlayerClasses());
		c.get().setResources(characterSheet.getResources());
		return repo.save(c.get());
	}
	
	@PutMapping("/update/{id}/{Field}")
	public CharacterSheet updateField(@PathVariable int id, @PathVariable String Field,@RequestBody Object value ) {
		Optional<CharacterSheet> c = repo.findById(id);
		if (!c.isPresent()) {
			throw new RuntimeException("no character found");
		}
		try {
			switch(Field.toLowerCase()) {
			case "armourClass": c.get().setArmourClass((Integer) value);
			break;
			case "health": c.get().setHealth((Integer) value);
			break;
			case "initative": c.get().setInitative((Integer) value);
			break;
			case "speed": c.get().setSpeed((Integer) value);
			break;
			case "skills": c.get().setSkills((Skills) value);
			break;
			case "stats": c.get().setStats((Stats) value);
			break;
			case "tempHealth": c.get().setTempHealth((Integer) value);
			break;
			case "name": c.get().setName((String)value);
			break;
			case "proficiencyBonus": c.get().setProficiencyBonus((Integer) value);
			break;
			case "background": c.get().setBackground((String) value);
			break;
			case "playerClasses": c.get().setPlayerClasses((List<PlayerClass>) value);
			break;
			case "resources": c.get().setResources((List<Resource>) value);
			break;
			}
		} catch (Exception e) {
			throw new RuntimeException("invalid input type");
		}
		return repo.save(c.get());
	}
	
	@DeleteMapping("/delete/{id}")
	public String deleteCharacter(@PathVariable int id) {
		Optional<CharacterSheet> c = repo.findById(id);
		if (!c.isPresent()) {
			return "char not found";
		}
		repo.deleteById(id);
		return "char deleted";
	}
	
	@DeleteMapping("/deleteResource/{id}/{rName}")
	public String deleteResource(@PathVariable int id, @PathVariable String rName) {
		Optional<CharacterSheet> c = repo.findById(id);
		if (!c.isPresent()) {
			return "char not found";
		}
		Optional<Resource> r = rRepo.findByName(rName);
		if (!r.isPresent()) {
			return "resource not found";
		}
		List<Resource> ls = c.get().getResources();
		ls.add(r.get());
		c.get().setResources(ls);
		repo.save(c.get());
		return "'"+rName+"' deleted";
	}
	
	@DeleteMapping("/deletePlayerClass/{id}/{pName}")
	public String deletePlayerClass(@PathVariable int id, @PathVariable String pName) {
		Optional<CharacterSheet> c = repo.findById(id);
		if (!c.isPresent()) {
			return "char not found";
		}
		Optional<PlayerClass> p = pRepo.findByName(pName);
		if (!p.isPresent()) {
			return "class not found";
		}
		List<PlayerClass> ls = c.get().getPlayerClasses();
		ls.add(p.get());
		c.get().setPlayerClasses(ls);
		repo.save(c.get());
		return "'"+pName+"' deleted";
	}
	
}