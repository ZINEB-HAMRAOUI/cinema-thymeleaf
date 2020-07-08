package org.sid.cinema.web;

import java.util.List;

import javax.validation.Valid;

import org.sid.cinema.dao.CategorieRepository;
import org.sid.cinema.dao.FilmRepository;
import org.sid.cinema.entities.Categorie;
import org.sid.cinema.entities.Cinema;
import org.sid.cinema.entities.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class filmC {
	@Autowired
	private FilmRepository filmRepository;
	
	
////liste film
	@GetMapping(path ="/film" )
	public String listfilm (Model model, 
			@RequestParam (name= "page", defaultValue= "0")int page,
			@RequestParam (name= "size", defaultValue= "3")int size,
	         @RequestParam (name= "keyword", defaultValue= "")String mc) {
			Page<Film>pageFilms=filmRepository.findByTitreContains(mc,
					PageRequest.of(page, size));
				
		model.addAttribute("film",pageFilms.getContent());
		model.addAttribute("pages", new int[pageFilms.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("keyword",mc);
	return "film";
	}
	//Ajouter 
			@GetMapping(path= "/formfilm")
			public String formfilm(Model model)
			{
				model.addAttribute("film",new Film());
				return "formfilm";
			}
			
			///save
			@PostMapping("/savefilm")
			public String savefilm(@Valid Film film,BindingResult bindingResult)
			{
				if(bindingResult.hasErrors()) return "formfilm";
				filmRepository.save(film);
				return "formfilm";
			}
			
			//Supprimer ************
			
			@GetMapping(path ="/deletefilm/{id}")
			@ResponseBody
			public String delete(@PathVariable Long id) {

				filmRepository.deleteById(id);
			       return "Successfully deleted";
			}
			///update
			@GetMapping(path= "/editfilm")
			public String editfilm(Model model,Long id)
			{Film p=filmRepository.findById(id).get();
				model.addAttribute("film",p);
				model.addAttribute("mode","edit");
				return "formfilm";
			}
	
}
