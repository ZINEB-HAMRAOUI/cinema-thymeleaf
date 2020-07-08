package org.sid.cinema.web;

import java.util.List;

import javax.validation.Valid;

import org.sid.cinema.dao.CategorieRepository;
import org.sid.cinema.dao.CinemaRepository;
import org.sid.cinema.entities.Categorie;
import org.sid.cinema.entities.Cinema;
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
public class cinemaC {
	
	@Autowired
	private CinemaRepository cinemaRepository;
	@GetMapping(path = "/cinemas")
	public String list() {
		List<Cinema> cinemas=cinemaRepository.findAll();
		return "cinema";
	}
////liste cinema
	@GetMapping(path ="/zineb" )
	public String list (Model model, 
			@RequestParam (name= "page", defaultValue= "0")int page,
			@RequestParam (name= "size", defaultValue= "3")int size,
	@RequestParam (name= "keyword", defaultValue= "")String mc) {
			Page<Cinema>pageCinemas=cinemaRepository.findByNameContains(mc, 
					PageRequest.of(page, size));
				
		model.addAttribute("cinemas",pageCinemas.getContent());
		model.addAttribute("pages", new int[pageCinemas.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("keyword",mc);
	return "test";
	}
	
/*	
	@GetMapping(path = "/zineb")
	public String test() {
		return "test";
	}
*/
	
	
	//Supprimer cinema************
	
		@GetMapping(path ="/deletecinema/{id}")
		@ResponseBody
		public String delete(@PathVariable Long id) {

			cinemaRepository.deleteById(id);
		       return "Successfully deleted";
		}
		
		//Ajouter cinema
		@GetMapping(path= "/formcinema")
		public String formcinema(Model model)
		{
			model.addAttribute("cinema",new Cinema());
			return "formcinema";
		}
		
		///savecinema
		@PostMapping("/savecinema")
		public String savecinema(@Valid Cinema cinema,BindingResult bindingResult)
		{
			if(bindingResult.hasErrors()) return "formcinema";
			cinemaRepository.save(cinema);
			return "formcinema";
		}
		
		///update
		@GetMapping(path= "/editcinema")
		public String editCinema(Model model,Long id)
		{Cinema p=cinemaRepository.findById(id).get();
			model.addAttribute("cinema",p);
			model.addAttribute("mode","edit");
			return "formcinema";
		}
		////////////////////////////////////////////////////
	
}
