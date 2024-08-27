package com.example.Repositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.CharacterSheet;

@Repository
public interface CharRepo extends JpaRepository<CharacterSheet, Integer>{

}
