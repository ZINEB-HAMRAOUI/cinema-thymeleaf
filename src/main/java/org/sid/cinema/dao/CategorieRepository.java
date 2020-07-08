package org.sid.cinema.dao;

import org.sid.cinema.entities.Categorie;
import org.sid.cinema.entities.Cinema;
import org.sid.cinema.entities.Film;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource

@CrossOrigin("*")
public interface CategorieRepository extends JpaRepository<Categorie, Long> {

	//Page<Categorie> findAll(String mc, PageRequest of);
	//public Page<Categorie> findById(String mc, Pageable pageable);

	//public Page<Categorie> findById(String mc, Pageable pageable);

	
	// public Page<Categorie> findByTitreContains(String mc, Pageable pageable);
}
