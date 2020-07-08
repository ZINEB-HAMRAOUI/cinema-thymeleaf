package org.sid.cinema.dao;

import org.sid.cinema.entities.Categorie;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Seance;
import org.sid.cinema.entities.Salle;

import org.sid.cinema.entities.Projection;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface ProjectionRepository extends JpaRepository<Projection, Long> {

	//Page<Projection> findById(String mc, PageRequest of);
	//public Page<Projection> findByNameContains(String mc, Pageable pageable);

}
