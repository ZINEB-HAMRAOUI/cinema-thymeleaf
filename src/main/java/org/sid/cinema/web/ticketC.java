package org.sid.cinema.web;

import javax.validation.Valid;

import org.sid.cinema.dao.CinemaRepository;
import org.sid.cinema.dao.TicketRepository;
import org.sid.cinema.entities.Cinema;
import org.sid.cinema.entities.Ticket;
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
public class ticketC {
	@Autowired
	private TicketRepository ticketRepository;
	
	@GetMapping(path ="/ticket" )
	public String list (Model model, 
			@RequestParam (name= "page", defaultValue= "0")int page,
			@RequestParam (name= "size", defaultValue= "3")int size, 
	@RequestParam (name= "keyword", defaultValue= "")String mc) {
			Page<Ticket>pageTickets=ticketRepository.findAll(
							PageRequest.of(page, size));
				
		model.addAttribute("ticket",pageTickets.getContent());
		model.addAttribute("pages", new int[pageTickets.getTotalPages()]);
		model.addAttribute("currentPage", page);
		model.addAttribute("size", size);
		model.addAttribute("keyword",mc);
	return "ticket";
	}
	///delete
	@GetMapping(path ="/deleteticket/{id}")
	@ResponseBody
	public String delete(@PathVariable Long id) {

		ticketRepository.deleteById(id);
	       return "Successfully deleted";
	}
	///update
			@GetMapping(path= "/editticket")
			public String editTicket(Model model,Long id)
			{Ticket p=ticketRepository.findById(id).get();
				model.addAttribute("ticket",p);
				model.addAttribute("mode","edit");
				return "formticket";
			}
			//Ajouter 
			@GetMapping(path= "/formticket")
			public String formticket(Model model)
			{
				model.addAttribute("ticket",new Ticket());
				return "formticket";
			}
			
			///save
			@PostMapping("/saveticket")
			public String saveticket(@Valid Ticket ticket,BindingResult bindingResult)
			{
				if(bindingResult.hasErrors()) return "formticket";
				ticketRepository.save(ticket);
				return "formticket";
			}
}
