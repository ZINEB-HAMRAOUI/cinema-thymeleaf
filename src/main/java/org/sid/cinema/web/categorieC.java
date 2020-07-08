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
public class categorieC {

	@Autowired
	private CategorieRepository catRepoditory;
	
////liste categorie
	@GetMapping(path ="/categorie" )
	public String listCategorie (Model model, 
			@RequestParam (name= "page", defaultValue= "0")int page,
			@RequestParam (name= "size", defaultValue= "3")int size,
	@RequestParam (name= "keyword", defaultValue= "")String mc) {
			Page<Categorie>pageCategories=catRepoditory.findAll(
					PageRequest.of(page, size));
				
		model.addAttribute("categorie",pageCategories.getContent());
		model.addAttribute("pages", new int[pageCategories.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("keyword",mc);
	return "categorie";
	}
	
	//Ajouter cinema
			@GetMapping(path= "/formcategorie")
			public String Fcategotie(Model model)
			{
				model.addAttribute("categorie",new Categorie());
			//model.addAttribute("mode","new");
				return "formcategorie";
			}
			
			///savecinema
			@PostMapping("/savecategorie")
			public String savecategorie(@Valid Categorie categorie,BindingResult bindingResult)
			{
				if(bindingResult.hasErrors()) return "formcategorie";
				catRepoditory.save(categorie);
				return "formcategorie";
			}
			//Supprimer cinema************
		
			@GetMapping(path ="/deletecategorie/{id}")
			@ResponseBody
			public String delete(@PathVariable Long id) {

				catRepoditory.deleteById(id);
			       return "Successfully deleted";
			}
			///update
			@GetMapping(path= "/editcategorie")
			public String editCategorie(Model model,Long id)
			{Categorie p=catRepoditory.findById(id).get();
				model.addAttribute("categorie",p);
				model.addAttribute("mode","edit");
				return "formcategorie";
			}
			
}
