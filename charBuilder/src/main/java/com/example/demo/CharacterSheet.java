package com.example.demo;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterSheet {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int armourClass;
	private int health;
	private int initative;
	private int speed;
	
	@Embedded
	private Skills skills;
	
	@Embedded
	private Stats stats;
	private int tempHealth;
	private String name;
	private int proficiencyBonus;
	private String background;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<PlayerClass> playerClasses;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Resource> resources;
	
	
	
}