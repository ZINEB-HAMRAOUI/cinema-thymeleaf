package org.sid.cinema.web;

import java.util.List;

import javax.validation.Valid;

import org.sid.cinema.dao.CategorieRepository;
import org.sid.cinema.dao.ProjectionRepository;
import org.sid.cinema.entities.Categorie;
import org.sid.cinema.entities.Cinema;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Projection;
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
public class projectionC {
	@Autowired
private ProjectionRepository projectionRepository;
	
	
	///liste categorie
		@GetMapping(path ="/projection" )
		public String listProj (Model model, 
				@RequestParam (name= "page", defaultValue= "0")int page,
				@RequestParam (name= "size", defaultValue= "3")int size,
		@RequestParam (name= "keyword", defaultValue= "")String mc) {
				Page<Projection>pageProjections=projectionRepository.findAll(
						PageRequest.of(page, size));
					
			model.addAttribute("projections",pageProjections.getContent());
			model.addAttribute("pages", new int[pageProjections.getTotalPages()]);
			model.addAttribute("currentPage", page);
			model.addAttribute("size", size);
			model.addAttribute("keyword",mc);
		return "projection";
		}
		//Ajouter 
		@GetMapping(path= "/formprojection")
		public String formprojection(Model model)
		{
			model.addAttribute("projection",new Projection());
			return "formprojection";
		}
		
		///save
		@PostMapping("/saveprojection")
		public String saveprojection(@Valid Projection projection,BindingResult bindingResult)
		{
			if(bindingResult.hasErrors()) return "formprojection";
			projectionRepository.save(projection);
			return "formprojection";
		}
		//Supprimer ************
		
		@GetMapping(path ="/deleteprojection/{id}")
		@ResponseBody
		public String delete(@PathVariable Long id) {

			projectionRepository.deleteById(id);
		       return "Successfully deleted";
		}
		///update
		@GetMapping(path= "/editprojection")
		public String editprojection(Model model,Long id)
		{Projection p=projectionRepository.findById(id).get();
			model.addAttribute("projection",p);
			model.addAttribute("mode","edit");
			return "formprojection";
		}

		
}
