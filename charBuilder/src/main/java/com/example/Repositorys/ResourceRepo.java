package com.example.Repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.Resource;

@Repository
public interface ResourceRepo extends JpaRepository<Resource, Integer>{

	Optional<Resource> findByName(String rName);

}
