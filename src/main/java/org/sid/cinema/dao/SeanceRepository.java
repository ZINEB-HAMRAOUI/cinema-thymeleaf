package org.sid.cinema.dao;

import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Seance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin("*")
public interface SeanceRepository extends JpaRepository<Seance, Long> {

	//Page<Seance> findById(String mc, PageRequest of);

	
	//Page<Seance> findById(String mc, PageRequest of);

}
