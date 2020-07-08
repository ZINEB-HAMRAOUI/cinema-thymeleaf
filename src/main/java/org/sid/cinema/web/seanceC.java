package org.sid.cinema.web;

import javax.validation.Valid;

import org.sid.cinema.dao.SeanceRepository;
import org.sid.cinema.entities.Film;
import org.sid.cinema.entities.Seance;
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
public class seanceC {
	@Autowired
	private SeanceRepository seanceRepository;
	@GetMapping(path ="/seance" )
	public String listseance(Model model, 
			@RequestParam (name= "page", defaultValue= "0")int page,
			@RequestParam (name= "size", defaultValue= "3")int size,
	         @RequestParam (name= "keyword", defaultValue= "")String mc) {
			Page<Seance>pageSeances=seanceRepository.findAll(
					PageRequest.of(page, size));
				
		model.addAttribute("seance",pageSeances.getContent());
		model.addAttribute("pages", new int[pageSeances.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("keyword",mc);
	return "seance";
	}
	//Ajouter 
	@GetMapping(path= "/formseance")
	public String formseance(Model model)
	{
		model.addAttribute("seance",new Seance());
		return "formseance";
	}
	
	///save
	@PostMapping("/saveseance")
	public String saveseance(@Valid Seance seance,BindingResult bindingResult)
	{
		if(bindingResult.hasErrors()) return "formseance";
		seanceRepository.save(seance);
		return "formseance";
	}
	//Supprimer ************
	
	@GetMapping(path ="/deleteseance/{id}")
	@ResponseBody
	public String delete(@PathVariable Long id) {

		seanceRepository.deleteById(id);
	       return "Successfully deleted";
	}
	///update
	@GetMapping(path= "/editseance")
	public String editseance(Model model,Long id)
	{Seance p=seanceRepository.findById(id).get();
		model.addAttribute("seance",p);
		model.addAttribute("mode","edit");
		return "formseance";
	}

	
}
