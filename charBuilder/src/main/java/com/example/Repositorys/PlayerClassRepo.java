package com.example.Repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.PlayerClass;

@Repository
public interface PlayerClassRepo extends JpaRepository<PlayerClass, Integer>{

	Optional<PlayerClass> findByName(String pName);

}
